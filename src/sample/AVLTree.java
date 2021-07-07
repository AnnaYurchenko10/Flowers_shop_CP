package sample;

public class AVLTree {
    public static class Node
    {
        Node left, right, parent;
        Buyer value;
        int count = 0;

        public Node(Node parent, Buyer value) {
            this.parent = parent;
            this.value = value;
        }

        void setLeftL(Node L)
        {
            if (L!= null)
                L.parent = this;

            this.left = L;
        }

        void  setRightR(Node R)
        {
            if (R!= null)
                R.parent = this;

            this.right = R;
        }

        Buyer getBuyer()
        {
            return value;
        }

    }
    private Node root = null;

    public void insert(Buyer data) { // Добавление элемента
        insert(data, root);
    }

    private int height(Node node) { // Возвращает вес
        return node == null ? -1 : node.count;
    }

    private void reHeight(Node node) {
        node.count = Math.max(height(node.left), height(node.right)) + 1;
    }

    private void rotateRight(Node pivot) {
        Node parent = pivot.parent;
        Node leftL = pivot.left;
        Node rightChildOfLeftChild = leftL.right;
        pivot.setLeftL(rightChildOfLeftChild);
        leftL.setRightR(pivot);
        if (parent == null)
        {
            this.root = leftL;
            leftL.parent = null;
            return;
        }
        if (parent.left == pivot)
            parent.setLeftL(leftL);
        else
            parent.setRightR(leftL);
        reHeight(pivot);
        reHeight(leftL);
    }

    private void rotateLeft(Node pivot) {
        Node parent = pivot.parent;
        Node rightR = pivot.right;
        Node leftChildOfRightChild = rightR.left;
        pivot.setRightR(leftChildOfRightChild);
        rightR.setLeftL(pivot);
        if (parent == null) {
            this.root = rightR;
            rightR.parent = null;
            return;
        }
        if (parent.left == pivot)
            parent.setLeftL(rightR);
        else
            parent.setRightR(rightR);
        reHeight(pivot);
        reHeight(rightR);
    }

    private void rotateLeftThenRight(Node node) {
        rotateLeft(node.left);
        rotateRight(node);
    }
    private void rotateRightThenLeft(Node node) {
        rotateRight(node.right);
        rotateLeft(node);
    }

    public void insert(Buyer value, Node node)
    {
        if (root == null)
        {
            root = new Node(null, value);
            return;
        }

        if(value.getCardnumber() < node.value.getCardnumber())
        {
            if (node.left!= null)
                insert(value, node.left);
            else
                node.left = new Node(node, value);

            if(height(node.left) - height(node.right) == 2)
            {
                if (value.getCardnumber()<node.left.value.getCardnumber())
                    rotateRight(node);
                else
                    rotateLeftThenRight(node);
            }

        }
        else if (value.getCardnumber()> node.value.getCardnumber())
        {
            if (node.right!=null)
                insert(value,node.right);
            else
                node.right = new Node(node, value);

            if(height(node.right) - height(node.left) == 2)
            {
                if (value.getCardnumber()>node.right.value.getCardnumber())
                    rotateLeft(node);
                else
                    rotateRightThenLeft(node);
            }
        }
        reHeight(node);
    }

    private Node binarySearch(Node node, int key) {
        if (node == null) {
            return null;
        }
        if (key == node.value.getCardnumber()) {
            System.out.println(node.toString());
            return node;
        }
        if (key < node.value.getCardnumber() && node.left != null) return binarySearch(node.left, key);
        if (key > node.value.getCardnumber() && node.right != null) return binarySearch(node.right, key);
        return null;
    }


    public Node search(int key) {
        if(binarySearch(root, key) == null){
           return null;
        }
        return binarySearch(root, key);
    }

    private Node getRightLeafForRoot(Node cur) {
        if (cur.left == null && cur.right == null)
            return cur;
        if (cur.right != null)
            return getRightLeafForRoot(cur.right);
        else
            return getRightLeafForRoot(cur.left);
    }
    private Node getLeftLeafForRoot(Node cur) {
        if (isLeaf(cur))
            return cur;
        if (cur.left != null)
            return getLeftLeafForRoot(cur.left);
        else
            return getLeftLeafForRoot(cur.right);
    }

    private boolean isLeaf(Node node) {
        return node.left == null && node.right == null;
    }

    private boolean isLeftChild(Node child) {
        return (child.parent.left == child);
    }

    private int calDifference(Node node) {
        int rightHeight = height(node.right);
        int leftHeight = height(node.left);
        return rightHeight - leftHeight;
    }


    private void balanceTree(Node node) {
        int difference = calDifference(node);
        Node parent = node.parent;
        if (difference == -2) {
            if (height(node.left.left) >= height(node.left.right))
                rotateRight(node);
            else
                rotateLeftThenRight(node);
        } else if (difference == 2) {
            if (height(node.right.right) >= height(node.right.left))
                rotateLeft(node);
            else
                rotateRightThenLeft(node);
        }
        if (parent != null)
            balanceTree(parent);
        reHeight(node);
    }

    private Node immediatePredInOrder(Node node) {
        Node current = node.left;
        while (current.right != null)
            current = current.right;
        return current;
    }

    private Node deleteNode(Node target) {
        if (isLeaf(target)) { // leaf
            if (isLeftChild(target))
                target.parent.left = null;
            else
                target.parent.right = null;
        } else if (target.left == null ^ target.right == null) { // exact 1 child
            Node nonNullChild = target.left == null ? target.right : target.left;
            if (isLeftChild(target))
                target.parent.setLeftL(nonNullChild);
            else
                target.parent.setRightR(nonNullChild);
        } else { // 2 children
            Node immediatePredInOrder = immediatePredInOrder(target);
            target.value = immediatePredInOrder.value;
            target = deleteNode(immediatePredInOrder);
        }
        reHeight(target.parent);
        return target;
    }

    public boolean delete(int key) {
        Node target = search(key);
        if (target == root) {
            if (root.left == null && root.right == null) {
                root = null;
                return true;
            } else {
                if (root.left != null) {
                    Node cur = getLeftLeafForRoot(root.right);
                    if (isLeftChild(cur))
                        cur.parent.left = null;
                    else
                        cur.parent.right = null;
                    cur.left = root.left;
                    cur.right = root.right;
                    root = cur;
                    balanceTree(root);
                    return true;
                } else {
                    Node cur = getRightLeafForRoot(root.left);
                    if (isLeftChild(cur))
                        cur.parent.left = null;
                    else
                        cur.parent.right = null;
                    cur.left = root.left;
                    cur.right = root.right;
                    root = cur;
                    balanceTree(root);
                    return true;
                }
            }
        }
        if (target == null) return false;
        target = deleteNode(target);
        balanceTree(target.parent);
        return true;
    }

    public void clear() {
        root = null;
        System.gc();
    }


    private void preorder(Node node) {
        if (node != null) {
            preorder(node.left);
            buyersController.buyerlist.add(node.getBuyer());
            preorder(node.right);
        }
    }

    public void traversePreOrder() {
        preorder(root);
    }



}

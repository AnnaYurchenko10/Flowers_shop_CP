package sample;

public class List {
private Operation first;


    public List() {
        first = null;
    }

    public void insert(Operation operation)
    {
        operation.next = first;
        first = operation;
    }

    public void delete(Operation operation)
    {
        Operation current = first;
        Operation previous = first;
        while (!current.equals(operation))
        {
            previous = current;
            current = current.next;
        }
        if(current == first)
            first=first.next;
        else
            previous.next=current.next;
    }

    public Operation getFirst() {
        return first;
    }

    public void clear()
    {
        while (first!=null)
        {
            first = first.next;
        }
    }

    private Operation getByPosition(int pos) {
        Operation cur = first;
        for (int i = 0; i != pos; i++) {
            cur = cur.next;
        }
        return cur;
    }


    public void sort(List list, int start, int end) {//быстрая сортировка(хоара)
        if (start >= end)
            return;
        int middle = start - (start - end) / 2;
        int i = start, j = end;
        while (i < j) {
            while (i<middle && (list.getByPosition(i).getId() <= list.getByPosition(middle).getId()) ) {
                i++;
            }
            while (j>middle && list.getByPosition(middle).getId() <= list.getByPosition(j).getId()) {
                j--;
            }
            if (i < j) {
                int temp = list.getByPosition(i).getId();
                list.getByPosition(i).setId(list.getByPosition(j).getId());
                list.getByPosition(j).setId(temp);
                if (i == middle)
                    middle = j;
                else if (j == middle)
                   middle = i;
            }
        }
            sort(list, i, middle);
            sort(list, middle+1, j);
    }

}

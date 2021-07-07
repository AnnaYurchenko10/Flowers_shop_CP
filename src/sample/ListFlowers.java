package sample;

public class ListFlowers {
    private Flowers first;
    private int size = 0;

    public ListFlowers() {
        first = null;
    }

    public void add(Flowers flowers)
    {
        size++;
        flowers.next = first;
        first = flowers;
    }

    public void clear()
    {
        while (first!=null)
        {
            first = first.next;
        }
        size = 0;
    }

    public Flowers getFirst() {
        return first;
    }

}

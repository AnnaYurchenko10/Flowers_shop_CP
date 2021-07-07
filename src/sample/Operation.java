package sample;

public class Operation {
    private String type;
    private String flower;
    private int cardnumber;
    private int count;
    private Integer price;
    private int id;
    Operation next;
    static private int max = 1;

    public static void setMax(int max) {
        Operation.max = max;
    }

    public Operation(String flower, int count) {
        this.type = "Склад";
        this.id = max;
        max++;
        this.flower = flower;
        this.count = count;
    }

    public Operation(String flower,int cardnumber, int count, Integer price)
    {
        this.id = max;
        max++;
        this.type = "Покупатель";
        this.cardnumber = cardnumber;
        this.flower = flower;
        this.count = count;
        this.price = price;
    }

    public String getType() {
        return type;
    }

    public String getFlower() {
        return flower;
    }

    public int getCardnumber() {
        return cardnumber;
    }

    public int getCount() {
        return count;
    }

    public Integer getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


}

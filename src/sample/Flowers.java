package sample;

public class Flowers {
    String name,image;
    Integer number, price, count;
    static Integer max=1;
    Flowers next;

    public Flowers(String name, Integer price,String image) {
        this.name = name;
        this.number = max;
        this.price = price;
        this.count = 50;
        this.image = image;
        max++;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public Integer getNumber() {
        return number;
    }

    public Integer getPrice() {
        return price;
    }

    public Integer getCount() {
        return count;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Название: " + name  +
                "\nАртикул: " + number +
                "\nСтоимость: " + price +
                "\nКоличество на складе: " + count;
    }
}

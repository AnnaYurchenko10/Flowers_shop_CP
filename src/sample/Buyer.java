package sample;

import java.util.Objects;

public class Buyer {
    String fio, phone,email;
    int cardnumber;

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(int cardnumber) {
        this.cardnumber = cardnumber;
    }

    public Buyer(String fio, String phone, String email, int cardnumber) {
        this.fio = fio;
        this.phone = phone;
        this.email = email;
        this.cardnumber = cardnumber;
    }

    @Override
    public boolean equals(Object o) {//переопределение метода для правильного сравнения объектов
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Buyer buyer = (Buyer) o;
        return cardnumber == buyer.cardnumber &&
                Objects.equals(fio, buyer.fio) &&
                Objects.equals(phone, buyer.phone) &&
                Objects.equals(email, buyer.email);
    }

    @Override
    public int hashCode() {//генерация хэш-кода для хранения одинаковых объектов в одной ячейки памяти
        return Objects.hash(fio, phone, email, cardnumber);
    }
}

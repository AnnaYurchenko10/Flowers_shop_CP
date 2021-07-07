package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


import java.io.File;
import java.net.MalformedURLException;

public class flowersController {

    @FXML
    private ComboBox<String> flowerComboBox;

    @FXML
    private ImageView img;//изображение

    @FXML
    private TextArea infoarea;//поля для вывода информации

    @FXML
    private TextField namefield;//поле для ввода названия

    @FXML
    private TextField pricefield;//поле для ввода цены

    @FXML
    private TextField imagefield;//поле для ввода изображения

    @FXML
    private Label error;//сообщение об ошибке

    @FXML
    private TextField findnamefield;

    @FXML
    private TextField findnumberfield;


    static private ObservableList<Flowers> list = FXCollections.observableArrayList();//лист для добавления в comboBox

    static private HashTable hashTable = new HashTable();//хэш-таблица

    public static ObservableList<Flowers> getList() {
        return list;
    }
    public static HashTable getHashTable() { return hashTable; }


    @FXML
    void onClickAdd(ActionEvent event) {//добавление элемента

        if (namefield.getText().isEmpty() && pricefield.getText().isEmpty() &&
                imagefield.getText().isEmpty())
        {
            // надпись об ошибке
            error.setText("Заполните все поля!");
        }
        else {
            if (Integer.parseInt(pricefield.getText()) <= 0) {
                error.setText("Стоимость не может быть не положительной");
                return;
            }
            for(Flowers fl : list) {
                if(fl.getName().equals(namefield.getText()))//проверка на уникальность
                {
                    error.setText("Такой товар уже есть на складе!");
                    return;
                }

            }
            error.setText("");

            list.clear();
            flowerComboBox.getItems().clear();
            try {
                hashTable.insert(new Flowers(namefield.getText(),
                        Integer.parseInt(pricefield.getText()),imagefield.getText()));
            } catch (Exception e)
            {
                error.setText("Некорректно введена стоимость!");
                return;
            }
            hashTable.getElements();//заполнение списка
            for(Flowers fl : list) {
                flowerComboBox.getItems().add(fl.getName());//добавление в comboBox
            }
            namefield.clear();
            pricefield.clear();
            imagefield.clear();
        }
        System.out.println(HashTable.n);
        if(hashTable.checkFull())
            hashTable.createHashTable();
    }

    @FXML
    void comboBoxSelect(ActionEvent event) throws MalformedURLException {//выгрузка информации и изображения при нажатии в comboBox
         for(Flowers flowers:list)
         {
             if(flowers.getName().equals(flowerComboBox.getValue()))
             {
                 infoarea.setText(flowers.toString());
                 File file = new File(flowers.getImage());
                 String localURL = file.toURI().toURL().toString();
                 img.setImage(new Image(localURL));

               if(img.getImage().isError()) {
                   File file1 = new File("D:\\Учеба ГУАП\\Курсач САОД\\src\\sample\\resources\\notimage.png");
                   String localURL1 = file1.toURI().toURL().toString();
                   img.setImage(new Image(localURL1));
               }

             }
         }


    }


    @FXML
    void onClickDelete(ActionEvent event) {//удаление
        for (Flowers flowers:list)
        {
            if(flowerComboBox.getValue().equals(flowers.getName()))
            {
                hashTable.delete(flowers.getNumber());
                list.clear();
                flowerComboBox.getItems().clear();
                infoarea.clear();
                hashTable.getElements();
                img.setImage(null);
                break;
            }
        }

        for(Flowers fl : list) {
            flowerComboBox.getItems().add(fl.getName());//добавление в comboBox
        }

    }

    @FXML
    void onClickClearButton(ActionEvent event) {//очистка
        hashTable.clear();
        list.clear();
        flowerComboBox.getItems().clear();
        infoarea.clear();
        img.setImage(null);
    }

    @FXML
    void onClickFindButton(ActionEvent event) throws MalformedURLException {//поиск по названию,стоимости или артиклу
        try{
        error.setText("");
        for (Flowers flowers : list)
        {
            if(!findnumberfield.getText().isEmpty() || !findnamefield.getText().isEmpty())
            {
                    if (findnamefield.getText().equals(flowers.getName()) || Integer.parseInt(findnumberfield.getText()) == flowers.getNumber()) {
                        infoarea.setText(flowers.toString());
                        File file = new File(flowers.getImage());
                        String localURL = file.toURI().toURL().toString();
                        img.setImage(new Image(localURL));

                        if (img.getImage().isError()) {
                            File file1 = new File("D:\\Учеба ГУАП\\Курсач САОД\\src\\sample\\resources\\notimage.png");
                            String localURL1 = file1.toURI().toURL().toString();
                            img.setImage(new Image(localURL1));
                        }
                        error.setText("");
                        break;
                    } else {
                        error.setText("Такого цветка не существует!");
                    }
            }
            else {
                error.setText("Заполните поле!");
            }
        }
        }catch (Exception e){
            error.setText("Такого цветка не существует!");
        }
    }


    static void test()
    {
        hashTable.insert(new Flowers("Розы", 100,"D:\\Учеба ГУАП\\Курсач САОД\\src\\sample\\resources\\Розы.png"));
        hashTable.insert(new Flowers("Пионы", 120,"D:\\Учеба ГУАП\\Курсач САОД\\src\\sample\\resources\\Пионы.png"));
        hashTable.insert(new Flowers("Лилии", 180,"D:\\Учеба ГУАП\\Курсач САОД\\src\\sample\\resources\\Лилии.png"));
        hashTable.insert(new Flowers("Тюльпаны", 80,"D:\\Учеба ГУАП\\Курсач САОД\\src\\sample\\resources\\Тюльпаны.png"));
        hashTable.getElements();//заполнение списка
    }


    @FXML
    void initialize() {
        for(Flowers fl : list) {
            flowerComboBox.getItems().add(fl.getName());//добавление в comboBox
        }
    }

}

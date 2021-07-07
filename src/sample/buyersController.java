package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;

public class buyersController {
    @FXML
    private Button addButton;//кнопка добавления

    @FXML
    private TextField fiofieid;//поле ФИО

    @FXML
    private TextField cardnumberfield;//поле бонусная карта

    @FXML
    private TextField phonefield;// поле номер телефона

    @FXML
    private TextField emailfield;// поле почты

    @FXML
    private Button deleteButton;//кнопка удаления

    @FXML
    private Label error;//вывод сообщения

    @FXML
    private TableColumn<Buyer, String> fioColumn;

    @FXML
    private TableColumn<Buyer, Long> cardnumberColumn;

    @FXML
    private TableColumn<Buyer, String> phoneColumn;

    @FXML
    private TableColumn<Buyer, String> emailColumn;

    @FXML
    private TextField cardfindfield;//поле поиска по номеру карты

    @FXML
    private Button findButton;//кнопка поиска

    @FXML
    private Button clearButton;//кнопка полной очистки

    @FXML
    private TextField fiofindfield;//поле поиска по ФИО

    @FXML
    private TableView<Buyer> tablebuyers;//таблица пользователей
    

    static AVLTree tree = new AVLTree();
    static ObservableList<Buyer> buyerlist = FXCollections.observableArrayList();//список для вывода в таблицу
    static ObservableList<Buyer> findlist = FXCollections.observableArrayList();//список поиска пользователей

    public boolean unique(Buyer newbuyer)//проверка на уникальность по телефон, номер карты, маил
    {
        for (Buyer buyer : buyerlist) {
            if (buyer.phone.equals(newbuyer.phone) || buyer.email.equals(newbuyer.email) ||
                    buyer.getCardnumber() == newbuyer.getCardnumber())
                return false;
        }
        return true;
    }

    public boolean correctData()//проверка на корректность ввода данных
    {
        if (fiofieid.getText().contains("[0-9]+") && !(emailfield.getText().contains("@")))//проверка ФИО и email
        {
            return false;
        }

        try {//проверка телефона и номера карты
            Long.parseLong(cardnumberfield.getText());
            Long.parseLong(phonefield.getText());
        } catch (Exception e) {
            return false;
        }

        if ((!(phonefield.getText().charAt(0) == '+') || !(phonefield.getText().charAt(1) == '7') ||
                !(phonefield.getLength() == 12)))
            return false;

        return true;
    }

    @FXML
    void clickAddButton(ActionEvent event) {//добавление в дерево и в таблицу
        if (!fiofieid.getText().isEmpty() && !cardnumberfield.getText().isEmpty() &&
                !phonefield.getText().isEmpty() && !emailfield.getText().isEmpty()) {
            error.setVisible(false);//видимость ошибки
            if (correctData()) {
                Buyer buyer = new Buyer(fiofieid.getText(), phonefield.getText(), emailfield.getText(),
                        Integer.parseInt(cardnumberfield.getText()));
                //проверка
                if (unique(buyer)) {
                    tree.insert(buyer);//добавление в дерево
                    fiofieid.clear();
                    cardnumberfield.clear();
                    phonefield.clear();
                    emailfield.clear();
                } else {
                    error.setText("Пользователь с такими данными существует!");
                    error.setVisible(true);
                }

                //очистка
                buyerlist.clear();
                tree.traversePreOrder();//вывод дерева
            } else {
                error.setText("Неккоректно введены данные!");
                error.setVisible(true);
            }
        } else {
            error.setText("Заполните все поля!");
            error.setVisible(true);
        }
    }

    @FXML
    void clickDeleteButton(ActionEvent event) {//удаление из таблицы и дерева
        int row = tablebuyers.getSelectionModel().getSelectedIndex();
        tree.delete(buyerlist.get(row).getCardnumber());
        tablebuyers.getItems().remove(row);
    }

    @FXML
    void clickFindButton(ActionEvent event) throws IOException {//поиск по ФИО и номеру бонусной карты
        findlist.clear();
        if (!(fiofindfield.getText().isEmpty()) || !(cardfindfield.getText().isEmpty())) {
            for (Buyer buyer : buyerlist)
                try {
                    if (fiofindfield.getText().equals(buyer.getFio()) ||
                            Integer.parseInt(cardfindfield.getText()) == buyer.getCardnumber())
                        findlist.add(buyer);
                }catch (Exception e){}
        }
        Parent root = FXMLLoader.load(getClass().getResource("Information.fxml"));
        Stage infostage = new Stage();
        infostage.setScene(new Scene(root));
        infostage.show();
    }

    @FXML
    void clickClearButton(ActionEvent event) {//полная очистка
        tree.clear();
        buyerlist.clear();
    }

    static void test()
    {
        tree.insert(new Buyer("Юрченко Анна Евгеньевна", "+79174999706", "Ann20001010@mail.ru", 45));
        tree.insert(new Buyer("Биккулова Лилия Фаизовна", "+79174887065", "LiLiPrime@mail.ru", 50));
        tree.insert(new Buyer("Саргсян Кристина Эдуардовна", "+79175621476", "Kristofo@gmail.com", 10));
        tree.insert(new Buyer("Вязевцева Лидия Григорьевна", "+79053078686", "Babulita@mail.ru", 69));
        tree.insert(new Buyer("Юрченко Евгений Сергеевич", "+79874887065", "Papulya@mail.ru", 8));
        tree.insert(new Buyer("Баранова Олеся Владимировна", "+79873125871", "Mamulya@gmail.com", 105));
        tree.traversePreOrder();
    }



    @FXML
    void initialize() {
        fioColumn.setCellValueFactory(new PropertyValueFactory<Buyer,String>("fio"));
        cardnumberColumn.setCellValueFactory(new PropertyValueFactory<Buyer, Long>("cardnumber"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Buyer,String>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Buyer,String>("email"));
        tablebuyers.setItems(buyerlist);//добавляем элементы списка в таблицу

    }
}

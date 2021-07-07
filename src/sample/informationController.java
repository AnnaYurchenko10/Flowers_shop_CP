package sample;

import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class informationController {
    @FXML
    private TableView<Buyer> tablefind;//таблица для поиска пользователей

    @FXML
    private TableColumn<Buyer, String> fioColumn;

    @FXML
    private TableColumn<Buyer, Long> cardnumberColumn;

    @FXML
    private TableColumn<Buyer, String> phoneColumn;

    @FXML
    private TableColumn<Buyer, String> emailColumn;

    @FXML
    void initialize() {
        fioColumn.setCellValueFactory(new PropertyValueFactory<Buyer,String>("fio"));
        cardnumberColumn.setCellValueFactory(new PropertyValueFactory<Buyer, Long>("cardnumber"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<Buyer,String>("phone"));
        emailColumn.setCellValueFactory(new PropertyValueFactory<Buyer,String>("email"));
        tablefind.setItems(buyersController.findlist);//добавляем элементы списка в таблицу
    }
}

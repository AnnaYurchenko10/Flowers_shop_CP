package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class operationController {

    @FXML
    private TableView<Operation> operationTable;

    @FXML
    private TableColumn<Operation, Integer> idColumn;

    @FXML
    private TableColumn<Operation, String> typeColumn;

    @FXML
    private TableColumn<Operation, Integer> cardColumn;

    @FXML
    private TableColumn<Operation, String> flowersColumn;

    @FXML
    private TableColumn<Operation, Integer> countColumn;

    @FXML
    private TableColumn<Operation, Integer> priceColumn;


    @FXML
    void onClickClearButton(ActionEvent event) {
        orderController.getOperationlist().clear();
        orderController.getList().clear();
        Operation.setMax(1);
    }

    @FXML
    void onClickDelButton(ActionEvent event) {
        int row = operationTable.getSelectionModel().getSelectedIndex();
        orderController.getList().delete(orderController.getOperationlist().get(row));
        operationTable.getItems().remove(row);
    }



    @FXML
    void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Operation,Integer>("id"));
        cardColumn.setCellValueFactory(new PropertyValueFactory<Operation, Integer>("cardnumber"));
        flowersColumn.setCellValueFactory(new PropertyValueFactory<Operation, String>("flower"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<Operation, String>("type"));
        countColumn.setCellValueFactory(new PropertyValueFactory<Operation, Integer>("count"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<Operation, Integer>("price"));
        operationTable.setItems(orderController.getOperationlist());
    }
}

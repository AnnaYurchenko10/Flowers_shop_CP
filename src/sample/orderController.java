package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class orderController {

    @FXML
    private ComboBox<String> comboBoxWarehouse;

    @FXML
    private TextField countfieldWarehouse;

    @FXML
    private Button buttonWarehouse;

    @FXML
    private TextField cardfieldBuyer;

    @FXML
    private ComboBox<String> comboBoxBuyer;

    @FXML
    private TextField countfieldBuyer;

    @FXML
    private Button buttonBuyer;

    @FXML
    private Label errorBuyer;

    @FXML
    private Label errorWarehouse;

    public static List getList() {
        return list;
    }

    static private List list = new List();

    static private ObservableList<Operation> operationlist = FXCollections.observableArrayList();

    public static ObservableList<Operation> getOperationlist() {
        return operationlist;
    }

    void show()//вывод в таблицу операций
    {
        operationlist.clear();
        Operation current = list.getFirst();
        while (current != null)
        {
            operationlist.add(current);
            current = current.next;
        }
    }



    @FXML
    void onClickBuyer(ActionEvent event) {//ПОКУПАТЕЛЬ
            errorBuyer.setText("");//очищаем поля сообщения о ошибке

        if (comboBoxBuyer.getValue().equals(null) || cardfieldBuyer.getText().isEmpty() ||
                countfieldBuyer.getText().isEmpty()) {
            errorBuyer.setText("Заполните все поля!");
        }
        else {
                try {
                    if (buyersController.tree.search(Integer.parseInt(cardfieldBuyer.getText())) == null)
                    {
                        errorBuyer.setText("Пользователя с такой картой не существует!");
                    }
                    else {
                        if(Integer.parseInt(countfieldBuyer.getText()) <=0)//проверка на корректный ввод количества цветов
                        {
                            throw new Exception();
                        }

                        if (flowersController.getHashTable().searchFlowers(comboBoxBuyer.getValue()).getCount() >=
                                Integer.parseInt(countfieldBuyer.getText())) //проверяем есть ли столько цаетов на складе, сколько запрашивает пользователь при заказе
                        {

                            int k = flowersController.getHashTable().searchFlowers(comboBoxBuyer.getValue()).getCount() -
                                    Integer.parseInt(countfieldBuyer.getText());//высчитываем остаток на складе
                            flowersController.getHashTable().searchFlowers(comboBoxBuyer.getValue()).setCount(k);//добавляем остток на склад


                            int price = flowersController.getHashTable().searchFlowers(comboBoxBuyer.getValue()).getPrice() *
                                    Integer.parseInt(countfieldBuyer.getText());//расчет стоимость за букет

                            list.insert(new Operation(comboBoxBuyer.getValue(), Integer.parseInt(cardfieldBuyer.getText()),
                                    Integer.parseInt(countfieldBuyer.getText()),price));//добавляем в список операций

                            errorBuyer.setText("Заказ добавлен!");

                        }
                        else {
                            errorBuyer.setText("Количество отсутствует на складе!");
                        }
                    }
                } catch (Exception e) {
                    errorBuyer.setText("Некорректный ввод данных!");
                }

                show();//вывод в таблицу операций
            }
        }

    @FXML
    void onClickWarehouse(ActionEvent event) {//СКЛАД
        errorWarehouse.setText("");

        if(comboBoxWarehouse.getValue() == null || countfieldWarehouse.getText().isEmpty())
           errorWarehouse.setText("Заполните все поля!");
        else {

            try {
                if (Integer.parseInt(countfieldWarehouse.getText()) <= 0)//проверка на корректный ввод количества цветов
                {
                    throw new Exception();
                }
                list.insert(new Operation(comboBoxWarehouse.getValue(),
                        Integer.parseInt(countfieldWarehouse.getText())));

                int k = flowersController.getHashTable().searchFlowers(comboBoxWarehouse.getValue()).getCount();
                flowersController.getHashTable().searchFlowers(comboBoxWarehouse.getValue()).setCount(k + Integer.parseInt(countfieldWarehouse.getText()));

                errorWarehouse.setText("Заказ добавлен!");
            } catch (Exception e) {
                errorWarehouse.setText("Некорреткный ввод данных!");
            }
            show();
        }

    }

    @FXML
    void onClick(MouseEvent event) {
        errorWarehouse.setText("");
        errorBuyer.setText("");
    }

    @FXML
    void initialize() {
        for(Flowers flowers: flowersController.getList() )
        {
            comboBoxWarehouse.getItems().add(flowers.getName());
            comboBoxBuyer.getItems().add(flowers.getName());
        }
    }
}

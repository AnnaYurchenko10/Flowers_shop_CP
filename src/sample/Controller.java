package sample;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buyers;

    @FXML
    private Button flowers;

    @FXML
    private Button order;

    @FXML
    private Button operations;

    @FXML
    void initialize() {

    }


    @FXML
    void clickButton1(ActionEvent event) throws IOException {
        Parent buyers = FXMLLoader.load(getClass().getResource("Buyers.fxml"));
        Stage stagebuyers = new Stage();
        stagebuyers.setScene(new Scene(buyers));
        stagebuyers.show();
    }

    @FXML
    void clickButton2(ActionEvent event) throws IOException {
        Parent flowers = FXMLLoader.load(getClass().getResource("Flowers.fxml"));
        Stage stageflowers = new Stage();
        stageflowers.setScene(new Scene(flowers));
        stageflowers.show();
    }

    @FXML
    void clickButton3(ActionEvent event) throws IOException {
        Parent order = FXMLLoader.load(getClass().getResource("Order.fxml"));
        Stage stageorder = new Stage();
        stageorder.setScene(new Scene(order));
        stageorder.show();
    }

    @FXML
    void clickButton4(ActionEvent event) throws IOException {
        Parent operation = FXMLLoader.load(getClass().getResource("Operation.fxml"));
        Stage stageoperation = new Stage();
        stageoperation.setScene(new Scene(operation));
        stageoperation.show();
    }
}

package com.example.project2new;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private Button btnBusiness;

    @FXML
    private Button btnEconomy;

    @FXML
    private Menu menuBusiness;

    @FXML
    private Menu menuEconomy;

    @FXML
    private Menu menuSports;

    @FXML
    private Menu menuTechnology;


    @FXML
    private Button btnExit;

    @FXML
    private Button btnContact;

    @FXML
    private Button btnInfo;

    @FXML
    private Button btnSports;

    @FXML
    private Button btnTechnology;
    Alert alert = new Alert(Alert.AlertType.INFORMATION);


    @FXML
    void toLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage)(btnExit.getScene().getWindow());
        Pane fxmlLoader = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.setScene(new Scene(fxmlLoader,700,400));
        stage.show();
        System.out.println("Login page is opened");
    }

    @FXML
    void contact(ActionEvent event) {
        alert.setContentText("Our mail: 220107138@sdu.stu.edu.kz\nOur phone number: 8 705 419 2357");
        alert.show();
        System.out.println("User checks contact data");
    }

    @FXML
    void info(ActionEvent event) {
        alert.setContentText("Your username is " + HelloApplication.thisUser);
        alert.show();
        System.out.println("User check own username");
    }

    @FXML
    void toBusiness(ActionEvent event) {
        Alert alertBusiness = new Alert(Alert.AlertType.INFORMATION);
        alertBusiness.setContentText("Nothing interesting happens in the business world");
        alertBusiness.show();
    }

    @FXML
    void toEconomy(ActionEvent event) {
        Alert alertEconomy = new Alert(Alert.AlertType.INFORMATION);
        alertEconomy.setContentText("Nothing interesting happens in the economy world");
        alertEconomy.show();
    }

    @FXML
    void toSports(ActionEvent event) {
        Alert alertSports = new Alert(Alert.AlertType.INFORMATION);
        alertSports.setContentText("Nothing interesting happens in the sport world");
        alertSports.show();
    }

    @FXML
    void toTechnology(ActionEvent event) {
        Alert alertTechnology = new Alert(Alert.AlertType.INFORMATION);
        alertTechnology.setContentText("Nothing interesting happens in the technology world");
        alertTechnology.show();
    }

}

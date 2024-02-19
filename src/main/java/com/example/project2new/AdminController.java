package com.example.project2new;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Timer;
import java.util.TimerTask;

public class AdminController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnLogin;

    @FXML
    private Label lblPW9;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblUN9;

    @FXML
    private Label lblUsername;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

   Alert errorAlert = new Alert(Alert.AlertType.ERROR);

    @FXML
    void checkData(ActionEvent event) throws IOException {
        if (username.getText().replace(" ", "").equals("")
                || password.getText().replace(" ", "").equals("")
                || username.getText().length() > 9 || password.getText().length() > 9) {
            if (username.getText().replace(" ", "").equals("")
                    || password.getText().replace(" ", "").equals("")) {
                if (username.getText().replace(" ", "").equals("")) {
                    lblUsername.setVisible(true);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            lblUsername.setVisible(false);
                        }}, 2000);
                    System.out.println("Username field is empty");
                }
                if (password.getText().replace(" ", "").equals("")) {
                    lblPassword.setVisible(true);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            lblPassword.setVisible(false);
                        }}, 2000);
                    System.out.println("Password field is empty");
                }
            } if (username.getText().length() > 9 || password.getText().length() > 9) {
                if (username.getText().length() > 9) {
                    lblUN9.setVisible(true);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            lblUN9.setVisible(false);
                        }}, 2000);
                    System.out.println("Username must be less than 10 characters");
                }
                if (password.getText().length() > 9) {
                    lblPW9.setVisible(true);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            lblPW9.setVisible(false);
                        }}, 2000);
                    System.out.println("Password must be less than 10 characters");
                }
            }
        } else if (username.getText().equals(password.getText())) {
            errorAlert.setContentText("Password matched!");
            errorAlert.show();
            System.out.println("Password matched");
        } else if (username.getText().equals("Medat") && password.getText().equals("qwerty")) {
            //to home page
            Stage stage = (Stage)(btnLogin.getScene().getWindow());
            Pane fxmlLoader = FXMLLoader.load(getClass().getResource("Home.fxml"));
            stage.setScene(new Scene(fxmlLoader,700,400));
            stage.show();

            System.out.println("Home page is opened");
            System.out.println("Admin Login successful");
        } else {
            errorAlert.setContentText("The admin username/password incorrect");
            errorAlert.show();
            System.out.println("Wrong user");
        }

    }

    @FXML
    void toLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage)(btnBack.getScene().getWindow());
        Pane fxmlLoader = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.setScene(new Scene(fxmlLoader,700,400));
        stage.show();
        System.out.println("Login page is opened");
    }

}

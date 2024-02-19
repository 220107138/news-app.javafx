package com.example.project2new;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblUsername;

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    private Button btnAdmin;

    @FXML
    private Button btnForgot;

    @FXML
    private PasswordField password;

    @FXML
    private TextField username;

    @FXML
    private Label lblUN9;

    @FXML
    private Label lblPW9;

    static File file = new File("C:\\Users\\Medat\\IdeaProjects\\project2NEW\\src\\main\\resources\\com\\example\\project2new\\dir\\file.txt");
    Alert errorAlert = new Alert(AlertType.ERROR);

    @FXML
    void toSignUp(ActionEvent event) throws IOException {
        Stage stage = (Stage)(btnLogin.getScene().getWindow());
        Pane fxmlLoader = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
        stage.setScene(new Scene(fxmlLoader,700,400));
        stage.show();
        System.out.println("Sign Up page is opened");
    }

    @FXML
    void toAdminLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage)(btnAdmin.getScene().getWindow());
        Pane fxmlLoader = FXMLLoader.load(getClass().getResource("AdminLogin.fxml"));
        stage.setScene(new Scene(fxmlLoader,700,400));
        stage.show();
        System.out.println("Admin Login page is opened");
    }

    @FXML
    void toForgotPassword(ActionEvent event) throws IOException {
        Stage stage = (Stage)(btnForgot.getScene().getWindow());
        Pane fxmlLoader = FXMLLoader.load(getClass().getResource("ForgotPassword.fxml"));
        stage.setScene(new Scene(fxmlLoader,350,400));
        stage.show();
        System.out.println("Forgot Password page is opened");
    }

    @FXML
    void checkData(ActionEvent event) {
        try {
            SignUpController.createFolder();
            SignUpController.readFile();
            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[fis.available()];
            fis.read(data);
            fis.close();
            String users = new String(data);

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
            } else if (users.contains("Username: " + username.getText() + "\nPassword: " + password.getText()) ||
                    users.contains("Password: " + password.getText() + "\nEmail: " + username.getText())) {

                //to home page
                Stage stage = (Stage)(btnLogin.getScene().getWindow());
                Pane fxmlLoader = FXMLLoader.load(getClass().getResource("Home.fxml"));
                stage.setScene(new Scene(fxmlLoader,700,400));
                stage.show();
                System.out.println("Home page is opened");

                System.out.println("Login successful");
                HelloApplication.thisUser = username.getText();
                System.out.println("Current session by user " + HelloApplication.thisUser);
            } else {
                errorAlert.setContentText("The username/password incorrect");
                errorAlert.show();
                System.out.println("Wrong user");
            }
        } catch (IOException ex) {
            System.out.println("Error");
        }
    }

    @FXML
    void remMe(ActionEvent event) {

    }

}
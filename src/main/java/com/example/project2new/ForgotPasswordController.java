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
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

public class ForgotPasswordController {

    @FXML
    private Button btnBack;

    @FXML
    private Button btnUpdate;

    @FXML
    private PasswordField password;

    @FXML
    private PasswordField password1;

    @FXML
    private TextField username;

    @FXML
    private Label lblPW19;

    @FXML
    private Label lblPW9;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblPassword1;

    @FXML
    private Label lblUN9;

    @FXML
    private Label lblUsername;

    static File file = new File("C:\\Users\\Medat\\IdeaProjects\\project2NEW\\src\\main\\resources\\com\\example\\project2new\\dir\\file.txt");
    Alert errorAlert = new Alert(Alert.AlertType.ERROR);
    Alert errorAlert1 = new Alert(Alert.AlertType.ERROR);

    @FXML
    void toLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage)(btnBack.getScene().getWindow());
        Pane fxmlLoader = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.setScene(new Scene(fxmlLoader,700,400));
        stage.show();
        System.out.println("Login page is opened");
    }

    @FXML
    void updateData(ActionEvent event) throws IOException {
        SignUpController.createFolder();
        SignUpController.readFile();
        FileInputStream fis = new FileInputStream(file);
        byte[] data = new byte[fis.available()];
        fis.read(data);
        fis.close();
        String oldData = new String(data);
        String[] users = oldData.split("///\n\n");
        String newData = "";

        if (username.getText().replace(" ", "").equals("")
                || password.getText().replace(" ", "").equals("")
                || password1.getText().replace(" ", "").equals("")
                || password.getText().length() > 9 || password1.getText().length() > 9) {
            if (username.getText().replace(" ", "").equals("")
                    || password.getText().replace(" ", "").equals("")
                    || password1.getText().replace(" ", "").equals("")) {
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
                if (password1.getText().replace(" ", "").equals("")) {
                    lblPassword1.setVisible(true);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            lblPassword1.setVisible(false);
                        }
                    }, 2000);
                    System.out.println("Password field is empty");
                }
            } if (password.getText().length() > 9 || password1.getText().length() > 9) {
                if (password.getText().length() > 9) {
                    lblUN9.setVisible(true);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            lblUN9.setVisible(false);
                        }}, 2000);
                    System.out.println("Username must be less than 10 characters");
                }
                if (password1.getText().length() > 9) {
                    lblPW9.setVisible(true);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            lblPW9.setVisible(false);
                        }}, 2000);
                    System.out.println("Password must be less than 10 characters");
                }
            }
        } else if (username.getText().equals(password.getText()) || !password.getText().equals(password1.getText())) {
            if (username.getText().equals(password.getText())) {
                errorAlert.setContentText("Password matched with username!");
                errorAlert.show();
                System.out.println("Password matched with username");
            }
            if (!password.getText().equals(password1.getText())) {
                errorAlert1.setContentText("Passwords are NOT matched!");
                errorAlert1.show();
                System.out.println("Password are NOT matched!");
            }
        } else if (oldData.contains(username.getText())) {
            for (int i = 0; i < users.length; i++) {
                if (users[i].contains(username.getText())) {
                    System.out.println(users[i]);
                    String s[] = users[i].replace("Username: " + username.getText() + "\nPassword: ", "").split("\n");
                    users[i] = users[i].replace(s[0], password.getText());
                }
                newData += users[i] + "///\n\n";
            }
            new RandomAccessFile(file, "rw").writeBytes(newData);
            System.out.println("Data is updated successful");

            //to home page
            Stage stage = (Stage)(btnUpdate.getScene().getWindow());
            Pane fxmlLoader = FXMLLoader.load(getClass().getResource("Home.fxml"));
            stage.setScene(new Scene(fxmlLoader,700,400));
            stage.show();
            System.out.println("Home page is opened");
        } else {
            errorAlert.setContentText("The username incorrect");
            errorAlert.show();
            System.out.println("Wrong user");
        }
    }
}
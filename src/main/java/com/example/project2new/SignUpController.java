package com.example.project2new;

import java.io.*;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class SignUpController {

    @FXML
    private Button btnLogin;

    @FXML
    private Button btnSignUp;

    @FXML
    private TextField email;

    @FXML
    private Label lblEmail;

    @FXML
    private Label lblPW9;

    @FXML
    private Label lblPassword;

    @FXML
    private Label lblUN9;

    @FXML
    private Label lblGender;

    @FXML
    private Label lblUsername;

    @FXML
    private TextField password;

    @FXML
    private RadioButton rbtnFemale;

    @FXML
    private RadioButton rbtnMale;

    @FXML
    private RadioButton rbtnOther;

    @FXML
    private TextField username;

    static File file = new File("C:\\Users\\Medat\\IdeaProjects\\project2NEW\\src\\main\\resources\\com\\example\\project2new\\dir\\file.txt");

    static void createFolder() {
        File dir = new File("C:\\Users\\Medat\\IdeaProjects\\project2NEW\\src\\main\\resources\\com\\example\\project2new\\dir");
        if (!dir.exists()) {
            dir.mkdir();
            System.out.println("Folder has been created");
        } else
            System.out.println("Folder already exists");
    }

    static void readFile() throws IOException {
        if (file.exists())
            System.out.println("File already exists");
        else {
            file.createNewFile();
            System.out.println("File has been created");
        }
    }

    void countLines() throws FileNotFoundException {
        int numberOfLines = 1;
        Scanner sc = new Scanner(file);
        while (sc.hasNextLine()) {
            numberOfLines++;
            sc.nextLine();
        }
        sc.close();
        System.out.println("Number of lines " + numberOfLines + " and number of users " + (int)(numberOfLines/5));
}

    @FXML
    void isFemale(ActionEvent event) {
        rbtnMale.setSelected(false);
        rbtnOther.setSelected(false);
    }

    @FXML
    void isMale(ActionEvent event) {
        rbtnFemale.setSelected(false);
        rbtnOther.setSelected(false);
    }

    @FXML
    void isOther(ActionEvent event) {
        rbtnMale.setSelected(false);
        rbtnFemale.setSelected(false);
    }

    @FXML
    void addData(ActionEvent event) {
        try {
            createFolder();
            readFile();

            FileInputStream fis = new FileInputStream(file);
            byte[] data = new byte[fis.available()];
            fis.read(data);
            fis.close();

            if (username.getText().replace(" ", "").equals("")
                    || password.getText().replace(" ", "").equals("")
                    || email.getText().replace(" ", "").equals("")
                    || !(rbtnMale.isSelected() || rbtnFemale.isSelected() || rbtnOther.isSelected())) {
                if (username.getText().replace(" ", "").equals("")) {
                    lblUsername.setVisible(true);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            lblUsername.setVisible(false);
                        }
                    }, 2000);
                    System.out.println("Username field is empty");
                }
                if (password.getText().replace(" ", "").equals("")) {
                    lblPassword.setVisible(true);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            lblPassword.setVisible(false);
                        }
                    }, 2000);
                    System.out.println("Password field is empty");
                }
                if (email.getText().replace(" ", "").equals("")) {
                    lblEmail.setVisible(true);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            lblEmail.setVisible(false);
                        }
                    }, 2000);
                    System.out.println("Email field is empty");
                }
                if (!(rbtnMale.isSelected() || rbtnFemale.isSelected() || rbtnOther.isSelected())) {
                    lblGender.setVisible(true);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            lblGender.setVisible(false);
                        }
                    }, 2000);
                    System.out.println("Gender isn't selected");
                }
            } else if (username.getText().length() > 9 || password.getText().length() > 9) {
                if (username.getText().length() > 9) {
                    lblUN9.setVisible(true);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            lblUN9.setVisible(false);
                        }
                    }, 2000);
                    System.out.println("Username must be less than 10 characters");
                }
                if (password.getText().length() > 9) {
                    lblPW9.setVisible(true);
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            lblPW9.setVisible(false);
                        }
                    }, 2000);
                    System.out.println("Password must be less than 10 characters");
                }
            } else if (username.getText().equals(password.getText())) {
                new Alert(AlertType.ERROR).setContentText("Password matched!");
                System.out.println("Password matched");
            } else if (new String(data).contains("Username: " + username.getText() + "\nPassword: " + password.getText())) {
                new Alert(AlertType.ERROR).setContentText("User already has account");
                System.out.println("User already has account");
            } else {
                String sEmail = email.getText();
                String sGender = "Other///\n\n";
                if (!sEmail.contains("@"))
                    sEmail += "@sdu.edu.kz";
                if (rbtnMale.isSelected())
                    sGender = "Male///\n\n";
                else if (rbtnFemale.isSelected()) {
                    sGender = "Female///\n\n";
                }
                new RandomAccessFile(file, "rw").
                        writeBytes(new String(data) + "Username: " + username.getText() + "\nPassword: "
                                + password.getText() + "\nEmail: " + sEmail + "\nGender: " + sGender);

                //to home page
                Stage stage = (Stage) (btnSignUp.getScene().getWindow());
                Pane fxmlLoader = FXMLLoader.load(getClass().getResource("Home.fxml"));
                stage.setScene(new Scene(fxmlLoader, 700, 400));
                stage.show();
                System.out.println("Home page is opened");
                System.out.println("Data has been saved\nSign Up successful");

                HelloApplication.thisUser = username.getText();
                System.out.println("Current session by user " + HelloApplication.thisUser);
            }
            countLines();
        } catch (IOException ex) {
            System.out.println("Error");
        }
    }

    @FXML
    void toLogin(ActionEvent event) throws IOException {
        Stage stage = (Stage)(btnLogin.getScene().getWindow());
        Pane fxmlLoader = FXMLLoader.load(getClass().getResource("hello-view.fxml"));
        stage.setScene(new Scene(fxmlLoader,700,400));
        stage.show();
        System.out.println("Login page is opened");
    }
}

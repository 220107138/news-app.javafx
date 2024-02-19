module com.example.project2new {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.project2new to javafx.fxml;
    exports com.example.project2new;
}
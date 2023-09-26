module com.example.cinemaapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires javafx.web;


    opens com.example.cinemaapp to javafx.fxml;
    exports com.example.cinemaapp;
}
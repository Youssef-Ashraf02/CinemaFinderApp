package com.example.cinemaapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class MoviesForm
{
    @FXML
    private Button book1;

    @FXML
    private Button book2;

    @FXML
    private Button book3;

    @FXML
    private Button book4;

    Cinema cinema;
    public void setcinema(Cinema cinema)
    {
        this.cinema =cinema;
    }
    @FXML
    public void bookbutton(ActionEvent actionEvent) throws IOException, ClassNotFoundException {

        System.out.println(cinema);
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("booking_form.fxml"));
        Object root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene((Parent) root);

        BookingForm bookingForm = fxmlLoader.getController();
        bookingForm.setcinema(cinema);

        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void backbutton(ActionEvent actionEvent) throws  IOException, ClassNotFoundException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("cinemas_form.fxml"));
        Object root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();
    }


}

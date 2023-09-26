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
import java.util.EventObject;

public class ChooseForm
{
    @FXML
    private Button pressDistrict;

    @FXML
    private Button pressLocation;

    @FXML
    public void pressDistrictButton(ActionEvent actionEvent) throws IOException, ClassNotFoundException {
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


    @FXML
    public void pressLocationButton(ActionEvent actionEvent) throws IOException, ClassNotFoundException {

        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("location_form.fxml"));
        Object root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene((Parent) root);
        stage.setScene(scene);
        stage.show();

    }

}

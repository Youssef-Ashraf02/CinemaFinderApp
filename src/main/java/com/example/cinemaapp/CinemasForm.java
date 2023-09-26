package com.example.cinemaapp;

import javafx.event.ActionEvent;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class CinemasForm
{
    //String []districts = {"Gleem","Mostafa Kamel","Roshdy","DownTown","Smouha"};
    ArrayList<Cinema> cinema = Cinema.getCinema();

    @FXML
    private FlowPane flowpane1;

    @FXML
    private Button viewbutton;

    @FXML
    private TextField text;

    @FXML
    private ComboBox<String> comboBox = new ComboBox<>();

    Cinema cima;


    public CinemasForm() throws SQLException, ClassNotFoundException
    {
    }

    @FXML
    void initialize()
    {
        for(int i = 0;i<cinema.size();i++)
        {
            comboBox.getItems().add(cinema.get(i).getCinemaLocation());
        }
        comboBox.setOnAction(event -> {
            try {
                cima = Cinema.getName(comboBox.getSelectionModel().getSelectedItem());
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }

            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("viewcinema_form.fxml"));

            AnchorPane anchorPane = null;
            try {
                anchorPane = fxmlLoader.load();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            Viewcinema viewcinema = fxmlLoader.getController();
            viewcinema.setcinema(cima);
            viewcinema.showdata();
            flowpane1.getChildren().clear();
            flowpane1.getChildren().add(anchorPane);



        });
    }

    @FXML
    public void setViewbutton(ActionEvent actionEvent) throws  IOException, ClassNotFoundException {

            if ((comboBox.getSelectionModel().getSelectedItem()==null)){
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setHeaderText(null);
                Label contentLabel = new Label("Please choose district.");
                VBox content = new VBox(contentLabel);
                dialog.getDialogPane().setContent(content);
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                dialog.showAndWait();
                return;
            }


        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("Movies_form.fxml"));
        Object root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene((Parent) root);

        MoviesForm moviesForm = fxmlLoader.getController();
        moviesForm.setcinema(cima);

        stage.setScene(scene);
        stage.show();

    }
}

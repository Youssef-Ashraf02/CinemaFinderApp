package com.example.cinemaapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.scene.control.ListView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class Location implements Initializable
{
    @FXML
    private Button button;

    @FXML
    private TextField latitudeText;

    @FXML
    private TextField longitudeText;
    @FXML
    private WebView webview;

    @FXML
    private  ListView<String> listview;

   private WebEngine webEngine;

    ArrayList<Cinema> cinema;


    @FXML
    void initialize() {
        button.setOnAction(event -> {
            try {
                double latitude = Double.parseDouble(latitudeText.getText());
                double longitude = Double.parseDouble(longitudeText.getText());

                cinema = Cinema.getcinemawithdistance(latitude, longitude, listview);



                Stage secondStage = new Stage();
                FXMLLoader loader = new FXMLLoader(getClass().getResource("nearestCinema_form.fxml"));
                Parent root = loader.load();
                NearestCinemaForm nearestCinemaForm = loader.getController();
                nearestCinemaForm.setDataItems(cinema);
                Scene secondScene = new Scene(root);
                secondStage.setScene(secondScene);
                secondStage.show();

            } catch (NumberFormatException e) {
                System.err.println("Invalid latitude or longitude input.");
            } catch (SQLException | ClassNotFoundException e) {
                e.printStackTrace();
                System.err.println("An error occurred while fetching cinema data.");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        webEngine = webview.getEngine();
        loadpage();
    }
    public void loadpage()
    {
        webEngine.load("https://www.google.com/maps");
    }

}

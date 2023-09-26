package com.example.cinemaapp;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

public class Viewcinema
{
    @FXML
    private ImageView image;

    @FXML
    private Label label;
    Cinema cinema;

    public Viewcinema() throws SQLException, ClassNotFoundException {
    }

    public void setcinema(Cinema cinema)
    {
        this.cinema =cinema;
    }
    public void showdata()
    {
            label.setText(cinema.getCinemaName());
            image.setImage(new Image("C:\\Users\\Elnada\\IdeaProjects\\cinemaApp\\src\\main\\resources\\images\\" + cinema.getCinemaName() + ".jpg"));

    }

}

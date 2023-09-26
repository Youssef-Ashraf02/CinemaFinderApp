package com.example.cinemaapp;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.sql.SQLException;

public class Viewcinema2Form
{
    @FXML
    private ImageView image;

    @FXML
    private Label label;
    Cinema cinema;

    public Viewcinema2Form() throws SQLException, ClassNotFoundException {
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

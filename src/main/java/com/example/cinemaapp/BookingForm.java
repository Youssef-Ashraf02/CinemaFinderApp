package com.example.cinemaapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.util.Arrays;

public class BookingForm {

    @FXML
    private Button checkoutbutton;

    String[] itemNames = {"A", "B", "C", "D", "E", "F", "G", "H"};
    int[] itemPrices = {80, 80, 80,100,100,100,120,120};

    Integer[] seatnumber = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};

    @FXML
    ComboBox<String> comboBox = new ComboBox<>();
    @FXML
    ComboBox<Integer> comboBox2 = new ComboBox<>();

    int selectedItemPrice ;
    String selectedName ;
    int selectedSeats;

    Cinema cinema;
    public void setcinema(Cinema cinema)
    {
        this.cinema =cinema;
    }


    @FXML
    void initialize() {
        comboBox.getItems().addAll(itemNames);
        comboBox2.getItems().addAll(seatnumber);
         comboBox.setOnAction(event -> {
             selectedName =comboBox.getSelectionModel().getSelectedItem();
             for (int i = 0; i < itemNames.length; i++) {
                 if (selectedName.equals(itemNames[i])) {
                     selectedItemPrice = itemPrices[i];
                     break;
                 }
             }
             Item selectedItem = new Item(selectedName, selectedItemPrice);


             Dialog<ButtonType> dialog = new Dialog<>();
             dialog.setHeaderText(null);

             Label contentLabel = new Label("Selected seat : " + selectedItem.getName()+"\n"+" price : "+ selectedItem.getPrice());

             VBox content = new VBox(contentLabel);
             dialog.getDialogPane().setContent(content);

             dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

             dialog.showAndWait();
        });
         comboBox2.setOnAction(event -> {
             selectedSeats = ((comboBox2.getSelectionModel().getSelectedItem()));
             Dialog<ButtonType> dialog = new Dialog<>();
             dialog.setHeaderText(null);

             Label contentLabel = new Label("you will buy  : " + selectedSeats + " tickets");

             VBox content = new VBox(contentLabel);
             dialog.getDialogPane().setContent(content);

             dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

             dialog.showAndWait();
         });
        checkoutbutton.setOnAction(event -> {
            selectedName =comboBox.getSelectionModel().getSelectedItem();
            for (int i = 0; i < itemNames.length; i++) {
                if (selectedName.equals(itemNames[i])) {
                    selectedItemPrice = itemPrices[i];
                    break;
                }
            }
            Item selectedItem = new Item(selectedName, selectedItemPrice);
            selectedSeats = ((comboBox2.getSelectionModel().getSelectedItem()));

            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setHeaderText(null);

            Label contentLabel = new Label("your will pay : " + selectedSeats * selectedItem.getPrice());

            VBox content = new VBox(contentLabel);
            dialog.getDialogPane().setContent(content);

            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

            dialog.showAndWait();

            seats seat = new seats() ;


            try {
                System.out.println("remaining seats are :"+ seat.bookingseatss(selectedSeats, cinema.getCinemaName()));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}

package com.example.cinemaapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;


public class signinform {


    @FXML
    private PasswordField passwordtext;
    @FXML
    private CheckBox checkbox;

    @FXML
    private Button signInbutton;

    @FXML
    private Button signUpbutton;

    @FXML
    private TextField usernametext;

    public Parent root;
    @FXML
    public void Signupbutton(ActionEvent actionEvent) throws  IOException, ClassNotFoundException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signup_form.fxml"));
        root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    public void initialize() {
        checkbox.setOnAction(event -> {
            if (checkbox.isSelected()) {
                passwordtext.setPromptText(passwordtext.getText());
                passwordtext.setText("");
                passwordtext.setDisable(true);
            } else {
                passwordtext.setText(passwordtext.getPromptText());
                passwordtext.setPromptText("");
                passwordtext.setDisable(false);
            }
        });
    }

    @FXML
    public void Signinbutton(ActionEvent actionEvent) throws IOException, ClassNotFoundException, SQLException
    {
        String username = usernametext.getText();
        String password = passwordtext.getText();

        if (username.isEmpty() || password.isEmpty()) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setHeaderText(null);
            Label contentLabel = new Label("Please enter both username and password.");
            VBox content = new VBox(contentLabel);
            dialog.getDialogPane().setContent(content);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.showAndWait();
            return;
        }
            Database db = Database.getInstance();
            User check = User.logincheck(usernametext.getText(),passwordtext.getText());
            if(check!=null )
            {
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setHeaderText(null);
                Label contentLabel = new Label("Welcome" );
                VBox content = new VBox(contentLabel);
                dialog.getDialogPane().setContent(content);
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                dialog.showAndWait();

                Node n = (Node) actionEvent.getSource();
                Stage closeWindow = (Stage) n.getScene().getWindow();
                closeWindow.close();
                FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("choose_form.fxml"));
                root = fxmlLoader.load();
                Stage stage = new Stage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            }
            else
            {
                Dialog<ButtonType> dialog = new Dialog<>();
                dialog.setHeaderText(null);
                Label contentLabel = new Label("user not found" );
                VBox content = new VBox(contentLabel);
                dialog.getDialogPane().setContent(content);
                dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
                dialog.showAndWait();
            }

        }


    }

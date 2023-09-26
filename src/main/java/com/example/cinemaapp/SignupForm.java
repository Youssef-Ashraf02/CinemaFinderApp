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


public class SignupForm
{
    @FXML
    private Button backbuttonUp;

    @FXML
    private ComboBox<String> combobox;

    @FXML
    private TextField emailtext;

    @FXML
    private TextField passwordtext1;

    @FXML
    private Button signupbutton;

    @FXML
    private TextField usernametext1;
    public Parent root;

    @FXML
    public void backbutton(ActionEvent actionEvent) throws  IOException, ClassNotFoundException {
        Node n = (Node) actionEvent.getSource();
        Stage closeWindow = (Stage) n.getScene().getWindow();
        closeWindow.close();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signin_form.fxml"));
        root = fxmlLoader.load();
        Stage stage = new Stage();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void initialize() {   ///combobox
        combobox.getItems().addAll("Male","Female");
    }

    @FXML
    public void Signupbutton(ActionEvent actionEvent) throws IOException, ClassNotFoundException, SQLException {

        String username = usernametext1.getText();
        String password = passwordtext1.getText();
        String email = emailtext.getText();
        String comboboxValue =combobox.getValue();

        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || comboboxValue.isEmpty()) {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setHeaderText(null);
            Label contentLabel = new Label("Please fill the missing data.");
            VBox content = new VBox(contentLabel);
            dialog.getDialogPane().setContent(content);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.showAndWait();
            return;
        }
        Database db = Database.getInstance();
        try
        {
            User.signupcheck(usernametext1.getText(), passwordtext1.getText(),emailtext.getText(),combobox.getValue());
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setHeaderText(null);

            Label contentLabel = new Label("Registration done" );

            VBox content = new VBox(contentLabel);
            dialog.getDialogPane().setContent(content);

            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);

            dialog.showAndWait();
            Node n = (Node) actionEvent.getSource();
            Stage closeWindow = (Stage) n.getScene().getWindow();
            closeWindow.close();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("signin_form.fxml"));
            root = fxmlLoader.load();
            Stage stage = new Stage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        catch (IllegalArgumentException e)
        {
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setHeaderText(null);
            Label contentLabel = new Label("Username is already taken.");
            VBox content = new VBox(contentLabel);
            dialog.getDialogPane().setContent(content);
            dialog.getDialogPane().getButtonTypes().add(ButtonType.CLOSE);
            dialog.showAndWait();
        }

    }

}

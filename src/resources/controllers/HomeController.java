package resources.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    public static String templateLink;

    @FXML
    ImageView exit;

    @FXML
    private void confirmBeforeExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm before exit");
        alert.setHeaderText("Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }

    @FXML
    private void moveToList() {
        templateLink = "../templates/dynamic-array.fxml";
        moveToMain();
    }

    @FXML
    private void moveToStack() {
        templateLink = "../templates/stack.fxml";
        moveToMain();
    }

    @FXML
    private void moveToQueue() {
        templateLink  = "../templates/queue.fxml";
        moveToMain();
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exit.setOnMouseClicked(event -> {
            confirmBeforeExit();
        });
    }

    private void moveToMain() {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../templates/main.fxml"));
            Stage stage = (Stage) exit.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


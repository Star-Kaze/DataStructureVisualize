package resources.controllers;

import graphics.collections.dynamic.array.DynamicArrayDisplay;
import graphics.collections.queue.QueueDisplay;
import graphics.collections.stack.StackDisplay;
import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private StackPane content;

    @FXML
    private ImageView exit, menu;

    @FXML
    private AnchorPane darkTheme, options;

    @FXML
    private void home() {
        this.closeMenu();
        this.setAllExistedToFalse();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("../templates/home.fxml"));
            Stage stage = (Stage) exit.getScene().getWindow();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void list() {
        this.closeMenu();
        this.setAllExistedToFalse();
        try {
            Parent template = FXMLLoader.load(getClass().getResource("../templates/dynamic-array.fxml"));
            content.getChildren().removeAll();
            content.getChildren().setAll(template);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    @FXML
    private void stack() {
        this.closeMenu();
        this.setAllExistedToFalse();
        try {
            Parent template = FXMLLoader.load(getClass().getResource("../templates/stack.fxml"));
            content.getChildren().removeAll();
            content.getChildren().setAll(template);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    @FXML
    private void queue() {
        this.closeMenu();
        this.setAllExistedToFalse();
        try {
            Parent template = FXMLLoader.load(getClass().getResource("../templates/queue.fxml"));
            content.getChildren().removeAll();
            content.getChildren().setAll(template);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exit.setOnMouseClicked(event -> {
            confirmBeforeExit();
        });

        try {
            Parent template = FXMLLoader.load(getClass().getResource(HomeController.templateLink));
            content.getChildren().removeAll();
            content.getChildren().setAll(template);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }

        darkTheme.setVisible(false);

        FadeTransition fadeTransitionOfDarkTheme1 = new FadeTransition(Duration.seconds(0.5), darkTheme);
        fadeTransitionOfDarkTheme1.setFromValue(1);
        fadeTransitionOfDarkTheme1.setToValue(0);
        fadeTransitionOfDarkTheme1.play();

        TranslateTransition translateTransitionOfOptions1 = new TranslateTransition(Duration.seconds(0.5), options);
        translateTransitionOfOptions1.setByX(-600);
        translateTransitionOfOptions1.play();

        menu.setOnMouseClicked(event -> {
            darkTheme.setVisible(true);

            FadeTransition fadeTransitionOfDarkTheme2 = new FadeTransition(Duration.seconds(0.5), darkTheme);
            fadeTransitionOfDarkTheme2.setFromValue(0);
            fadeTransitionOfDarkTheme2.setToValue(0.15);
            fadeTransitionOfDarkTheme2.play();

            TranslateTransition translateTransitionOfOptions2 = new TranslateTransition(Duration.seconds(0.5), options);
            translateTransitionOfOptions2.setByX(+600);
            translateTransitionOfOptions2.play();
        });

        darkTheme.setOnMouseClicked(event1 -> {
            this.closeMenu();
        });
    }

    private void closeMenu() {
        FadeTransition fadeTransitionOfDarkTheme2 = new FadeTransition(Duration.seconds(0.5), darkTheme);
        fadeTransitionOfDarkTheme2.setFromValue(0.15);
        fadeTransitionOfDarkTheme2.setToValue(0);
        fadeTransitionOfDarkTheme2.play();

        fadeTransitionOfDarkTheme2.setOnFinished(event2 -> {
            darkTheme.setVisible(false);
        });

        TranslateTransition translateTransitionOfOptions2 = new TranslateTransition(Duration.seconds(0.5), options);
        translateTransitionOfOptions2.setByX(-600);
        translateTransitionOfOptions2.play();
    }

    private void setAllExistedToFalse() {
        DynamicArrayDisplay.Existed = false;
        StackDisplay.Existed = false;
        QueueDisplay.Existed = false;
    }

    private void confirmBeforeExit() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm before exit");
        alert.setHeaderText("Are you sure you want to exit?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            System.exit(0);
        }
    }
}

package resources.controllers;

import javafx.animation.FadeTransition;
import javafx.animation.TranslateTransition;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private ImageView exit, menu;

    @FXML
    private AnchorPane darkTheme, options;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        exit.setOnMouseClicked(event -> {
            System.exit(0);
        });

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
        });
    }
}

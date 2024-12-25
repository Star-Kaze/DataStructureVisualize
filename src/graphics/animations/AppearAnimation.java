package graphics.animations;

import graphics.graphical.elements.GraphicalNumber;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class AppearAnimation {
    public static ParallelTransition create(GraphicalNumber node, double deltaX, double deltaY, double duration) {
        node.getContainer().setOpacity(0);
        node.getLabel().setStyle("-fx-font-size: " + (node.getFrame().getHeight() / 3) + "px; "
                               + "-fx-text-fill: white; "
                               + "-fx-alignment: center;");
        node.getFrame().setFill(Color.GREEN);
        node.getFrame().setStroke(Color.TRANSPARENT);

        // Create fade-in transition
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(duration), node.getContainer());
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(1);

        // Create translate transition
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node.getContainer());
        translateTransition.setByX(deltaX);
        translateTransition.setByY(deltaY);

        // Create parallel transition
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(fadeTransition, translateTransition);

        return parallelTransition;
    }

    public static ParallelTransition createDisappear(GraphicalNumber node, double deltaX, double deltaY, double duration) {
        // Create fade-in transition
        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(duration), node.getContainer());
        fadeTransition.setFromValue(1);
        fadeTransition.setToValue(0);

        // Create translate transition
        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(duration), node.getContainer());
        translateTransition.setByX(deltaX);
        translateTransition.setByY(deltaY);

        // Create parallel transition
        ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren().addAll(fadeTransition, translateTransition);

        return parallelTransition;
    }
}

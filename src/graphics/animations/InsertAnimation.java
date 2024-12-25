package graphics.animations;

import graphics.graphical.elements.GraphicalNumber;
import javafx.animation.*;
import javafx.util.Duration;

public class InsertAnimation {
    public static SequentialTransition create(GraphicalNumber node, int value) {
        Timeline selected = SelectionAnimation.create(node, 0.1);

        KeyValue keyValue = new KeyValue(node.getLabel().textProperty(), Integer.toString(value));
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.3), keyValue);

        Timeline updated = new Timeline(keyFrame);

        PauseTransition pause = new PauseTransition(Duration.seconds(0.3));

        Timeline unselected = SelectionAnimation.createUnselected(node, 0.1);

        return new SequentialTransition(selected, updated, pause, unselected);
    }

    public static SequentialTransition createUpdated(GraphicalNumber node, int value) {
        Timeline selected = SelectionAnimation.create(node, 0.1);

        KeyValue keyValue1 = new KeyValue(node.getLabel().textProperty(), "");
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.3), keyValue1);

        KeyValue keyValue2 = new KeyValue(node.getLabel().textProperty(), Integer.toString(value));
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.6), keyValue2);

        Timeline updated = new Timeline(keyFrame1, keyFrame2);

        PauseTransition pause = new PauseTransition(Duration.seconds(0.3));

        Timeline unselected = SelectionAnimation.createUnselected(node, 0.1);

        return new SequentialTransition(selected, updated, pause, unselected);
    }
}

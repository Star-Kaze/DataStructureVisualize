package graphics.animations;

import graphics.graphical.elements.GraphicalNumber;
import javafx.animation.*;
import javafx.util.Duration;

public class UpdatedAnimation {
    public static SequentialTransition create(GraphicalNumber node, int value) {
        Timeline selected = SelectionAnimation.create(node, 0.1);

        KeyValue keyValue = new KeyValue(node.getLabel().textProperty(), Integer.toString(value));
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.3), keyValue);

        Timeline updated = new Timeline(keyFrame);

        PauseTransition pause = new PauseTransition(Duration.seconds(0.3));

        Timeline unselected = SelectionAnimation.createUnselected(node, 0.1);

        return new SequentialTransition(selected, updated, pause, unselected);
    }
}

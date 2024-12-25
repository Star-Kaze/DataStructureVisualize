package graphics.animations;

import graphics.graphical.elements.GraphicalNumber;
import javafx.animation.*;
import javafx.util.Duration;

public class ShiftAnimation {
    public static SequentialTransition create(GraphicalNumber from, GraphicalNumber to) {
        String text1 = from.getLabel().getText();
        String text2 = "";

        Timeline selected1 = SelectionAnimation.create(from, 0.1);
        Timeline selected2 = SelectionAnimation.create(to, 0.1);

        ParallelTransition selected = new ParallelTransition(selected1, selected2);

        KeyValue keyValue1 = new KeyValue(from.getLabel().textProperty(), text2);
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.3), keyValue1);

        KeyValue keyValue2 = new KeyValue(to.getLabel().textProperty(), text1);
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.3), keyValue2);

        Timeline shift = new Timeline();
        shift.getKeyFrames().addAll(keyFrame1, keyFrame2);

        PauseTransition pause = new PauseTransition(Duration.seconds(0.3));

        Timeline unselected1 = SelectionAnimation.createUnselected(from, 0.1);
        Timeline unselected2 = SelectionAnimation.createUnselected(to, 0.1);

        ParallelTransition unselected = new ParallelTransition(unselected1, unselected2);

        return new SequentialTransition(selected, shift, pause, unselected);
    }
}

package graphics.animations;

import graphics.graphical.elements.GraphicalNumber;
import javafx.animation.*;
import javafx.util.Duration;

public class SwapAnimation {
    public static SequentialTransition create(GraphicalNumber node1, GraphicalNumber node2) {
        String text1 = node1.getLabel().getText();
        String text2 = node2.getLabel().getText();

        Timeline selected1 = SelectionAnimation.create(node1, 0.1);
        Timeline selected2 = SelectionAnimation.create(node2, 0.1);

        ParallelTransition selected = new ParallelTransition(selected1, selected2);

        KeyValue keyValue1 = new KeyValue(node1.getLabel().textProperty(), text2);
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.1), keyValue1);

        KeyValue keyValue2 = new KeyValue(node2.getLabel().textProperty(), text1);
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.1), keyValue2);

        Timeline swap = new Timeline();
        swap.getKeyFrames().addAll(keyFrame1, keyFrame2);

        PauseTransition pause = new PauseTransition(Duration.seconds(0.3));

        Timeline unselected1 = SelectionAnimation.createUnselected(node1, 0.1);
        Timeline unselected2 = SelectionAnimation.createUnselected(node2, 0.1);

        ParallelTransition unselected = new ParallelTransition(unselected1, unselected2);

        return new SequentialTransition(selected, swap, pause, unselected);
    }

    public static SequentialTransition create(GraphicalNumber node1, GraphicalNumber node2, int value1, int value2) {
        String text1 = Integer.toString(value2);
        String text2 = Integer.toString(value1);

        Timeline selected1 = SelectionAnimation.create(node1, 0.1);
        Timeline selected2 = SelectionAnimation.create(node2, 0.1);

        ParallelTransition selected = new ParallelTransition(selected1, selected2);

        KeyValue keyValue1 = new KeyValue(node1.getLabel().textProperty(), text2);
        KeyFrame keyFrame1 = new KeyFrame(Duration.seconds(0.1), keyValue1);

        KeyValue keyValue2 = new KeyValue(node2.getLabel().textProperty(), text1);
        KeyFrame keyFrame2 = new KeyFrame(Duration.seconds(0.1), keyValue2);

        Timeline swap = new Timeline();
        swap.getKeyFrames().addAll(keyFrame1, keyFrame2);

        PauseTransition pause = new PauseTransition(Duration.seconds(0.3));

        Timeline unselected1 = SelectionAnimation.createUnselected(node1, 0.1);
        Timeline unselected2 = SelectionAnimation.createUnselected(node2, 0.1);

        ParallelTransition unselected = new ParallelTransition(unselected1, unselected2);

        return new SequentialTransition(selected, swap, pause, unselected);
    }
}

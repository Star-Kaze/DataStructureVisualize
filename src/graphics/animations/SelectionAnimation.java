package graphics.animations;

import graphics.graphical.elements.GraphicalNumber;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class SelectionAnimation {
    public static Timeline create(GraphicalNumber node, double duration) {
        // Create key frames
        KeyValue textFill = new KeyValue(node.getLabel().textFillProperty(), Color.WHITE);
        KeyFrame frame1 = new KeyFrame(Duration.seconds(duration), textFill);

        KeyValue backGround = new KeyValue(node.getFrame().fillProperty(), Color.ORANGE);
        KeyFrame frame2 = new KeyFrame(Duration.seconds(duration), backGround);

        KeyValue stroke = new KeyValue(node.getFrame().strokeProperty(), Color.TRANSPARENT);
        KeyFrame frame3 = new KeyFrame(Duration.seconds(duration), stroke);

        // Create timeline
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(frame1, frame2, frame3);

        return timeline;
    }

    public static Timeline createUnselected(GraphicalNumber node, double duration) {
        // Create key frames
        KeyValue textFill = new KeyValue(node.getLabel().textFillProperty(), Color.BLACK);
        KeyFrame frame1 = new KeyFrame(Duration.seconds(duration), textFill);

        KeyValue backGround = new KeyValue(node.getFrame().fillProperty(), Color.TRANSPARENT);
        KeyFrame frame2 = new KeyFrame(Duration.seconds(duration), backGround);

        KeyValue stroke = new KeyValue(node.getFrame().strokeProperty(), Color.BLACK);
        KeyFrame frame3 = new KeyFrame(Duration.seconds(duration), stroke);

        // Create timeline
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(frame1, frame2, frame3);

        return timeline;
    }
}

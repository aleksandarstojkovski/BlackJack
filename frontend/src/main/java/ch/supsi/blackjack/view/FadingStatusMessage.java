package ch.supsi.blackjack.view;

import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class FadingStatusMessage {
    public static void flash(Node node, String message) {
        final Rectangle rectangle = new Rectangle();
        final Text text = new Text(message);

        Scene scene = node.getScene();
        final Pane pane = (Pane)scene.getRoot();
        final ObservableList<Node> observableList = pane.getChildren();
        observableList.add(rectangle);
        observableList.add(text);

        customizeControls(rectangle, text, scene);
        fadeControls(rectangle, text, observableList);
    }

    private static void fadeControls(Rectangle rectangle, Text text, ObservableList<Node> observableList) {
        double duration = 3000;
        FadeTransition ft = new FadeTransition(Duration.millis(duration), rectangle);
        ft.setFromValue(1.0);
        ft.setToValue(0.0);
        ft.play();
        ft.setOnFinished(event -> {
            observableList.remove(rectangle);
            observableList.remove(text);
        });

        fadeText(text, duration);
    }

    private static void fadeText(Text text, double duration) {
        FadeTransition ft2 = new FadeTransition(Duration.millis(duration + (duration * .1)), text);
        ft2.setFromValue(1.0);
        ft2.setToValue(0.0);
        ft2.play();
    }

    private static void customizeControls(Rectangle rectangle, Text text, Scene scene) {
        // Text customization
        Font font = Font.font("Verdana", FontWeight.BOLD, 30);
        Color textColor = Color.WHITE;
        text.setLayoutX(0);
        text.setLayoutY(0);
        text.setFont(font);
        text.setFill(textColor);

        Bounds bounds = text.getBoundsInParent();
        double sWidth = scene.getWidth();
        double sHeight = scene.getHeight();
        double x = sWidth / 2 - (bounds.getWidth() / 2);
        double y = sHeight / 2 - (bounds.getHeight() / 2);
        text.setLayoutX(x);
        text.setLayoutY(y);

        bounds = text.getBoundsInParent();
        double baseLineOffset = text.getBaselineOffset();

        // Rectangle customization
        Color boxColor = Color.color(0,0,0,0.6);
        double arcH = 5;
        double arcW = 5;
        rectangle.setFill(boxColor);
        rectangle.setLayoutX(x - arcW);
        rectangle.setLayoutY(y - baseLineOffset - arcH);
        rectangle.setArcHeight(arcH);
        rectangle.setArcWidth(arcW);
        rectangle.setWidth(bounds.getWidth() + arcW * 2);
        rectangle.setHeight(bounds.getHeight() + arcH * 2);
    }

}
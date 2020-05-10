package ch.supsi.blackjack;

import ch.supsi.blackjack.controller.ContentAreaController;
import ch.supsi.blackjack.controller.LogController;
import ch.supsi.blackjack.controller.MenuController;
import ch.supsi.blackjack.model.AbstractModel;
import ch.supsi.blackjack.model.Model;
import ch.supsi.blackjack.view.ContentAreaView;
import ch.supsi.blackjack.view.LogView;
import ch.supsi.blackjack.view.MenuView;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.beans.PropertyChangeListener;
import java.lang.reflect.Constructor;
import java.util.ResourceBundle;

public class MainApp extends Application {
    public static void main(String[] args) {
        MainApp.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        ResourceBundle bundle = ResourceBundle.getBundle("ch/supsi/blackjack/bundles/blackjack");

        // MVC scaffolding
        Model model = Model.instance();

        ContentAreaView contentAreaView = ContentAreaView.create(new ContentAreaController(model), bundle);
        model.addPropertyChangeListener(contentAreaView);

        MenuView menuView = MenuView.create(new MenuController(model), bundle);
        model.addPropertyChangeListener(menuView);

        LogView logView = LogView.create(new LogController(model), bundle);
        model.addPropertyChangeListener(logView);

        setupStage(primaryStage, contentAreaView, menuView, logView);
    }

    private void setupStage(Stage primaryStage, ContentAreaView contentAreaView, MenuView menuView, LogView logView) {
        AnchorPane root = new AnchorPane();
        VBox vbox = new VBox();
        AnchorPane.setBottomAnchor(vbox, 0.0);
        AnchorPane.setTopAnchor(vbox, 0.0);
        AnchorPane.setRightAnchor(vbox, 0.0);
        AnchorPane.setLeftAnchor(vbox, 0.0);
        root.getChildren().add(vbox);

        vbox.getChildren().add(contentAreaView.getComponent());
        vbox.getChildren().add(menuView.getComponent());
        vbox.getChildren().add(logView.getComponent());

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("/ch/supsi/blackjack/css/style.css");
        Image appIcon = new Image(this.getClass().getResource("/ch/supsi/blackjack/images/spades.png").toString());
        primaryStage.setTitle("Black Jack");
        primaryStage.getIcons().add(appIcon);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

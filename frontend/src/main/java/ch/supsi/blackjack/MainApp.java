package ch.supsi.blackjack;

import ch.supsi.blackjack.controller.ContentAreaController;
import ch.supsi.blackjack.controller.LogController;
import ch.supsi.blackjack.controller.MenuController;
import ch.supsi.blackjack.model.GameModel;
import ch.supsi.blackjack.view.ContentAreaView;
import ch.supsi.blackjack.view.LogView;
import ch.supsi.blackjack.view.MenuView;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ResourceBundle;

public class MainApp extends Application {
    public static void main(String[] args) {
        MainApp.launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // resource bundle for i18n (actually only default language is provided: blackjack.properties)
        ResourceBundle bundle = ResourceBundle.getBundle("ch/supsi/blackjack/bundles/blackjack");

        // MVC scaffolding:
        // - Views listen to model notification (Observer Pattern) and interact with controller
        // - Controllers interact with a GameHandler object (model) and with the Components of the View
        // - JavaFX route user events to the controller defined in the FXML file

        // There is one single instance of Model
        GameModel gameModel = GameModel.instance();
        CommandCatalog commandCatalog = new CommandCatalog(gameModel);

        ContentAreaView contentAreaView = ContentAreaView.create(new ContentAreaController(commandCatalog), bundle);
        gameModel.addPropertyChangeListener(contentAreaView);

        MenuView menuView = MenuView.create(new MenuController(commandCatalog), bundle);
        gameModel.addPropertyChangeListener(menuView);

        LogView logView = LogView.create(new LogController(commandCatalog), bundle);
        gameModel.addPropertyChangeListener(logView);

        // add components to the primary stage and show it
        setupStage(primaryStage, contentAreaView.getComponent(), menuView.getComponent(), logView.getComponent());
    }

    private void setupStage(Stage primaryStage, Parent... components) {
        VBox vbox = new VBox();
        AnchorPane.setBottomAnchor(vbox, 0.0);
        AnchorPane.setTopAnchor(vbox, 0.0);
        AnchorPane.setRightAnchor(vbox, 0.0);
        AnchorPane.setLeftAnchor(vbox, 0.0);

        // the main page is an AnchorPane that contains a simple VBox layout
        AnchorPane root = new AnchorPane();
        root.getChildren().add(vbox);

        // VBox children are the components of the following views: ContentAreaView, MenuView, LogView
        for (Parent component : components)
            vbox.getChildren().add(component);

        Scene scene = new Scene(root, 800, 600);
        scene.getStylesheets().add("/ch/supsi/blackjack/css/style.css");
        Image appIcon = new Image(this.getClass().getResource("/ch/supsi/blackjack/images/spades.png").toString());
        primaryStage.setTitle("Black Jack");
        primaryStage.getIcons().add(appIcon);
        primaryStage.setScene(scene);
        primaryStage.show();
        primaryStage.toFront();
    }
}

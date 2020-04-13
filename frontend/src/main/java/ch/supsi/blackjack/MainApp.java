package ch.supsi.blackjack;

import ch.supsi.blackjack.model.Model;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/ch/supsi/blackjack/view/Main.fxml"), bundle);

        // MVC scaffolding
        Model model = Model.instance();
        model.setPrimaryStage(primaryStage);
//        MainController controller = new MainController(model);
        fxmlLoader.setControllerFactory((Class<?> type) -> {
            try {
                for (Constructor<?> c : type.getConstructors()) {
                    if (c.getParameterCount() == 1 && c.getParameterTypes()[0] == Model.class) {
                        PropertyChangeListener controller = (PropertyChangeListener)c.newInstance(model);
                        model.addPropertyChangeListener(controller);
                        return controller;
                    }
                }
                throw new Exception(type.toString() + " does not have proper Constructor");
            } catch (Exception exc) {
                throw new RuntimeException(exc);
            }
        });

        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        scene.getStylesheets().add("/ch/supsi/blackjack/css/style.css");

        Image appIcon = new Image(this.getClass().getResource("/ch/supsi/blackjack/images/spades.png").toString());
        primaryStage.setTitle("Black Jack");
        primaryStage.getIcons().add(appIcon);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

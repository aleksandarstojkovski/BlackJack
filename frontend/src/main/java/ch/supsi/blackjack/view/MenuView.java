package ch.supsi.blackjack.view;

import ch.supsi.blackjack.controller.MenuController;
import ch.supsi.blackjack.event.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

/**
 * The View is associated to an FXML resource for the definition of the components
 * Methods annotated with @EventHandler are executed through reflection
 */

@SuppressWarnings("SpellCheckingInspection")
public class MenuView extends AbstractView {

    private final static String FXML = "ch/supsi/blackjack/view/Menu.fxml";
    private final MenuController controller;

    // Constructor is not public, use Static Factory Method
    MenuView(MenuController controller) {
        this.controller = controller;
    }

    // Static Factory Method
    public static MenuView create(MenuController controller, ResourceBundle bundle)
            throws InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        return AbstractView.create(MenuView.class, FXML, controller, bundle);
    }

    @EventHandler
    void handleEvent(GameStartedEvent event) {
        controller.onGameStarted();
    }

    @EventHandler
    void handleEvent(GameFinishedEvent event) {
        controller.onGameFinished();
    }

    @EventHandler
    void handleEvent(NewRoundEvent event) {
        controller.onNewRound();
    }

    @EventHandler
    void handleEvent(NewBetEvent event) {
        controller.onNewBet();
    }

    @EventHandler
    void handleEvent(BetConfirmedEvent event) {
        controller.onBetConfirmed();
    }

    @EventHandler
    void handleEvent(StandEvent event) {
        controller.onStand();
    }

    @EventHandler
    void handleEvent(PlayerBustedEvent event) {
        controller.onPlayerBusted();
    }

    @EventHandler
    void handleEvent(RoundCompletedEvent event) {
        controller.onRoundCompleted();
    }

    @EventHandler
    void handleEvent(PlayerBlackjackEvent event) {
        controller.onPlayerBlackjack();
    }

}

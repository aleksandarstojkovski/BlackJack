package ch.supsi.blackjack.view;

import ch.supsi.blackjack.controller.ContentAreaController;
import ch.supsi.blackjack.event.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

/**
 * The View is associated to an FXML resource for the definition of the components
 * Methods annotated with @EventHandler are executed through reflection
 */
public class ContentAreaView  extends AbstractView {

    @SuppressWarnings("SpellCheckingInspection")
    private final static String FXML = "ch/supsi/blackjack/view/ContentArea.fxml";
    private final ContentAreaController controller;

    // Constructor is not public, use Static Factory Method
    ContentAreaView(ContentAreaController controller) {
        this.controller = controller;
    }

    // Static Factory Method
    public static ContentAreaView create(ContentAreaController controller, ResourceBundle bundle)
            throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        return AbstractView.create(ContentAreaView.class, FXML, controller, bundle);
    }

    @EventHandler
    void handleEvent(GameStartedEvent event) {
        controller.onNewGame(event);
    }

    @EventHandler
    void handleEvent(GameFinishedEvent event) {
        controller.onExitGame();
    }

    @EventHandler
    void handleEvent(NewRoundEvent event) {
        controller.onNewRound(event);
    }

    @EventHandler
    void handleEvent(NewBetEvent event) {
        controller.onNewBet(event);
    }

    @EventHandler
    void handleEvent(PlayerBustedEvent event) {
        controller.onPlayerBusted();
    }

    @EventHandler
    void handleEvent(DealerBustedEvent event) {
        controller.onDealerBusted();
    }

    @EventHandler
    void handleEvent(RoundCompletedEvent event) {
        controller.onRoundCompleted(event);
    }

    @EventHandler
    void handleEvent(PlayerBlackjackEvent event) {
        controller.onPlayerBlackjack();
    }

    @EventHandler
    void handleEvent(DealerHandUpdateEvent event) {
        controller.onDealerHand(event);
    }

    @EventHandler
    void handleEvent(PlayerHandUpdateEvent event) {
        controller.onPlayerHand(event);
    }

    @EventHandler
    void handleEvent(GameOverEvent event) {
        controller.onGameOver();
    }

    @EventHandler
    void handleEvent(DealerCompletedEvent event) {
        controller.onDealerCompleted();
    }

}

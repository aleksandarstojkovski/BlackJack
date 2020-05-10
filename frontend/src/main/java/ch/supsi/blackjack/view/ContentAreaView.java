package ch.supsi.blackjack.view;

import ch.supsi.blackjack.controller.ContentAreaController;
import ch.supsi.blackjack.event.*;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class ContentAreaView  extends AbstractView {
    private final ContentAreaController controller;

    public ContentAreaView(ContentAreaController controller) {
        this.controller = controller;
    }

    public static ContentAreaView create(ContentAreaController controller, ResourceBundle bundle) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        return AbstractView.create(ContentAreaView.class, "/ch/supsi/blackjack/view/ContentArea.fxml", controller, bundle);
    }

    @EventHandler
    void handleEvent(GameStartedEvent event) {
        controller.handleNewGame(event);
    }

    @EventHandler
    void handleEvent(GameFinishedEvent event) {
        controller.handleExitGame(event);
    }

    @EventHandler
    void handleEvent(NewRoundEvent event) {
        controller.handleNewRound(event);
    }

    @EventHandler
    void handleEvent(NewBetEvent event) {
        controller.handleNewBet(event);
    }

    @EventHandler
    void handleEvent(PlayerBustedEvent event) {
        controller.handlePlayerBusted(event);
    }

    @EventHandler
    void handleEvent(DealerBustedEvent event) {
        controller.handleDealerBusted(event);
    }

    @EventHandler
    void handleEvent(RoundCompletedEvent event) {
        controller.handleRoundCompleted(event);
    }

    @EventHandler
    void handleEvent(PlayerBlackjackEvent event) {
        controller.handlePlayerBlackjack(event);
    }

    @EventHandler
    void handleEvent(NewCardEvent event) {
        controller.handleNewCard(event);
    }

    @EventHandler
    void handleEvent(DealerHandUpdateEvent event) {
        controller.handleDealerHand(event);
    }

    @EventHandler
    void handleEvent(PlayerHandUpdateEvent event) {
        controller.handlePlayerHand(event);
    }

    @EventHandler
    void handleEvent(GameOverEvent event) {
        controller.handleGameOver();
    }

    @EventHandler
    void handleEvent(DealerStartEvent event) {
        controller.showCoveredCard(event);
    }

}

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

    @Override
    public void propertyChange(PropertyChangeEvent event) {

        if (event instanceof NewCardEvent) {
            controller.handleNewCard((NewCardEvent) event);
        } else if (event instanceof DealerHandUpdateEvent) {
            controller.handleDealerHand((DealerHandUpdateEvent) event);
        } else if (event instanceof PlayerHandUpdateEvent) {
            controller.handlePlayerHand((PlayerHandUpdateEvent) event);
        } else if (event instanceof GameStartedEvent) {
            controller.handleNewGame((GameStartedEvent) event);
        } else if (event instanceof GameFinishedEvent){
            controller.handleExitGame((GameFinishedEvent) event);
        } else if (event instanceof NewBetEvent) {
            controller.handleNewBet((NewBetEvent) event);
        } else if (event instanceof NewRoundEvent){
            controller.handleNewRound((NewRoundEvent) event);
        } else if (event instanceof PlayerBustedEvent){
            controller.handlePlayerBusted((PlayerBustedEvent) event);
        } else if (event instanceof PlayerBlackjackEvent){
            controller.handlePlayerBlackjack((PlayerBlackjackEvent) event);
        } else if (event instanceof RoundCompletedEvent){
            controller.handleRoundCompleted((RoundCompletedEvent) event);
        } else if (event instanceof DealerBustedEvent){
            controller.handleDealerBusted((DealerBustedEvent) event);
        } else if (event instanceof GameOverEvent) {
            controller.handleGameOver();
        } else if (event instanceof DealerStartEvent) {
            controller.showCoveredCard((DealerStartEvent) event);
        }

    }
}

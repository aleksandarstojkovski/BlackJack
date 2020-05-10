package ch.supsi.blackjack.view;

import ch.supsi.blackjack.controller.MenuController;
import ch.supsi.blackjack.event.*;

import java.beans.PropertyChangeEvent;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ResourceBundle;

public class MenuView extends AbstractView {
    private final MenuController controller;

    MenuView(MenuController controller) {
        this.controller = controller;
    }

    public static MenuView create(MenuController controller, ResourceBundle bundle) throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException, IOException {
        return AbstractView.create(MenuView.class, "/ch/supsi/blackjack/view/Menu.fxml", controller, bundle);
    }

    @Override
    public void propertyChange(PropertyChangeEvent event) {
        if (event instanceof GameStartedEvent) {
            controller.onGameStarted();
        } else if (event instanceof GameFinishedEvent) {
            controller.onGameFinished();
        } else if (event instanceof NewRoundEvent) {
            controller.onNewRound();
        } else if (event instanceof NewBetEvent) {
            controller.onNewBet();
        } else if (event instanceof BetConfirmedEvent) {
            controller.onBetConfirmed();
        } else if (event instanceof StandEvent) {
            controller.onStand();
        } else if (event instanceof PlayerBustedEvent) {
            controller.onPlayerBusted();
        } else if (event instanceof RoundCompletedEvent) {
            controller.onRoundCompleted();
        } else if (event instanceof PlayerBlackjackEvent) {
            controller.onPlayerBlackjack();
        }
    }
}

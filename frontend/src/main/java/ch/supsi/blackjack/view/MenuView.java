package ch.supsi.blackjack.view;

import ch.supsi.blackjack.controller.MenuController;
import ch.supsi.blackjack.event.*;

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

    @EventHandler
    void handleEvent(GameStartedEvent event) {
        controller.onGameStarted();
    }

    @EventHandler
    void handleEvent(GameFinishedEvent event) {
        controller.handleGameFinished();
    }

    @EventHandler
    void handleEvent(NewRoundEvent event) {
        controller.handleNewRound();
    }

    @EventHandler
    void handleEvent(NewBetEvent event) {
        controller.handleNewBet();
    }

    @EventHandler
    void handleEvent(BetConfirmedEvent event) {
        controller.handleBetConfirmed();
    }

    @EventHandler
    void handleEvent(StandEvent event) {
        controller.handleStand();
    }

    @EventHandler
    void handleEvent(PlayerBustedEvent event) {
        controller.handlePlayerBusted();
    }

    @EventHandler
    void handleEvent(RoundCompletedEvent event) {
        controller.handleRoundCompleted();
    }

    @EventHandler
    void handleEvent(PlayerBlackjackEvent event) {
        controller.handlePlayerBlackjack();
    }

}

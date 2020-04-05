package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

/*

Descrizione: il dealer provvede a dare le carte al giocatore, e a sé stesso.

Cosa vede l’utente:
•	Tutti i bottoni sono disabilitati eccetto <exit game>.
•	Viene mostrata un’animazione del dealer che dà le carte a tutti i giocatori.

Cosa deve accadere:
•	Il Player riceve due carte (tutte e due scoperte).
•	Il dealer riceve due carte (una coperta ed una scoperta)

*/

public class SetupTableState implements GameState {

        // singleton
        private static SetupTableState instance = new SetupTableState();

        private SetupTableState() {
        }

        public static SetupTableState instance() {
            System.out.println("Current state: " +instance.getClass().toString());
            return instance;
        }

        // business logic and state transition
        @Override
        public void updateState(Model model) {
            // TODO:
            // if (21){
            //  TwentyOneState
            // } else {
            //  PlayerDealsState
            // }
            model.setCurrentState(BetState.instance());

        }

}

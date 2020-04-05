package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;

// TODO: Creare

/*

Descrizione: il giocatore non ha ancora iniziato a giocare.

Cosa vede l’utente
•	Bottone <new game> - Iniziare una nuova partita.
•	Bottone <exit game> - Uscire completamente dal gioco

Cosa deve accadere:

In caso di <new game>:
•	Un nuovo Player deve essere creato.
•	Il nuovo utente inizia con una quantità di soldi (fiche) stabilita <1000>.
•	Aggiornamento al prossimo stato.
In caso di <exit game>:
•	Exit 0.

*/

public class TwentyOneState implements GameState {

    // singleton
    private static TwentyOneState instance = new TwentyOneState();

    private TwentyOneState() {
    }

    public static TwentyOneState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(Model model) {
        model.setCurrentState(BetState.instance());
    }

}
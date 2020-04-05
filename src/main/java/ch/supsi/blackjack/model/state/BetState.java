package ch.supsi.blackjack.model.state;

import ch.supsi.blackjack.model.Model;


// TODO: Creare

/*

Descrizione: il giocatore ha iniziato a giocare, deve decidere quanto vuole puntare per poter ricevere le due prime carte.

Cosa vede l’utente:
•	Bottoni <5,25,50,100> - Incrementano la puntata totale del giocatore.
•	Bottone <reset>
•	Bottone <bet> - Conferma la quantità da puntare.

Cosa deve accadere:
In caso di <5,25,50,100>:
•	La variabile che mantiene la puntata del giocatore deve essere incrementata.
•	Controllo della quantità puntata: MAX<puntata<MIN.
In caso di <bet>
•	Aggiornamento al prossimo stato.

 */

public class BetState implements GameState {

    // singleton
    private static BetState instance = new BetState();

    private BetState() {
    }

    public static BetState instance() {
        System.out.println("Current state: " +instance.getClass().toString());
        return instance;
    }

    // business logic and state transition
    @Override
    public void updateState(Model model) {
        model.setCurrentState(SetupTableState.instance());
    }

}
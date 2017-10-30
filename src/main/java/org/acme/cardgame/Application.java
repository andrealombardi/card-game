package org.acme.cardgame;

import org.acme.cardgame.card.PokerCardFactory;
import org.acme.cardgame.deck.Card;
import org.acme.cardgame.deck.CardDeck;
import org.acme.cardgame.deck.Deck;

/**
 * Shows how to initialise a deck of 52 cards and prints its content.
 * Please refer to the README.md and the unit tests for further details.
 */
public class Application {

    public static void main(String[] args) {
        Deck<Card> deck = new CardDeck(new PokerCardFactory());
        for (int i = 0; i < 52; i++) {
            System.out.println(deck.draw());
        }
    }

}

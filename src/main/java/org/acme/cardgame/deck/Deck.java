package org.acme.cardgame.deck;

public interface Deck<T> {

    /**
     * Restore the pack to a full deck and randomize the order of the elements.
     */
    void shuffle();

    /**
     * @return a random element from the deck.
     */
    T draw();

    /**
     * @return the first element on the deck, without removing it.
     */
    T top();

    /**
     * @return the last element of the deck, without removing it.
     */
    T bottom();

}

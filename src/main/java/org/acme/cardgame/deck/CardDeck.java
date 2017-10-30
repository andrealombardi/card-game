package org.acme.cardgame.deck;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 *
 * This implementation throws an IllegalStateException if draw(), top(),
 * or bottom() are invoked on an empty deck.
 *
 * This class is not ThreadSafe.
 */
public class CardDeck implements Deck<Card> {


    private final CardFactory cardFactory;
    private final Random random;

    private List<Card> deck;

    public CardDeck(CardFactory cardFactory) {
        this.cardFactory = cardFactory;
        this.random = new Random();
        shuffle();
    }


    @Override
    public void shuffle() {
        deck = new ArrayList<>(cardFactory.get());
        Collections.shuffle(deck);
    }

    @Override
    public Card draw() {
        checkDeckState();
        return deck.remove(random.nextInt(deck.size()));
    }


    @Override
    public Card top() {
        checkDeckState();
        return deck.get(0);
    }

    @Override
    public Card bottom() {
        checkDeckState();
        return deck.get(deck.size() - 1);
    }

    private void checkDeckState() {
        if (deck.isEmpty()) {
            throw new IllegalStateException("Operation not permitted on an empty deck");
        }
    }
}

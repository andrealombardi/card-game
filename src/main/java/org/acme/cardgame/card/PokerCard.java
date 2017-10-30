package org.acme.cardgame.card;

import org.acme.cardgame.deck.Card;

import javax.annotation.Nonnull;
import java.util.Objects;

/**
 * Concrete implementation of the Card interface. This implementation is immutable.
 */
//@Immutable
public class PokerCard implements Card {

    private final Rank rank;
    private final Suit suit;


    private PokerCard(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
    }

    public static PokerCard of(@Nonnull Rank rank, @Nonnull Suit suit) {
        return new PokerCard(rank, suit);
    }

    @Override
    public Rank getRank() {
        return rank;
    }

    @Override
    public Suit getSuit() {
        return suit;
    }

    @Override
    public String toString() {
        return rank + " of " + suit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PokerCard card = (PokerCard) o;
        return suit == card.suit && rank == card.rank;
    }

    @Override
    public int hashCode() {
        return Objects.hash(suit, rank);
    }
}

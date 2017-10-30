package org.acme.cardgame.card;

import com.google.common.collect.ImmutableSet;
import org.acme.cardgame.deck.Card;
import org.acme.cardgame.deck.CardFactory;

import javax.annotation.Nonnull;
import java.util.Set;

import static java.util.EnumSet.allOf;
import static java.util.stream.Collectors.toSet;

/**
 * This implementation of the CardFactory holds the reference of an immutable set
 * of 52 cards.
 */
public class PokerCardFactory implements CardFactory {


    private static final Set<Card> CARDS = ImmutableSet.copyOf(
            allOf(Suit.class)
                    .stream()
                    .flatMap(
                            suit -> allOf(Rank.class)
                                    .stream()
                                    .map(rank -> PokerCard.of(rank, suit)))
                    .collect(toSet()));

    @Override
    @Nonnull
    /**
     * Returns an Immutable Set of 52 cards.
     */
    public Set<Card> get() {
        return CARDS;
    }
}

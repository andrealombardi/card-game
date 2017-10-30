package org.acme.cardgame.card;

import org.acme.cardgame.deck.Card;
import org.acme.cardgame.deck.CardFactory;
import org.junit.Test;

import java.util.EnumSet;
import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class PokerCardFactoryTest {

    private CardFactory cardFactory = new PokerCardFactory();

    @Test
    public void instance_contains_52_cards() {

        Set<Card> cards = cardFactory.get();
        assertThat(cards.size(), is(52));

    }

    @Test
    public void instance_contains_all_suits() {

        Set<Suit> suits = cardFactory.get()
                .stream()
                .map(Card::getSuit)
                .collect(toSet());

        assertTrue(suits.containsAll(EnumSet.allOf(Suit.class)));

    }


    @Test
    public void instance_contains_all_ranks() {

        Set<Rank> ranks = cardFactory.get()
                .stream()
                .map(Card::getRank)
                .collect(toSet());

        assertTrue(ranks.containsAll(EnumSet.allOf(Rank.class)));


    }

}
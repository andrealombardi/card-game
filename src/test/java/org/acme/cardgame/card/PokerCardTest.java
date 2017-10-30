package org.acme.cardgame.card;

import org.acme.cardgame.deck.Card;
import org.junit.Test;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

public class PokerCardTest {

    @Test
    public void toString_produces_the_expected_output(){
        Card card = PokerCard.of(Rank.THREE, Suit.CLUBS);
        assertThat(card.toString(), equalTo("THREE of CLUBS"));
    }

}
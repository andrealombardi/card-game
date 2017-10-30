package org.acme.cardgame.deck;

import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Sets;
import org.acme.cardgame.card.PokerCard;
import org.acme.cardgame.card.Rank;
import org.acme.cardgame.card.Suit;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.util.Collections;
import java.util.Set;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNot.not;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsSame.sameInstance;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class CardDeckTest {


    public static final PokerCard ACE_OF_HEARTS = PokerCard.of(Rank.ACE, Suit.HEARTS);
    public static final PokerCard ACE_OF_SPADES = PokerCard.of(Rank.ACE, Suit.SPADES);

    private Deck<Card> deck;

    @Rule
    public ExpectedException expectedException = ExpectedException.none();


    @Test
    public void top_on_an_empty_deck_will_result_in_exception() {
        setUpExceptionExpectations();
        deck = new CardDeck(() -> Collections.emptySet());
        deck.top();
    }

    @Test
    public void bottom_on_an_empty_deck_will_result_in_exception() {
        setUpExceptionExpectations();
        deck = new CardDeck(() -> Collections.emptySet());
        deck.bottom();
    }

    @Test
    public void draw_on_an_empty_deck_will_result_in_exception() {
        setUpExceptionExpectations();
        deck = new CardDeck(() -> Collections.emptySet());
        deck.draw();
    }


    @Test
    public void it_is_safe_to_invoke_shuffle_on_empty_deck() {
        deck = new CardDeck(() -> Collections.emptySet());
        deck.shuffle();
    }

    @Test
    public void when_deck_contains_only_one_card_top_and_bottom_return_the_same_card() {
        deck = new CardDeck(() -> ImmutableSet.of(ACE_OF_HEARTS));
        assertThat(deck.top(), sameInstance(deck.bottom()));
        assertThat(deck.top(), sameInstance(ACE_OF_HEARTS));
    }

    @Test
    public void when_deck_contains_two_cards_top_and_bottom_return_different_cards() {
        deck = new CardDeck(() -> ImmutableSet.of(ACE_OF_HEARTS, ACE_OF_SPADES));
        assertThat(deck.top(), notNullValue());
        assertThat(deck.bottom(), notNullValue());
        assertThat(deck.top(), is(not(deck.bottom())));
    }

    @Test
    public void bottom_does_not_remove_the_card() {
        deck = new CardDeck(() -> ImmutableSet.of(ACE_OF_HEARTS));
        Card last = deck.bottom();
        assertThat(last, equalTo(deck.bottom()));
    }

    @Test
    public void top_does_not_remove_the_card() {
        deck = new CardDeck(() -> ImmutableSet.of(ACE_OF_HEARTS));
        Card first = deck.top();
        assertThat(first, equalTo(deck.top()));
    }

    @Test
    public void shuffle_restores_the_deck_to_its_original_size() {

        deck = new CardDeck(() -> ImmutableSet.of(ACE_OF_HEARTS, ACE_OF_SPADES));

        Set<Card> firstRun = Sets.newHashSetWithExpectedSize(2);
        firstRun.add(deck.draw());
        firstRun.add(deck.draw());

        //deck should be empty at this stage
        deck.shuffle();

        Set<Card> secondRun = Sets.newHashSetWithExpectedSize(2);
        secondRun.add(deck.draw());
        secondRun.add(deck.draw());


        assertTrue(Iterables.elementsEqual(firstRun, secondRun));
    }

    private void setUpExceptionExpectations() {
        expectedException.expect(IllegalStateException.class);
        expectedException.expectMessage("Operation not permitted on an empty deck");
    }

}

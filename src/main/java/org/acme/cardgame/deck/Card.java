package org.acme.cardgame.deck;

import org.acme.cardgame.card.Rank;
import org.acme.cardgame.card.Suit;

public interface Card {

    Rank getRank();

    Suit getSuit();
}

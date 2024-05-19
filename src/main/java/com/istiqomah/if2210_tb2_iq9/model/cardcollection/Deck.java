package com.istiqomah.if2210_tb2_iq9.model.cardcollection;

import com.istiqomah.if2210_tb2_iq9.model.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Deck {
    private List<Card> mainDeck;
    private List<Card> hand;

    public Deck() {
        mainDeck = new ArrayList<>();
        hand = new ArrayList<>();
    }

    public void shuffleMainDeck() {
        Collections.shuffle(mainDeck);
    }

    public List<Card> drawFromMainDeck() {
        List<Card> drawnCards = new ArrayList<>();
        int cardsToDraw = Math.min(4, mainDeck.size());
        for (int i = 0; i < cardsToDraw; i++) {
            drawnCards.add(mainDeck.remove(0)); // Draw the top card
        }
        return drawnCards;
    }

    public void addToHand(List<Card> cards) {
        int spaceInHand = 6 - hand.size();
        int cardsToDraw = Math.min(spaceInHand, cards.size());

        for (int i = 0; i < cardsToDraw; i++) {
            hand.add(cards.get(i));
        }
    }

    public Card removeFromHand(int index) {
        if (index >= 0 && index < hand.size()) {
            return hand.remove(index);
        } else {
            // Handle the case where the index is out of bounds
            return null;
        }
    }

    public boolean isHandFull() {
        return hand.size() == 6;
    }

    public int getMainDeckSize() {
        return mainDeck.size();
    }

    public void addToMainDeck(Card card) {
        mainDeck.add(card);
    }

    public List<Card> getHand() {
        return new ArrayList<>(hand); // Return a copy of the hand
    }
}
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
        hand = new ArrayList<>(Collections.nCopies(6, null));
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

    public void addCardToHand(Card card) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i) == null) {
                hand.set(i, card);
                return;
            }
        }
    }

    public void addCardToHand(Integer index, Card card) {
        if (index >= 0 && index < hand.size()) {
            hand.set(index, card);
        }
    }

    public void addToHand(List<Card> cards) {
        for (Card card : cards) {
            addCardToHand(card);
        }
    }

    public Card removeFromHand(int index) {
        if (index >= 0 && index < hand.size()) {
            return hand.set(index, null); // return the removed card and set the position to null
        } else {
            // Handle the case where the index is out of bounds
            return null;
        }
    }
    public boolean isHandFull() {
        return !hand.contains(null);
    }

    public int getMainDeckSize() {
        return mainDeck.size();
    }

    public void addToMainDeck(Card card) {
        mainDeck.add(card);
    }

    public List<Card> getHand() {
        return hand; // Return a copy of the hand
    }

    public void removeCardFromHand(Card card) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i) != null && hand.get(i).equals(card)) {
                hand.set(i, null);
                break;
            }
        }
    }
}
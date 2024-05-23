package com.istiqomah.if2210_tb2_iq9.model.cardcollection;

import com.istiqomah.if2210_tb2_iq9.model.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.istiqomah.if2210_tb2_iq9.model.card.CardManager.*;

public class Deck {
    private List<Card> mainDeck;
    private List<Card> hand;

    public Deck() {
        mainDeck = new ArrayList<>();
        hand = new ArrayList<>(Collections.nCopies(6, null));
    }

    public void initMainDeck() {
        for (int i = 0; i < 5; i++) {
            mainDeck.add(getRandomItem());
        }
        for (int i = 0; i < 12; i++) {
            mainDeck.add(getRandomAnimal());
        }
        for (int i = 0; i < 12; i++) {
            mainDeck.add(getRandomPlant());
        }
        for (int i = 0; i < 11; i++) {
            mainDeck.add(getRandomProduct());
        }
    }

    public void shuffleMainDeck() {
        Collections.shuffle(mainDeck);
    }

    public List<Card> drawFromMainDeck() {
        List<Card> drawnCards = new ArrayList<>();
        int cardsToDraw = 4;
        for (int i = 0; i < cardsToDraw; i++) {
            drawnCards.add(mainDeck.remove(0)); // Draw the top card
        }
        return drawnCards;
    }

    public void addCardToMainDeck(Card card) {
        mainDeck.set(getMainDeckSize() - 1, card);
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

    public Card removeCardFromMainDeck(Card card) {
        if (mainDeck.contains(card)) {
            mainDeck.remove(card);
            return card;
        } else {
            System.out.println("Error removeFromMainDeck");
            return null;
        }
    }

    public void removeFromMainDeck(List<Card> cards) {
        for (Card card : cards) {
            removeCardFromMainDeck(card);
        }
    }

    public boolean isHandFull() {
        return !hand.contains(null);
    }

    public int getMainDeckSize() {
        return mainDeck.size();
    }

    public void addToMainDeck(List<Card> cards) {
        for (Card card : cards) {
            addCardToMainDeck(card);
        }
    }

    public List<Card> getHand() {
        return hand; // Return a copy of the hand
    }

    public List<Card> getMainDeck() {
        return mainDeck; // Return a copy of the main deck
    }

    public void removeCardFromHand(Card card) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i) != null && hand.get(i).equals(card)) {
                hand.set(i, null);
                break;
            }
        }
    }

    public void clearHand() {
        for (int i= 0; i < hand.size(); i++){
            if (hand.get(i) != null){
                hand.set(i,null);
            }
        }
    }

    public Integer deckCount (){
        Integer count= 0;
        for (int i= 0; i < mainDeck.size(); i++){
            if (mainDeck.get(i) != null){
                count++;
            }
        }
        return count;
    }
}
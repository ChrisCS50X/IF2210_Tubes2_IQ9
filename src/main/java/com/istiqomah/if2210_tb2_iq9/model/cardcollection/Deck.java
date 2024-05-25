package com.istiqomah.if2210_tb2_iq9.model.cardcollection;

import com.istiqomah.if2210_tb2_iq9.model.card.Card;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.istiqomah.if2210_tb2_iq9.model.card.CardManager.*;

// Kelas Deck untuk mengatur deck hand pemain dan deck kartu utama
public class Deck {
    private List<Card> mainDeck;
    private List<Card> hand;

    // Konstruktor
    public Deck() {
        mainDeck = new ArrayList<>();
        hand = new ArrayList<>(Collections.nCopies(6, null));
    }

    // Metode untuk menginisialisasi deck utama
    public void initMainDeck() {
        mainDeck.addAll(getComposition());
    }

    // Metode untuk mengacak deck utama atau kocok kartu main deck
    public void shuffleMainDeck() {
        Collections.shuffle(mainDeck);
    }

    // Metode untuk mengambil kartu dari deck utama
    public List<Card> drawFromMainDeck() {
        List<Card> drawnCards = new ArrayList<>();
        int cardsToDraw = 4;
        for (int i = 0; i < cardsToDraw; i++) {
            drawnCards.add(mainDeck.remove(0));
        }
        return drawnCards;
    }

    // Metode untuk menambahkan kartu ke deck utama
    public void addCardToMainDeck(Card card) {
        mainDeck.set(getMainDeckSize() - 1, card);
    }


    // Metode untuk menambahkan kartu ke hand
    public void addCardToHand(Card card) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i) == null) {
                hand.set(i, card);
                return;
            }
        }
    }

    // Metode untuk menambahkan kartu ke hand dengan parameter index dan kartu
    public void addCardToHand(Integer index, Card card) {
        if (index >= 0 && index < hand.size()) {
            hand.set(index, card);
        }
    }

    // Metode untuk menambahkan kartu ke hand dengan parameter list kartu
    public void addToHand(List<Card> cards) {
        for (Card card : cards) {
            addCardToHand(card);
        }
    }

    // Metode untuk menghapus kartu dari hand dengan cara membuat kartu di index menjadi null
    public Card removeFromHand(int index) {
        if (index >= 0 && index < hand.size()) {
            return hand.set(index, null); 
        } else {
            return null;
        }
    }

    // Metode untuk menghapus kartu dari main deck dengan parameter kartu
    public Card removeCardFromMainDeck(Card card) {
        if (mainDeck.contains(card)) {
            mainDeck.remove(card);
            return card;
        } else {
            System.out.println("Error removeFromMainDeck");
            return null;
        }
    }

    // Metode untuk menghapus kartu dari main deck dengan parameter kartu yang banyak
    public void removeFromMainDeck(List<Card> cards) {
        for (Card card : cards) {
            removeCardFromMainDeck(card);
        }
    }

    // Metode untuk mengecek apakah hand pemain sudah penuh atau belum
    public boolean isHandFull() {
        return !hand.contains(null);
    }

    // Metode untuk mendapatkan ukuran hand pemain
    public int getMainDeckSize() {
        return mainDeck.size();
    }

    // Metode untuk menambahkan kartu ke deck utama dengan parameter list kartu
    public void addToMainDeck(List<Card> cards) {
        for (Card card : cards) {
            addCardToMainDeck(card);
        }
    }

    // Metode untuk mendapatkan hand pemain
    public List<Card> getHand() {
        return hand; 
    }

    // Metode untuk mendapatkan deck utama
    public List<Card> getMainDeck() {
        return mainDeck; // Return a copy of the main deck
    }

    // Metode untuk menghapus kartu dari hand dengan parameter kartu
    public void removeCardFromHand(Card card) {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i) != null && hand.get(i).equals(card)) {
                hand.set(i, null);
                break;
            }
        }
    }

    // Metode untuk clear kartu yang ada di hand
    public void clearHand() {
        for (int i = 0; i < hand.size(); i++) {
            if (hand.get(i) != null) {
                hand.set(i, null);
            }
        }
    }

    // Metode untuk menghitung jumlah kartu yang ada di deck utama
    public Integer deckCount() {
        Integer count = 0;
        for (int i = 0; i < mainDeck.size(); i++) {
            if (mainDeck.get(i) != null) {
                count++;
            }
        }
        return count;
    }
}
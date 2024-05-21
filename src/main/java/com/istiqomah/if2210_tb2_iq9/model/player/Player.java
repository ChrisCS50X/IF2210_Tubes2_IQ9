package com.istiqomah.if2210_tb2_iq9.model.player;

import com.istiqomah.if2210_tb2_iq9.model.ladang.Ladang;
import com.istiqomah.if2210_tb2_iq9.model.card.Card;
import com.istiqomah.if2210_tb2_iq9.model.card.Product;
import com.istiqomah.if2210_tb2_iq9.model.cardcollection.Deck;
import com.istiqomah.if2210_tb2_iq9.model.toko.Toko;

import java.util.List;

public class Player {
    private static Player[] players = new Player[2];
    private static int playerCount = 0;
    private static int idxTurnPlayer = 0;

    private String name;
    private int gulden;
    private Ladang ladang;
    private Deck deck;

    // Constructor
    public Player(String name ,int initialGulden) {
        this.name = name;
        this.gulden = initialGulden;
        this.ladang = new Ladang();
        this.deck = new Deck();
    }

    // Metode statis untuk menginisialisasi pemain
    public static Player createPlayer(int initialGulden) {
        if (playerCount < players.length) {
            Player newPlayer = new Player("player"+(playerCount+1),initialGulden);
            players[playerCount] = newPlayer;
            playerCount++;
            return newPlayer;
        } else {
            throw new IllegalStateException("Maximum number of players reached.");
        }
    }

    public static Player getPlayerNow() {
        return players[idxTurnPlayer];
    }

    public static void nextTurn() {
        idxTurnPlayer = (idxTurnPlayer + 1) % playerCount;
    }

    public static Player getPlayerByIdx(int idx) {
        return players[idx];
    }

    public static int getIdxTurnPlayer() {
        return idxTurnPlayer;
    }

    public static Player getPlayerEnemy() {
        return players[(idxTurnPlayer + 1) % playerCount];
    }
    // Getter untuk array players
    public static Player[] getAllPlayers() {
        return players;
    }

    public String getName() {
        return name;
    }
    // Getter
    public int getGulden() {
        return gulden;
    }

    public Ladang getLadang() {
        return ladang;
    }

    public Deck getDeck() {
        return deck;
    }

    public void setDeckAktifPlayer(int idx, Card card) {
        deck.addCardToHand(idx, card);
    }
    // Methods to add and subtract gulden
    public void addGulden(int amount) {
        this.gulden += amount;
    }

    public void subtractGulden(int amount) {
        this.gulden -= amount;
    }

    // Methods to plant and harvest
    public void harvest(int x, int y) {
        Card card = ladang.harvest(x, y);
        if (card != null) {
            deck.addToHand(List.of(card));
        }
    }

    public void addCardToLadang(Card card, int x, int y) {
        ladang.addCardToPosition(card, x, y);
    }

    public void removeCardFromLadang(int x, int y) {
        ladang.removeCardFromPosition(x, y);
    }

    public void addCardsToHand(List<Card> cards) {
        deck.addToHand(cards);
    }

    public Card removeCardFromHand(int index) {
        return deck.removeFromHand(index);
    }

    public void buyProduct(Toko toko, Card card) {
        if (card instanceof Product product) {
            if (gulden >= product.getPrice()) {
                toko.removeProduct(card);
                subtractGulden(product.getPrice());
            } else {
                System.out.println("Gulden tidak cukup untuk membeli produk.");
            }
        } else {
            System.out.println("Card is not a product.");
        }
    }

    public void sellProduct(Toko toko, Card card) {
        if (card instanceof Product product) {
            toko.addProduct(card);
            addGulden(product.getPrice());
            // Remove the card from the player's hand
            deck.getHand().remove(card);
        } else {
            System.out.println("Card is not a product.");
        }
    }


}
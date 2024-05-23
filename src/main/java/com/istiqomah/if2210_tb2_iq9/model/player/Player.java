package com.istiqomah.if2210_tb2_iq9.model.player;

import com.istiqomah.if2210_tb2_iq9.model.ladang.Ladang;
import com.istiqomah.if2210_tb2_iq9.model.card.Card;
import com.istiqomah.if2210_tb2_iq9.model.card.Product;
import com.istiqomah.if2210_tb2_iq9.model.cardcollection.Deck;
import com.istiqomah.if2210_tb2_iq9.model.toko.Toko;

import java.util.List;

// Kelas Player yang merepresentasikan pemain dalam permainan
public class Player {
    // Atribut statis untuk menyimpan pemain
    private static Player[] players = new Player[2];
    private static int playerCount = 0;
    private static int idxTurnPlayer = 0;

    // Atribut pemain
    private String name;
    private int gulden;
    private Ladang ladang;
    private Deck deck;

    // Konstruktor
    public Player(String name, int initialGulden) {
        this.name = name;
        this.gulden = initialGulden;
        this.ladang = new Ladang();
        this.deck = new Deck();
    }

    // Metode statis untuk menginisialisasi pemain
    public static Player createPlayer(int initialGulden) {
        if (playerCount < players.length) {
            Player newPlayer = new Player("player" + (playerCount + 1), initialGulden);
            players[playerCount] = newPlayer;
            playerCount++;
            return newPlayer;
        } else {
            throw new IllegalStateException("Maximum number of players reached.");
        }
    }

    // Metode statis untuk mendapatkan pemain yang sedang bermain
    public static Player getPlayerNow() {
        return players[idxTurnPlayer];
    }

    // Metode statis untuk mengganti giliran pemain
    public static void nextTurn() {
        idxTurnPlayer = (idxTurnPlayer + 1) % playerCount;
    }

    // Metode statis untuk mendapatkan pemain berdasarkan indeks
    public static Player getPlayerByIdx(int idx) {
        return players[idx];
    }

    // Metode statis untuk mendapatkan indeks pemain yang sedang bermain
    public static int getIdxTurnPlayer() {
        return idxTurnPlayer;
    }

    // Metode statis untuk mendapatkan pemain musuh
    public static Player getPlayerEnemy() {
        return players[(idxTurnPlayer + 1) % playerCount];
    }

    // Metode statis untuk mendapatkan semua pemain
    public static Player[] getAllPlayers() {
        return players;
    }

    // Metode statis untuk mengupdate usia tanaman
    public static void updateAgePlant() {
        for (Player player : players) {
            player.ladang.updateAgePlant();
        }
    }

    // Getter dan setter untuk atribut pemain
    public String getName() {
        return name;
    }

    // Getter
    public int getGulden() {
        return gulden;
    }

    public void setGulden(int gulden) {
        this.gulden = gulden;
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

    // Menambahkan dan mengurangi gulden
    public void addGulden(int amount) {
        this.gulden += amount;
    }

    public void subtractGulden(int amount) {
        this.gulden -= amount;
    }

    // Metode untuk mengambil kartu dari ladang atau panen
    public void harvest(int x, int y) {
        Product card = (Product) ladang.harvest(x, y);
        if (card != null) {
            deck.addCardToHand(card);
        }
    }

    // Metode untuk menaruh kartu ke ladang
    public void addCardToLadang(Card card, int x, int y) {
        ladang.addCardToPosition(card, x, y);
    }

    // Metode untuk menghapus kartu dari ladang
    public void removeCardFromLadang(int x, int y) {
        ladang.removeCardFromPosition(x, y);
    }

    // Metode untuk menambahkan kartu ke tangan
    public void addCardsToHand(List<Card> cards) {
        deck.addToHand(cards);
    }

    // Metode untuk menghapus kartu dari tangan
    public Card removeCardFromHand(int index) {
        return deck.removeFromHand(index);
    }

    // Metode player untuk membeli produk dari toko
    public void buyProduct(Toko toko, Card card) {
        if (card instanceof Product product) {
            if (gulden >= product.getPrice() && !deck.isHandFull()) {
                toko.removeProduct(card);
                deck.addCardToHand(card);
                subtractGulden(product.getPrice());
            } else {
                System.out.println("Gulden tidak cukup untuk membeli produk atau tangan penuh.");
            }
        } else {
            System.out.println("Card is not a product.");
        }
    }

    // Metode player untuk menjual produk ke toko
    public void sellProduct(Toko toko, Card card) {
        if (card instanceof Product product) {
            toko.addProduct(card);
            addGulden(product.getPrice());
            deck.removeCardFromHand(card);
        } else {
            System.out.println("Card is not a product.");
        }
    }

    // Metode untuk mengclear ladang
    public void clearLadang() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 5; j++) {
                if (!ladang.isPositionEmpty(i, j)) {
                    ladang.removeCardFromPosition(i, j);
                }
            }
        }
    }

}
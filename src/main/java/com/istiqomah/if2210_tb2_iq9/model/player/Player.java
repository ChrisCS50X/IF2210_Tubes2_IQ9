package com.istiqomah.if2210_tb2_iq9.model.player;

import com.istiqomah.if2210_tb2_iq9.model.ladang.Ladang;
import com.istiqomah.if2210_tb2_iq9.model.card.Card;
import com.istiqomah.if2210_tb2_iq9.model.card.Product;
import com.istiqomah.if2210_tb2_iq9.model.cardcollection.Deck;
import com.istiqomah.if2210_tb2_iq9.model.toko.Toko;

import java.util.List;

public class Player {
    private int gulden;
    private Ladang ladang;
    private Deck deck;

    // Constructor
    public Player(int initialGulden) {
        this.gulden = initialGulden;
        this.ladang = new Ladang();
        this.deck = new Deck();
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

    public void addCardsToHand(List<Card> cards) {
        deck.addToHand(cards);
    }

    public Card removeCardFromHand(int index) {
        return deck.removeFromHand(index);
    }

    public void buyProduct(Toko toko, Product product) {
        if (gulden >= product.getPrice()) {
            toko.buyProduct(product, this);
            subtractGulden((int)product.getPrice());
        } else {
            System.out.println("Gulden tidak cukup untuk membeli produk.");
        }
    }

    public void sellProduct(Toko toko, int index) {
        Card card = removeCardFromHand(index);
        if (card instanceof Product product) {
            toko.sellProduct(index, this);
            addGulden((int)product.getPrice());
        } else {
            System.out.println("Kartu pada index " + index + " bukanlah suatu product.");
        }
    }
}
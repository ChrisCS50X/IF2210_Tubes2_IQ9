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

    public Player(int initialGulden) {
        this.gulden = initialGulden;
        this.ladang = new Ladang();
        this.deck = new Deck();
    }

    public int getGulden() {
        return gulden;
    }

    public void setGulden(int gulden){
        this.gulden = gulden;
    }

    public void addGulden(int amount) {
        this.gulden += amount;
    }

    public void subtractGulden(int amount) {
        this.gulden -= amount;
    }

    public Ladang getLadang() {
        return ladang;
    }

    public Deck getDeck() {
        return deck;
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
            deck.addToHand(List.of(product));
        } else {
            System.out.println("Gulden tidak cukup untuk membeli produk.");
        }
    }

    public void sellProduct(Toko toko, int index) {
        Card card = deck.removeFromHand(index);
        if (card.getType().equals("Product")) {
            Product product = (Product) card;
            toko.sellProduct(product, this);
            addGulden((int)product.getPrice());
        } else {
            System.out.println("Card at index " + index + " is not a product.");
        }
    }

    public void harvest(int x, int y) {
        Card card = ladang.harvest(x, y);
        if (card != null) {
            deck.addToHand(List.of(card));
        }
    }
}
package com.istiqomah.if2210_tb2_iq9.model.toko;

import com.istiqomah.if2210_tb2_iq9.model.card.*;
import com.istiqomah.if2210_tb2_iq9.model.player.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class Toko {
    private Map<Card, Integer> products;

    public Toko() {
        products = new HashMap<>();
    }

    public void addProduct(Card card) {
        products.put(card, products.getOrDefault(card, 0) + 1);
    }

    public void removeProduct(Card card) {
        if (card instanceof Product product && products.containsKey(product) && products.get(product) > 0) {
            int count = products.get(product);
            if (count > 1) {
                products.put(product, count - 1);
            } else {
                products.remove(product);
            }
        }
    }

    public Map<Card, Integer> getAvailableProducts() {
        return products;
    }
}
package com.istiqomah.if2210_tb2_iq9.model.toko;

import com.istiqomah.if2210_tb2_iq9.model.card.*;

import java.util.HashMap;
import java.util.Map;

public class Toko {
    // Map untuk menyimpan produk dan jumlahnya
    private Map<Card, Integer> products;

    // Konstruktor Toko
    public Toko() {
        // Inisialisasi Map produk
        products = new HashMap<>();
    }

    // Metode untuk menambahkan produk
    public void addProduct(Card card) {
        // Menambahkan atau memperbarui jumlah produk
        products.put(card, products.getOrDefault(card, 0) + 1);
    }

    // Metode untuk menghapus produk
    public void removeProduct(Card card) {
        // Jika produk ada dan jumlahnya lebih dari 0
        if (card instanceof Product product && products.containsKey(product) && products.get(product) > 0) {
            int count = products.get(product);
            // Jika jumlah produk lebih dari 1, kurangi jumlahnya
            if (count > 1) {
                products.put(product, count - 1);
            } else {
                // Jika jumlah produk adalah 1, hapus produk dari Map
                products.remove(product);
            }
        }
    }

    // Metode untuk mendapatkan produk yang tersedia
    public Map<Card, Integer> getAvailableProducts() {
        return products;
    }

    // Metode untuk menghapus semua produk
    public void clearProducts() {
        products.clear();
    }
}
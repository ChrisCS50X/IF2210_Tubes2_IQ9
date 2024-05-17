//package com.istiqomah.if2210_tb2_iq9.model.toko;
//
//import com.istiqomah.if2210_tb2_iq9.model.card.Product;
//import com.istiqomah.if2210_tb2_iq9.model.player.Player;
//import java.util.HashMap;
//import java.util.Map;
//
//public class Toko {
//    private Map<Product, Integer> products;
//
//    public Toko() {
//        products = new HashMap<>(); // Initialize the map with no products
//    }
//
//    public void sellProduct(Product product, Player player) {
//        if (player.removeCardFromHand(product)) {
//            addProduct(product);
//            player.addGulden((int)product.getPrice());
//        }
//    }
//
//    public void buyProduct(Product product, Player player) {
//        if (products.containsKey(product) && products.get(product) > 0) {
//            removeProduct(product);
//            player.subtractGulden((int)product.getPrice());
//            player.addCardsToHand(List.of(product));
//        }
//    }
//
//    private void addProduct(Product product) {
//        products.put(product, products.getOrDefault(product, 0) + 1);
//    }
//
//    private void removeProduct(Product product) {
//        if (products.containsKey(product)) {
//            int count = products.get(product);
//            if (count > 1) {
//                products.put(product, count - 1);
//            } else {
//                products.remove(product);
//            }
//        }
//    }
//
//    public Map<Product, Integer> getAvailableProducts() {
//        return new HashMap<>(products); // Return a copy of the products map
//    }
//}
package com.istiqomah.if2210_tb2_iq9.model.card;

import java.util.Arrays;
import java.util.List;

public class HerbivoreAnimal extends Animal {
    private static final List<String> EDIBLE_PRODUCTS = Arrays.asList("Jagung", "Labu", "Stroberi");

    public HerbivoreAnimal(int id, String name, String imagePath, Card product, int harvestWeight) {
        super(id, name, imagePath, product, harvestWeight);
    }

    @Override
    public void feed(Product product) {
        if (EDIBLE_PRODUCTS.contains(product.getName())) {
            this.weight += product.getWeight();
        }
    }

    @Override
    public String getType() {
        return "HerbivoreAnimal";
    }
}
package com.istiqomah.if2210_tb2_iq9.model.card;

public class OmnivoreAnimal extends Animal {

    public OmnivoreAnimal(int id, String name, String imagePath, Card product, int harvestWeight) {
        super(id, name, imagePath, product, harvestWeight);
    }

    @Override
    public void feed(Product product) {
        // Omnivores can eat any product, so we don't need to check the product type
        this.weight += product.getWeight();
    }

    @Override
    public String getType() {
        return "OmnivoreAnimal";
    }
}
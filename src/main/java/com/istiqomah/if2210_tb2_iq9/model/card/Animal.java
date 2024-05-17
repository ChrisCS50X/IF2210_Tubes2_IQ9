package com.istiqomah.if2210_tb2_iq9.model.card;

import java.util.ArrayList;
import java.util.List;

public abstract class Animal extends Card implements Harvestable {
    protected int weight;
    protected int harvestWeight;
    protected Card product; // product yang dihasilkan
    private List<Item> activeItems;

    public Animal(int id, String name, String imagePath, Card product, int harvestWeight) {
        super(id, name, imagePath);
        this.product = product;
        this.harvestWeight = harvestWeight;
        this.weight = 0;
        this.activeItems = new ArrayList<>();
    }

    public Card getProduct() {return product;}

    public void setProduct(Card product) {this.product = product;}

    public int getHarvestWeight() {return harvestWeight;}

    public void setHarvestWeight(int harvestWeight) {this.harvestWeight = harvestWeight;}

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void applyItem(Item item) {
        this.activeItems.add(item);
    }

    public abstract void feed(Product product);

    @Override
    public boolean isHarvestable() {
        return this.weight >= this.harvestWeight;
    }

    @Override
    public Card harvest() {
        if (isHarvestable()) {
            this.weight = 0;
            return this.product;
        } else {
            return null;
        }
    }
}
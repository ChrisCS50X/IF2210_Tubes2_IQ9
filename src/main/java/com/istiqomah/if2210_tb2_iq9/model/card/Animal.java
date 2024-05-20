package com.istiqomah.if2210_tb2_iq9.model.card;

import java.util.ArrayList;
import java.util.List;

public class Animal extends Card implements Harvestable {
    private int weight;
    private int harvestWeight;
    private Card product; // product yang dihasilkan
    private String type;
    int tipe;
    private List<Item> activeItems;

    public Animal(int id, String name, String imagePath,int weight, int harvestWeight, int tipe) {
        super(id, name, imagePath);
        this.harvestWeight = harvestWeight;
        this.weight = 0;
        this.tipe = 0;
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

    public void feed(Product product){

    }

    @Override
    public void applyItem(Item item) {
        this.activeItems.add(item);
    }
    public int getjenis(){
        return tipe;
    }

    @Override
    public String getType() {
        return type;
    }
    @Override
    public String getDetails() {
        return "Animal: " + getName() + ", Weight: " + weight + ", Harvest Weight: " + harvestWeight;
    }

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
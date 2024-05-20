package com.istiqomah.if2210_tb2_iq9.model.card;

import java.util.ArrayList;
import java.util.List;

public class Plant extends Card implements Harvestable {
    private int age;
    private int harvestDuration;
    private Card product;
    private List<Item> activeItems;

    public Plant(int id, String name, String imagePath, int age, int harvestDuration) {
        super(id, name, imagePath);
        this.harvestDuration = harvestDuration;
        this.age = 0;
        this.activeItems = new ArrayList<>();
    }

    public Card getProduct() {return this.product;}

    public void setProduct(Card product) {this.product = product;}

    public int getHarvestDuration() {return this.harvestDuration;}

    public void setHarvestDuration(int harvestDuration) {this.harvestDuration = harvestDuration;}

    public int getAge() {return this.age;}

    public void setAge(int age) {this.age = age;}

    public void grow() {this.age++;}

    public void applyItem(Item item) {
        this.activeItems.add(item);
    }

    @Override
    public boolean isHarvestable() {return this.age >= this.harvestDuration;}

    public String getType() {return "Plant";}

    public Card harvest() {
        if (isHarvestable()) {
            this.age = 0;
            return this.product;
        } else {
            return null;
        }
    }
}
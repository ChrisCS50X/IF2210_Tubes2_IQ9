package com.istiqomah.if2210_tb2_iq9.model;

import com.istiqomah.if2210_tb2_iq9.model.Card;

import java.util.ArrayList;
import java.util.List;

public class Plant extends Card implements Harvestable {
    private int age;
    private int harvestDuration;
    private Card product;
    private List<Item> activeItems;

    public Plant(int id, String name, String imagePath, Card product, int harvestDuration) {
        super(id, name, imagePath);
        this.product = product;
        this.harvestDuration = harvestDuration;
        this.age = 0;
        this.activeItems = new ArrayList<>();
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void grow() {
        this.age++;
    }

    @Override
    public boolean isHarvestable() {
        return this.age >= this.harvestDuration;
    }

    public String getType() {
        return "Plant";
    }

    public Card harvest() { // Changed return type from String to Card
        if (isHarvestable()) {
            this.age = 0;
            return this.product;
        } else {
            return null;
        }
    }
}
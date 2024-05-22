package com.istiqomah.if2210_tb2_iq9.model.card;

import java.util.ArrayList;
import java.util.List;

public class Plant extends Card implements Harvestable {
    private int age;
    private int harvestDuration;
    private String namaProduct;
    private List<Item> activeItems;

    public Plant(int id, String name, String imagePath, int age, int harvestDuration, String Namaproduct) {
        super(id, name, imagePath);
        this.harvestDuration = harvestDuration;
        this.age = 0;
        this.activeItems = new ArrayList<>();
        this.namaProduct = Namaproduct;
    }

    public Card getProduct() {return CardManager.getCard("product", this.namaProduct);}

    public void setProduct(String product) {this.namaProduct = product;}

    public int getHarvestDuration() {return this.harvestDuration;}

    public void setHarvestDuration(int harvestDuration) {this.harvestDuration = harvestDuration;}

    public int getAge() {return this.age;}

    public void setAge(int age) {this.age = age;}

    public void grow() {this.age++;}

    public String getType() {return "Plant";}

    @Override
    public void applyItem(Item item) {
        this.activeItems.add(item);
    }

    @Override
    public String getDetails() {
        return "Plant: " + getName() + ", Age: " + age + ", Harvest Duration: " + harvestDuration;
    }

    @Override
    public boolean isHarvestable() {
        return this.age >= this.harvestDuration;
    }

    @Override
    public Card harvest() {
        if (isHarvestable()) {
            this.age = 0;
            return CardManager.getCard("product", this.namaProduct);
        } else {
            return null;
        }
    }

    @Override
    public Integer getBerat_Umur() {
        return age;
    }

    @Override
    public List<Item> getActiveItems() {
        return activeItems;
    }

}
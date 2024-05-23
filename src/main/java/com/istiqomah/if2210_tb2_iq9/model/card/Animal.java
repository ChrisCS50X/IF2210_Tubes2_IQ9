package com.istiqomah.if2210_tb2_iq9.model.card;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Animal extends Card implements Harvestable {
    private int weight;
    private int harvestWeight;
    private String product; // product yang dihasilkan
    String tipe;
    private boolean isProtected;
    private boolean isTrapped;
    private List<Item> activeItems;

    public Animal(int id, String name, String imagePath,int weight, int harvestWeight, String tipe, String product) {
        super(id, name, imagePath);
        this.harvestWeight = harvestWeight;
        this.weight = 0;
        this.tipe = tipe;
        this.activeItems = new ArrayList<>();
        this.product = product;
        isProtected = false;
        isTrapped = false;
    }
    public Card getProduct() {return CardManager.getCard("product", this.product);}

    public void setProduct(String product) {this.product = product;}

    public int getHarvestWeight() {return harvestWeight;}

    public void setHarvestWeight(int harvestWeight) {this.harvestWeight = harvestWeight;}

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setProtected (boolean isProtected) {
        this.isProtected = isProtected;
    }

    public boolean getProtected () {
        return this.isProtected;
    }

    public void setTrapped (boolean isTrapped) {
        this.isTrapped = isTrapped;
    }

    public boolean getTrapped () {
        return this.isTrapped;
    }

    public void feed(Product product){
        if (Objects.equals(tipe, "Carnivore")){
            if (CarnivoreFood.contains(product.getName())){
                this.weight += product.getWeight();
            }
        }
        else if (Objects.equals(tipe, "Herbivore")){
            if (HerbivoreFood.contains(product.getName())){
                this.weight += product.getWeight();
            }
        }

        else{
            this.weight += product.getWeight();
        }
    }

    @Override
    public void applyItem(Item item)
    {
        item.useItemOnAnimal(this);
        this.activeItems.add(item);
    }

    public String getjenis(){
        return tipe;
    }

    @Override
    public String getType() {
        return "Animal";
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
            return CardManager.getCard("product", this.product);
        } else {
            return null;
        }
    }
    @Override
    public Integer getBerat_Umur(){
        return weight;
    }
    @Override
    public List<Item> getActiveItems() {
        return activeItems;
    }

    public List<String> CarnivoreFood = Arrays.asList("Sirip Hiu","Susu","Telur","Daging Kuda", "Daging Domba", "Daging Beruang");

    public List<String> HerbivoreFood = Arrays.asList("Jagung", "Labu", "Stroberi");
}
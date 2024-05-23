package com.istiqomah.if2210_tb2_iq9.model.card;

import java.util.ArrayList;
import java.util.List;

// Kelas Plant yang mengextends Card dan mengimplementasikan interface Harvestable
public class Plant extends Card implements Harvestable {
    private int age;
    private int harvestDuration;
    private boolean isProtected;
    private boolean isTrapped;
    private String namaProduct;
    private List<Item> activeItems;

    // Konstruktor
    public Plant(int id, String name, String imagePath, int age, int harvestDuration, String Namaproduct) {
        super(id, name, imagePath);
        this.harvestDuration = harvestDuration;
        this.age = 0;
        this.activeItems = new ArrayList<>();
        this.namaProduct = Namaproduct;
        this.isProtected = false;
        this.isTrapped = false;
    }

    // Metode getter dan setter
    public Card getProduct() {
        return CardManager.getCard("product", this.namaProduct);
    }

    public void setProduct(String product) {
        this.namaProduct = product;
    }

    public int getHarvestDuration() {
        return this.harvestDuration;
    }

    public void setHarvestDuration(int harvestDuration) {
        this.harvestDuration = harvestDuration;
    }

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setProtected(boolean isProtected) {
        this.isProtected = isProtected;
    }

    public boolean getProtected() {
        return this.isProtected;
    }

    public void setTrapped(boolean isTrapped) {
        this.isTrapped = isTrapped;
    }

    public boolean getTrapped() {
        return this.isTrapped;
    }

    // Metode untuk meningkatkan usia tanaman
    public void grow() {
        this.age++;
    }

    // Metode untuk mendapatkan tipe kartu
    public String getType() {
        return "Plant";
    }

    // Metode untuk menerapkan item ke tanaman
    @Override
    public void applyItem(Item item) {
        item.useItemOnPlant(this);
        this.activeItems.add(item);
    }

    // Metode untuk mendapatkan detail tanaman
    @Override
    public String getDetails() {
        return "Plant: " + getName() + ", Age: " + age + ", Harvest Duration: " + harvestDuration;
    }

    // Metode untuk memeriksa apakah tanaman dapat dipanen
    @Override
    public boolean isHarvestable() {
        return this.age >= this.harvestDuration;
    }

    // Metode untuk memanen tanaman
    @Override
    public Product harvest() {
        if (isHarvestable()) {
            return (Product) CardManager.getCard("product", this.namaProduct);
        } else {
            return null;
        }
    }

    // Metode untuk mendapatkan usia tanaman
    @Override
    public Integer getBerat_Umur() {
        return age;
    }

    // Metode untuk mendapatkan item aktif pada tanaman
    @Override
    public List<Item> getActiveItems() {
        return activeItems;
    }

    // Metode untuk mengatur usia tanaman
    @Override
    public void setBerat_Umur(Integer berat_umur) {
        this.age = berat_umur;
    }
}
package com.istiqomah.if2210_tb2_iq9.model.card;

import java.util.List;

// Kelas Product yang mengextends Card
public class Product extends Card {
    private int price;
    private int weight;
    private String name;

    // Konstruktor
    public Product(int id, String name, String imagePath, int price, int weight) {
        super(id, name, imagePath);
        this.price = price;
        this.weight = weight;
        this.name = name;
    }

    // Metode getter dan setter
    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Metode untuk mendapatkan tipe kartu
    @Override
    public String getType() {
        return "Product";
    }

    // Metode untuk menerapkan item ke produk yang mana tidak ada
    @Override
    public void applyItem(Item item) {

    }

    // Metode untuk mendapatkan detail produk
    @Override
    public String getDetails() {
        return name;
    }

    // Metode untuk mendapatkan berat/umur produk
    @Override
    public Integer getBerat_Umur() {
        return null;
    }

    // Metode untuk mendapatkan item aktif pada produk
    @Override
    public List<Item> getActiveItems() {
        return null;
    }

    // Metode untuk mengatur berat/umur produk
    @Override
    public void setBerat_Umur(Integer berat_umur) {
        ;
    }
}
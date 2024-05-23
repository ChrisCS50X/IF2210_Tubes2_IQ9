package com.istiqomah.if2210_tb2_iq9.model.card;

import java.util.List;

import com.istiqomah.if2210_tb2_iq9.model.ladang.Ladang;

// Kelas ini merepresentasikan item yang dapat digunakan pada hewan, tanaman, dan ladang
public class Item extends Card {
    private AnimalEffect animalEffect;
    private PlantEffect plantEffect;
    private LadangEffect ladangEffect;

    // Konstruktor
    public Item(int id, String name, String imagePath, AnimalEffect animalEffect, PlantEffect plantEffect,
            LadangEffect ladangEffect) {
        super(id, name, imagePath);
        this.animalEffect = animalEffect;
        this.plantEffect = plantEffect;
        this.ladangEffect = ladangEffect;
    }

    // Metode getter dan setter
    @Override
    public String getType() {
        return "Item";
    }

    // Metode untuk menggunakan item pada hewan
    public void useItemOnAnimal(Animal animal) {
        if (animalEffect != null) {
            animalEffect.applyEffect(animal);
        }
    }

    // Metode untuk menggunakan item pada tanaman
    public void useItemOnPlant(Plant plant) {
        if (plantEffect != null) {
            plantEffect.applyEffect(plant);
        }
    }

    // Metode untuk menggunakan item pada ladang
    public void useItemOnLadang(Ladang ladang, int x, int y) {
        if (ladangEffect != null) {
            ladangEffect.applyEffect(ladang, x, y);
        }
    }

    // Metode untuk menerapkan item ke produk
    public void applyItem(Item item) {
    }

    //
    public String getDetails() {
        return "Item : " + getName();

    }

    @Override
    public Integer getBerat_Umur() {
        return null;
    }

    @Override
    public List<Item> getActiveItems() {
        return null;
    }

    @Override
    public void setBerat_Umur(Integer berat_umur) {
        ;
    }
}

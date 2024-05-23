package com.istiqomah.if2210_tb2_iq9.model.card;

import java.util.List;

import com.istiqomah.if2210_tb2_iq9.model.ladang.Ladang;

public class Item extends Card {
    private AnimalEffect animalEffect;
    private PlantEffect plantEffect;
    private LadangEffect ladangEffect;

    public Item(int id, String name, String imagePath, AnimalEffect animalEffect, PlantEffect plantEffect, LadangEffect ladangEffect) {
        super(id, name, imagePath);
        this.animalEffect = animalEffect;
        this.plantEffect = plantEffect;
        this.ladangEffect = ladangEffect;
    }

    @Override
    public String getType() {
        return "Item";
    }

    public void useItemOnAnimal(Animal animal) {
        if (animalEffect != null) {
            animalEffect.applyEffect(animal);
        }
    }

    public void useItemOnPlant(Plant plant) {
        if (plantEffect != null) {
            plantEffect.applyEffect(plant);
        }
    }

    public void useItemOnLadang(Ladang ladang, int x, int y) {
        if (ladangEffect != null) {
            ladangEffect.applyEffect(ladang,x,y);
        }
    }

    public void applyItem(Item item) {}

    public String getDetails(){
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

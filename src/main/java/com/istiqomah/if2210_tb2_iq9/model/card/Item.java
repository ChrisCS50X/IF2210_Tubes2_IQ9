package com.istiqomah.if2210_tb2_iq9.model.card;

public class Item extends Card {
    private AnimalEffect animalEffect;
    private PlantEffect plantEffect;

    public Item(int id, String name, String imagePath, AnimalEffect animalEffect, PlantEffect plantEffect) {
        super(id, name, imagePath);
        this.animalEffect = animalEffect;
        this.plantEffect = plantEffect;
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
}

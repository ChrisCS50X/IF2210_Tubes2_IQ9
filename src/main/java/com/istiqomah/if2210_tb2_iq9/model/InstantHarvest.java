package com.istiqomah.if2210_tb2_iq9.model;

public class InstantHarvest implements AnimalEffect, PlantEffect {
    @Override
    public void applyEffect(Animal animal) {
        animal.setWeight(animal.getHarvestWeight());
    }

    @Override
    public void applyEffect(Plant plant) {
        plant.setAge(plant.getHarvestDuration());
    }
}

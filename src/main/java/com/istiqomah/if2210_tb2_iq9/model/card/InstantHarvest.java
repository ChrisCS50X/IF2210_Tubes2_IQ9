package com.istiqomah.if2210_tb2_iq9.model.card;

// Kelas InstantHarvest yang mengimplements AnimalEffect dan PlantEffect
public class InstantHarvest implements AnimalEffect, PlantEffect {
    // Menerapkan efek pada hewan
    @Override
    public void applyEffect(Animal animal) {
        animal.setWeight(animal.getHarvestWeight());
    }

    // Menerapkan efek pada tanaman
    @Override
    public void applyEffect(Plant plant) {
        plant.setAge(plant.getHarvestDuration());
    }
}

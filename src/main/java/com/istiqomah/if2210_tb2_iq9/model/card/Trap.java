package com.istiqomah.if2210_tb2_iq9.model.card;

// Kelas Trap yang mengimplements AnimalEffect dan PlantEffect
public class Trap implements AnimalEffect, PlantEffect {
    // Menerapkan efek pada tanaman
    @Override
    public void applyEffect(Plant plant) {
        plant.setTrapped(true);
    }

    // Menerapkan efek pada hewan
    @Override
    public void applyEffect(Animal animal) {
        animal.setTrapped(true);
    }
}

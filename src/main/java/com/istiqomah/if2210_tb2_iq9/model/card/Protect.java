package com.istiqomah.if2210_tb2_iq9.model.card;

// Kelas Protect yang mengimplements AnimalEffect dan PlantEffect
public class Protect implements AnimalEffect, PlantEffect {
    // Menerapkan efek pada hewan
    @Override
    public void applyEffect(Animal animal) {
        animal.setProtected(true);
    }

    // Menerapkan efek pada tanaman
    @Override
    public void applyEffect(Plant plant) {
        plant.setProtected(true);
    }
}

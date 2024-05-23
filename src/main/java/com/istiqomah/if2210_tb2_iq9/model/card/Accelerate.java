package com.istiqomah.if2210_tb2_iq9.model.card;

// Kelas Accelerate yang mengimplements AnimalEffect dan PlantEffect
public class Accelerate implements AnimalEffect, PlantEffect {
    // Menerapkan efek pada hewan
    @Override
    public void applyEffect(Animal animal) {
        // Menambahkan berat hewan sebanyak 8
        animal.setWeight(animal.getWeight() + 8);
    }

    // Menerapkan efek pada tanaman
    @Override
    public void applyEffect(Plant plant) {
        // Menambahkan umur tanaman sebanyak 2
        plant.setAge(plant.getAge() + 2);
    }
}

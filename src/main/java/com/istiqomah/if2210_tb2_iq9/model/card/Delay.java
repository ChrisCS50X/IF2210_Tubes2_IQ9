package com.istiqomah.if2210_tb2_iq9.model.card;

// Kelas Delay yang mengimplements AnimalEffect dan PlantEffect
public class Delay implements AnimalEffect, PlantEffect {
    // Menerapkan efek pada hewan
    @Override
    public void applyEffect(Animal animal) {
        int weight = animal.getWeight();
        if (weight >= 5) {
            animal.setWeight(weight - 5);
        } else {
            animal.setWeight(0);
        }
    }

    // Menerapkan efek pada tanaman
    @Override
    public void applyEffect(Plant plant) {
        int age = plant.getAge();
        if (age >= 2) {
            plant.setAge(age - 2);
        } else {
            plant.setAge(0);
        }
    }
}

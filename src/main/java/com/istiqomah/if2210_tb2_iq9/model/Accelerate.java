package com.istiqomah.if2210_tb2_iq9.model;

public class Accelerate implements AnimalEffect, PlantEffect {
    @Override
    public void applyEffect(Animal animal) {
        // Increase the weight of the animal by 8
        animal.setWeight(animal.getWeight() + 8);
    }

    @Override
    public void applyEffect(Plant plant) {
        // Increase the age of the plant by 2 turns
        plant.setAge(plant.getAge() + 2);
    }
}

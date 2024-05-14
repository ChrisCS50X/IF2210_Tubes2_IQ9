package com.istiqomah.if2210_tb2_iq9.model;

public class Delay implements AnimalEffect, PlantEffect{
    @Override
    public void applyEffect(Animal animal) {
        //
        int beratAnimal = animal.getWeight();
        if (beratAnimal <= 0) {
            animal.setWeight(0);
        }
        else{
            animal.setWeight(beratAnimal - 5);
        };
    }

    @Override
    public void applyEffect(Plant plant) {
       int umurPlant = plant.getAge();
       if (umurPlant <= 0) {
              plant.setAge(0);
       }
       else{
              plant.setAge(umurPlant - 2);
       }
    }
}

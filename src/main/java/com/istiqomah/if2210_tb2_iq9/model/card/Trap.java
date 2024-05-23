package com.istiqomah.if2210_tb2_iq9.model.card;


public class Trap implements AnimalEffect, PlantEffect{
    @Override
    public void applyEffect(Plant plant) {
        plant.setTrapped(true);
    }

    @Override
    public void applyEffect(Animal animal) {
        animal.setTrapped(true);
    }
}

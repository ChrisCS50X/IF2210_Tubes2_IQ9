package com.istiqomah.if2210_tb2_iq9.model.card;

public class Protect implements AnimalEffect, PlantEffect {
    @Override
    public void applyEffect(Animal animal) {
        animal.setProtected(true);
    }

    @Override
    public void applyEffect(Plant plant) {
        plant.setProtected(true);
    }
}

package com.istiqomah.if2210_tb2_iq9.model.card;

// Interface Harvestable untuk mengecek apakah suatu objek bisa di-harvest atau tidak
public interface Harvestable {
    boolean isHarvestable();

    Product harvest();
}
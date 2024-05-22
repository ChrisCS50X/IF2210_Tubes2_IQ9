package com.istiqomah.if2210_tb2_iq9.model.ladang;

import com.istiqomah.if2210_tb2_iq9.model.card.Card;
import com.istiqomah.if2210_tb2_iq9.model.observer.Subject;
import com.istiqomah.if2210_tb2_iq9.model.ladang.KomponenPetak;
import com.istiqomah.if2210_tb2_iq9.model.card.Product;
import com.istiqomah.if2210_tb2_iq9.model.card.Harvestable;
import com.istiqomah.if2210_tb2_iq9.model.card.Plant;
import com.istiqomah.if2210_tb2_iq9.model.card.Animal;

import java.util.List;
import java.util.Objects;

public class Ladang extends Subject{
    private KomponenPetak[][] grid;

    public Ladang() {
        grid = new KomponenPetak[4][5]; // Initialize the grid with null values
    }

    public boolean isPositionEmpty(int x, int y) {
        return grid[x][y] == null;
    }

    public void addCardToPosition(KomponenPetak component, int x, int y) {
        if (isPositionEmpty(x, y)) {
            grid[x][y] = component;
            notifyObservers("Card added to position (" + x + ", " + y + ")");
        } else {
            System.out.println("Position is not empty.");
        }
    }

    public void removeCardFromPosition(int x, int y) {
        if (!isPositionEmpty(x, y)) {
            grid[x][y] = null;
            notifyObservers("Card removed from position (" + x + ", " + y + ")");
        } else {
            System.out.println("No card at this position.");
        }
    }

    public KomponenPetak[][] getGrid() {
        return grid;
    }

    public KomponenPetak getCardAtPosition(int x, int y) {
        return grid[x][y];
    }

    public int[] getGridSize() {
        return new int[]{grid.length, grid[0].length};
    }

    public Card harvest(int x, int y) {
        if (!isPositionEmpty(x, y)) {
            KomponenPetak component = grid[x][y];
            if (component.isHarvestable()) {
                grid[x][y] = null; // Remove the card from the grid
                notifyObservers("Card harvested at position (" + x + ", " + y + ")");
                return component.harvest();
            } else {
                System.out.println("Card at this position is not harvestable.");
                return null;
            }
        } else {
            System.out.println("No card at this position to harvest.");
            return null;
        }
    }
}
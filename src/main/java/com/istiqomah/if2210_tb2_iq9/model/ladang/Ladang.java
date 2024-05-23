package com.istiqomah.if2210_tb2_iq9.model.ladang;

import com.istiqomah.if2210_tb2_iq9.model.card.Card;
import com.istiqomah.if2210_tb2_iq9.model.observer.Subject;
import com.istiqomah.if2210_tb2_iq9.model.card.Product;
import com.istiqomah.if2210_tb2_iq9.model.card.Harvestable;
import com.istiqomah.if2210_tb2_iq9.model.card.Plant;
import com.istiqomah.if2210_tb2_iq9.model.card.Animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ladang extends Subject {
    private KomponenPetak[][] grid;
    private BearAttack currentBearAttack;

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

    public Product harvest(int x, int y) {
        if (!isPositionEmpty(x, y)) {
            KomponenPetak component = grid[x][y];
            if (component.isHarvestable()) {
                removeCardFromPosition(x, y);
                notifyObservers("Card harvested at position (" + x + ", " + y + ")");
                return (Product) component.harvest();
            } else {
                System.out.println("Card at this position is not harvestable.");
                return null;
            }
        } else {
            System.out.println("No card at this position to harvest.");
            return null;
        }
    }

    public void initiateBearAttack() {
        List<int[]> attackPositions = getRandomSubgrid();
        currentBearAttack = new BearAttack(attackPositions);
    }

    private List<int[]> getRandomSubgrid() {
        List<int[]> positions = new ArrayList<>();
        Random rand = new Random();
        int maxWidth = 5;
        int maxHeight = 4;

        int startX = rand.nextInt(maxHeight);
        int startY = rand.nextInt(maxWidth);

        int width = rand.nextInt(3) + 1;  // Random width between 1 and 3
        int height = rand.nextInt(3) + 1; // Random height between 1 and 3

        // Ensure the area does not exceed 6 and remains within the grid boundaries
        while (width * height > 6 || startX + height > maxHeight || startY + width > maxWidth) {
            width = rand.nextInt(3) + 1;
            height = rand.nextInt(3) + 1;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                positions.add(new int[]{startX + i, startY + j});
            }
        }

        return positions;
    }

    public BearAttack getCurrentBearAttack() {
        return currentBearAttack;
    }

    public void updateAgePlant() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] instanceof Plant) {
                    Plant plant = (Plant) grid[i][j];
                    plant.grow();
                }
            }
        }
    }

    public static class BearAttack {
        private List<int[]> attackPositions;
        private int duration;

        public BearAttack(List<int[]> attackPositions) {
            this.attackPositions = attackPositions;
            this.duration = (new Random().nextInt(31)) + 30; // 30-60 seconds
        }

        public List<int[]> getAttackPositions() {
            return attackPositions;
        }

        public int getDuration() {
            return duration;
        }
    }
}

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

import java.util.Random;



import com.istiqomah.if2210_tb2_iq9.model.card.Card;

import java.util.ArrayList;
import java.util.List;

public class Ladang {
    private Card[][] grid;
    private BearAttack currentBear;

    public Ladang() {
<<<<<<< Updated upstream
        grid = new KomponenPetak[4][5]; // Initialize the grid with null values
=======
        grid = new Card[5][4]; // Initialize the grid with null values
>>>>>>> Stashed changes
    }

    public boolean isPositionEmpty(int x, int y) {
        return grid[x][y] == null;
    }

    public void addCardToPosition(Card card, int x, int y) {
        if (isPositionEmpty(x, y)) {
            grid[x][y] = card;
        } else {
            System.out.println("Position is not empty.");
        }
    }

    public void removeCardFromPosition(int x, int y) {
        if (!isPositionEmpty(x, y)) {
            grid[x][y] = null;
        } else {
            System.out.println("No card at this position.");
        }
    }

<<<<<<< Updated upstream
    public KomponenPetak[][] getGrid() {
        return grid;
    }

    public KomponenPetak getCardAtPosition(int x, int y) {
=======
    public Card getCardAtPosition(int x, int y) {
>>>>>>> Stashed changes
        return grid[x][y];
    }

    public int[] getGridSize() {
        return new int[]{grid.length, grid[0].length};
    }

    public Card harvest(int x, int y) {
        if (!isPositionEmpty(x, y)) {
            Card card = grid[x][y];
            if (card instanceof Harvestable) {
                grid[x][y] = null; // Remove the card from the grid
                return ((Harvestable) card).harvest();
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
        currentBear = new BearAttack(attackPositions);
    }

    private List<int[]> getRandomSubgrid() {
        List<int[]> positions = new ArrayList<>();
        int startX = (int) (Math.random() * 5);
        int startY = (int) (Math.random() * 4);
        int width = 1 + (int) (Math.random() * Math.min(3, 6 / Math.max(1, 6 / (startY + 1))));
        int height = 1 + (int) (Math.random() * Math.min(2, 6 / width));

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                int x = startX + i;
                int y = startY + j;
                if (x < 5 && y < 4) {
                    positions.add(new int[]{x, y});
                }
            }
        }
        return positions;
    }

    public BearAttack getCurrentBear() {
        return currentBear;
    }

    public static class BearAttack {
        private List<int[]> attackPositions;
        private int duration;

        public BearAttack(List<int[]> attackPositions) {
            this.attackPositions = attackPositions;
            this.duration = (int) (Math.random() * 31) + 30; // 30-60 seconds
        }

        public List<int[]> getAttackPositions() {
            return attackPositions;
        }

        public int getDuration() {
            return duration;
        }
    }
}

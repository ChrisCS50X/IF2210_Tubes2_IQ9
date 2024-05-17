package com.istiqomah.if2210_tb2_iq9.model.ladang;

import com.istiqomah.if2210_tb2_iq9.model.card.Card;
import com.istiqomah.if2210_tb2_iq9.model.card.Product;
import com.istiqomah.if2210_tb2_iq9.model.card.Harvestable;
import com.istiqomah.if2210_tb2_iq9.model.card.Plant;
import com.istiqomah.if2210_tb2_iq9.model.card.Animal;

import java.util.Objects;

public class Ladang {
    private Card[][] grid;

    public Ladang() {
        grid = new Card[5][4]; // Initialize the grid with null values
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

    public Card getCardAtPosition(int x, int y) {
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
            }
            else {
                System.out.println("Card at this position is not harvestable.");
                return null;
            }
        } else {
            System.out.println("No card at this position to harvest.");
            return null;
        }
    }
}
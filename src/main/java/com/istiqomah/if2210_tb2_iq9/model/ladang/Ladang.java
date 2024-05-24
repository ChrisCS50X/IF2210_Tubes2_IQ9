package com.istiqomah.if2210_tb2_iq9.model.ladang;

import com.istiqomah.if2210_tb2_iq9.model.observer.Subject;
import com.istiqomah.if2210_tb2_iq9.model.card.Product;
import com.istiqomah.if2210_tb2_iq9.model.card.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ladang extends Subject {
    private KomponenPetak[][] grid; // Grid untuk menyimpan komponen petak
    private BearAttack currentBearAttack; // Serangan beruang saat ini

    public Ladang() {
        grid = new KomponenPetak[4][5]; // Inisialisasi grid dengan nilai null
    }

    // Cek apakah posisi kosong
    public boolean isPositionEmpty(int x, int y) {
        return grid[x][y] == null;
    }

    // Menambahkan kartu ke posisi tertentu
    public void addCardToPosition(KomponenPetak component, int x, int y) {
        if (isPositionEmpty(x, y)) {
            grid[x][y] = component;
            notifyObservers("Card added to position (" + x + ", " + y + ")");
        } else {
            System.out.println("Position is not empty.");
        }
    }

    // Menghapus kartu dari posisi tertentu
    public void removeCardFromPosition(int x, int y) {
        if (!isPositionEmpty(x, y)) {
            grid[x][y] = null;
            notifyObservers("Card removed from position (" + x + ", " + y + ")");
        } else {
            System.out.println("No card at this position.");
        }
    }

    // Mendapatkan grid
    public KomponenPetak[][] getGrid() {
        return grid;
    }

    // Mendapatkan kartu di posisi tertentu
    public KomponenPetak getCardAtPosition(int x, int y) {
        return grid[x][y];
    }

    // Mendapatkan ukuran grid
    public int[] getGridSize() {
        return new int[] { grid.length, grid[0].length };
    }

    // Panen produk dari posisi tertentu
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

    // Memulai serangan beruang
    public void initiateBearAttack() {
        List<int[]> attackPositions = getRandomSubgrid();
        currentBearAttack = new BearAttack(attackPositions);
    }

    // Mendapatkan subgrid acak untuk serangan beruang
    private List<int[]> getRandomSubgrid() {
        List<int[]> positions = new ArrayList<>();
        Random rand = new Random();
        int maxWidth = 5;
        int maxHeight = 4;

        int startX = rand.nextInt(maxHeight);
        int startY = rand.nextInt(maxWidth);

        int width = rand.nextInt(3) + 1; // Lebar acak antara 1 dan 3
        int height = rand.nextInt(3) + 1; // Tinggi acak antara 1 dan 3

        // Pastikan area tidak melebihi 6 dan tetap dalam batas grid
        while (width * height > 6 || startX + height > maxHeight || startY + width > maxWidth) {
            width = rand.nextInt(3) + 1;
            height = rand.nextInt(3) + 1;
        }

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                positions.add(new int[] { startX + i, startY + j });
            }
        }

        return positions;
    }

    // Mendapatkan serangan beruang saat ini
    public BearAttack getCurrentBearAttack() {
        return currentBearAttack;
    }

    // Memperbarui usia tanaman
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

    // Kelas untuk serangan beruang
    public static class BearAttack {
        private List<int[]> attackPositions; // Posisi serangan
        private int duration; // Durasi serangan

        public BearAttack(List<int[]> attackPositions) {
            this.attackPositions = attackPositions;
            this.duration = (new Random().nextInt(31)) + 30; // 30-60 detik
        }

        // Mendapatkan posisi serangan
        public List<int[]> getAttackPositions() {
            return attackPositions;
        }

        // Mendapatkan durasi serangan
        public int getDuration() {
            return duration;
        }
    }
}
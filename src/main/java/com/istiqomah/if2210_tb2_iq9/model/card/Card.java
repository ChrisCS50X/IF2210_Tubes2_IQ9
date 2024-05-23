package com.istiqomah.if2210_tb2_iq9.model.card;

import com.istiqomah.if2210_tb2_iq9.model.ladang.KomponenPetak;
import javafx.scene.image.Image;
import java.util.List;

// Kelas abstrak Card yang mengimplements KomponenPetak
public abstract class Card implements KomponenPetak {
    private int id;
    private String name;
    private Image image;
    private String imagePath;

    // Konstruktor
    public Card(int id, String name, String imagePath) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.image = new Image(imagePath);
    }

    // Metode getter dan setter
    public String getName() {
        return name;
    }

    public Image getImage() {
        return image;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Metode abstrak untuk mendapatkan tipe kartu
    public abstract String getType();

    // Metode abstrak untuk menerapkan item ke kartu
    @Override
    public abstract void applyItem(Item item);

    // Metode abstrak untuk mendapatkan detail kartu
    @Override
    public abstract String getDetails();

    // Atribut abstrak untuk mengecek apakah kartu dapat diambil / dipanen
    @Override
    public boolean isHarvestable() {
        return false; // Default implementation
    }

    // Metode abstrak untuk memanen kartu
    @Override
    public Product harvest() {
        return null; // Default implementation
    }

    // Metode abstrak untuk mendapatkan berat/umur kartu
    public abstract Integer getBerat_Umur();

    // Metode abstrak untuk mendapatkan item aktif pada kartu
    public abstract List<Item> getActiveItems();

    // Metode abstrak untuk mengatur berat/umur kartu
    public abstract void setBerat_Umur(Integer berat_umur);

    // Metode untuk mengecek apakah kartu memiliki item tertentu
    public boolean hasItem(String itemName) {
        for (Item item : getActiveItems()) {
            if (item.getName().equals(itemName)) {
                return true;
            }
        }
        return false;
    }

}

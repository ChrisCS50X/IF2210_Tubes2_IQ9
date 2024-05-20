package com.istiqomah.if2210_tb2_iq9.model.card;

import com.istiqomah.if2210_tb2_iq9.model.ladang.KomponenPetak;
import javafx.scene.image.Image;

public abstract class   Card implements KomponenPetak {
    private int id;
    private String name;
    private Image image;
    private String imagePath;

    public Card(int id, String name, String imagePath) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
        this.image = new Image(imagePath);
    }

    public String getName() {return name;}

    public Image getImage() {return image;}

    public void setName(String name) {this.name = name;}

    public String getImagePath() {return imagePath;}

    public void setImagePath(String imagePath) {this.imagePath = imagePath;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public abstract String getType();
    @Override
    public abstract void applyItem(Item item);

    @Override
    public abstract String getDetails();

    @Override
    public boolean isHarvestable() {
        return false; // Default implementation
    }

    @Override
    public Card harvest() {
        return null; // Default implementation
    }
}



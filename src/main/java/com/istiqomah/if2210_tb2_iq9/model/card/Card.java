package com.istiqomah.if2210_tb2_iq9.model.card;

import javafx.scene.image.Image;

public abstract class Card {
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

    public void setName(String name) {this.name = name;}

    public String getImagePath() {return imagePath;}

    public void setImagePath(String imagePath) {this.imagePath = imagePath;}

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public abstract String getType();
}



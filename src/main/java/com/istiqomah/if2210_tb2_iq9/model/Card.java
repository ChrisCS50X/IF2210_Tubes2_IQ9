package com.istiqomah.if2210_tb2_iq9.model;

public abstract class Card {
    private int id;
    private String name;
    private String imagePath;

    public Card(int id, String name, String imagePath) {
        this.id = id;
        this.name = name;
        this.imagePath = imagePath;
    }

    public String getName() {
        return name;
    }

    public String getImagePath() {
        return imagePath;
    }

    public int getId() {
        return id;
    }

    public abstract String getType();
}



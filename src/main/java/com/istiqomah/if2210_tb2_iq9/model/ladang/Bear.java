package com.istiqomah.if2210_tb2_iq9.model.ladang;

public class Bear {
    private int x;
    private int y;
    private int duration; // Duration of the bear attack in seconds

    public Bear(int x, int y, int duration) {
        this.x = x;
        this.y = y;
        this.duration = duration;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
}

package com.istiqomah.if2210_tb2_iq9.model.ladang;

public class Bear {
    private int x; // Koordinat x posisi beruang
    private int y; // Koordinat y posisi beruang
    private int duration; // Durasi serangan beruang dalam detik

    // Konstruktor beruang dengan posisi dan durasi serangan
    public Bear(int x, int y, int duration) {
        this.x = x;
        this.y = y;
        this.duration = duration;
    }

    // Mendapatkan koordinat x posisi beruang
    public int getX() {
        return x;
    }

    // Mendapatkan koordinat y posisi beruang
    public int getY() {
        return y;
    }

    // Mendapatkan durasi serangan beruang
    public int getDuration() {
        return duration;
    }

    // Mengatur durasi serangan beruang
    public void setDuration(int duration) {
        this.duration = duration;
    }
}
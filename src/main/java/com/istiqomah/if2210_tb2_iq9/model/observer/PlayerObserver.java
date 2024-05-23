package com.istiqomah.if2210_tb2_iq9.model.observer;

// Kelas PlayerObserver yang mengimplements Observer
public class PlayerObserver implements Observer {
    @Override
    public void update(String message) {
        // Memberikan pesan update
        System.out.println("Player update: " + message);
    }
}
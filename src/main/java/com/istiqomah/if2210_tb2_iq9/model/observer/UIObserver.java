package com.istiqomah.if2210_tb2_iq9.model.observer;

// Kelas UIObserver yang mengimplements Observer
public class UIObserver implements Observer {
    @Override
    public void update(String message) {
        // Memberikan pesan update
        System.out.println("UI update: " + message);
    }
}


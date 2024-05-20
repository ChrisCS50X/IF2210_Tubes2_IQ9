package com.istiqomah.if2210_tb2_iq9.model.observer;

public class UIObserver implements Observer {
    @Override
    public void update(String message) {
        // Update the UI based on the message
        System.out.println("UI update: " + message);
    }
}


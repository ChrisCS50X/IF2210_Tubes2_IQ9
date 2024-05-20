package com.istiqomah.if2210_tb2_iq9.model.observer;

public class PlayerObserver implements Observer {
    @Override
    public void update(String message) {
        // Update the player status based on the message
        System.out.println("Player update: " + message);
    }
}
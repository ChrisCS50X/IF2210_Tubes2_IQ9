package com.istiqomah.if2210_tb2_iq9.model.observer;

import java.util.ArrayList;
import java.util.List;

// Kelas Subject yang mengimplements Observer
public abstract class Subject {
    private List<Observer> observers = new ArrayList<>();

    // Menambahkan observer
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    // Menghapus observer
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    // Memberitahu observer
    protected void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }
}

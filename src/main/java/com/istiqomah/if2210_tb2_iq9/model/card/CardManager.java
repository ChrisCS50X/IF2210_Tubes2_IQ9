package com.istiqomah.if2210_tb2_iq9.model.card;

import java.util.HashMap;
import java.util.Map;

import static com.istiqomah.if2210_tb2_iq9.model.card.Cardfactory.createCard;

public class CardManager {
    private static CardManager instance;
    private Map<String, Map<String, Card>> cardMap = new HashMap<>();

    private CardManager() {
        // Initialize card types
        initializeCardTypes();
    }

    public static CardManager getInstance() {
        if (instance == null) {
            instance = new CardManager();
        }
        return instance;
    }

    // Method to initialize all card types
    private void initializeCardTypes() {
        cardMap.put("animal", new HashMap<>());
        cardMap.put("plant", new HashMap<>());
        cardMap.put("item", new HashMap<>());
        cardMap.put("product", new HashMap<>());

        // Initialize cards for each type
        initializeAnimalCards();
        initializePlantCards();
        initializeItemCards();
        initializeProductCards();
    }

    // Method to add a card to the manager
    public void addCard(String type, String name, Card card) {
        cardMap.get(type).put(name, card);
    }

    // Method to get a card from the manager
    public Card getCard(String type, String name) {
        return cardMap.get(type).get(name);
    }

    // Method to remove a card from the manager
    public void removeCard(String type, String name) {
        cardMap.get(type).remove(name);
    }

    // Method to initialize animal cards
    private void initializeAnimalCards() {
        for (int i = 1; i <= 6; i++) {
            Card card = createCard("animal", i);
            addCard("animal",card.getName(), card);
        }
    }

    // Method to initialize plant cards
    private void initializePlantCards() {
        for (int i = 1; i <= 3; i++) {
            Card card = createCard("plant", i);
            addCard("plant", card.getName(), card);
        }
    }

    // Method to initialize item cards
    private void initializeItemCards() {
        for (int i = 1; i <= 3; i++) {
            Card card = createCard("item", i);
            addCard("item", card.getName(), card);
        }
    }

    // Method to initialize product cards
    private void initializeProductCards() {
        for (int i = 1; i <= 3; i++) {
            Card card = createCard("product", i);
            addCard("product", card.getName(), card);
    }
    }
}

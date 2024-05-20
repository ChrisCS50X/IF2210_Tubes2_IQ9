package com.istiqomah.if2210_tb2_iq9.model.card;

import java.util.HashMap;
import java.util.Map;

import static com.istiqomah.if2210_tb2_iq9.model.card.Cardfactory.createCard;

public class CardManager {
    private static CardManager instance;
    private Map<String, Map<Integer, Card>> cardMap = new HashMap<>();

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
    public void addCard(String type, int id, Card card) {
        cardMap.get(type).put(id, card);
    }

    // Method to get a card from the manager
    public Card getCard(String type, int id) {
        return cardMap.get(type).get(id);
    }

    // Method to remove a card from the manager
    public void removeCard(String type, int id) {
        cardMap.get(type).remove(id);
    }

    // Method to initialize animal cards
    private void initializeAnimalCards() {
        for (int i = 1; i <= 6; i++) {
            Card card = createCard("animal", i);
            addCard("animal", i, card);
        }
    }

    // Method to initialize plant cards
    private void initializePlantCards() {
        for (int i = 1; i <= 3; i++) {
            Card card = createCard("plant", i);
            addCard("plant", i, card);
        }
    }

    // Method to initialize item cards
    private void initializeItemCards() {
        for (int i = 1; i <= 3; i++) {
            Card card = createCard("item", i);
            addCard("item", i, card);
        }
    }

    // Method to initialize product cards
    private void initializeProductCards() {
        for (int i = 1; i <= 3; i++) {
            Card card = createCard("product", i);
            addCard("product", i, card);
    }
    }
}

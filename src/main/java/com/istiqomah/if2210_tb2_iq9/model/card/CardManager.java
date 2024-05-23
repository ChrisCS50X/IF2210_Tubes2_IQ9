package com.istiqomah.if2210_tb2_iq9.model.card;

import java.util.*;

import static com.istiqomah.if2210_tb2_iq9.model.card.Cardfactory.createCard;

public class CardManager {
    private static Map<String, Map<String, Card>> cardMap = new HashMap<>();
    private static List<String> AnimalName = new ArrayList<>();
    private static List<String> PlantName = new ArrayList<>();
    private static List<String> ItemName = new ArrayList<>();
    private static List<String> ProductName = new ArrayList<>();

    public CardManager() {
        // Initialize card types
        initializeCardTypes();
        initializeCardName();
    }

    private static void initializeCardName() {
        initializeAnimalName();
        initializePlantName();
        initializeItemName();
        initializeProductName();
    }

    private static void initializeAnimalName() {
        AnimalName.add("Beruang");
        AnimalName.add("Ayam");
        AnimalName.add("Kuda");
        AnimalName.add("Domba");
        AnimalName.add("Sapi");
        AnimalName.add("Hiu Darat");
    }

    private static void initializePlantName() {
        PlantName.add("Biji Jagung");
        PlantName.add("Biji Labu");
        PlantName.add("Biji Stroberi");
    }

    private static void initializeItemName() {
        ItemName.add("Accelerate");
        ItemName.add("Delay");
        ItemName.add("Instant Harvest");
        ItemName.add("Destroy");
        ItemName.add("Protect");
        ItemName.add("Trap");
    }


    private static void initializeProductName() {
        ProductName.add("Sirip Hiu");
        ProductName.add("Susu");
        ProductName.add("Daging Domba");
        ProductName.add("Daging Kuda");
        ProductName.add("Telur");
        ProductName.add("Jagung");
        ProductName.add("Labu");
        ProductName.add("Stroberi");
    }

    // Method to initialize all card types
    private static void initializeCardTypes() {
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
    public static void addCard(String type, String name, Card card) {
        cardMap.get(type).put(name, card);
    }

    // Method to get a card from the manager
    public static Card getCard(String type, String name) {
        int id = cardMap.get(type).get(name).getId();
        return Cardfactory.createCard(type,id);  }

    // Method to initialize animal cards
    private static void initializeAnimalCards() {
        for (int i = 1; i <= 6; i++) {
            Card card = createCard("animal", i);
            addCard("animal",card.getName(), card);
        }
    }

    // Method to initialize plant cards
    private static void initializePlantCards() {
        for (int i = 1; i <= 3; i++) {
            Card card = createCard("plant", i);
            addCard("plant", card.getName(), card);
        }
    }

    // Method to initialize item cards
    private static void initializeItemCards() {
        for (int i = 1; i <= 6; i++) {
            Card card = createCard("item", i);
            addCard("item", card.getName(), card);
        }
    }

    // Method to initialize product cards
    private static void initializeProductCards() {
        for (int i = 1; i <= 9; i++) {
            Card card = createCard("product", i);
            addCard("product", card.getName(), card);
        }
    }

    public static Card getRandomCardAnimalPlant() {
        Random rand = new Random();
        int index = rand.nextInt(2);
        if (index == 0) {
            return getCard("animal", AnimalName.get(rand.nextInt(AnimalName.size())));
        } else {
            return getCard("plant", PlantName.get(rand.nextInt(PlantName.size())));
        }
    }

    public static Card getRandomCard() {
        Random rand = new Random();
        int index = rand.nextInt(4);
        if (index == 0) {
            return getCard("animal", AnimalName.get(rand.nextInt(AnimalName.size())));
        } else if (index == 1) {
            return getCard("plant", PlantName.get(rand.nextInt(PlantName.size())));
        } else if (index == 2) {
            return getCard("item", ItemName.get(rand.nextInt(ItemName.size())));
        } else {
            return getCard("product", ProductName.get(rand.nextInt(ProductName.size())));
        }
    }

    public static List<String> getAnimalNames() {
        return AnimalName;
    }

    public static List<String> getPlantNames() {
        return PlantName;
    }

    public static List<String> getItemNames() {
        return ItemName;
    }

    public static List<String> getProductNames() { return ProductName; }
}

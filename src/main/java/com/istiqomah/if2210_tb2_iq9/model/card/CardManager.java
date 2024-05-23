package com.istiqomah.if2210_tb2_iq9.model.card;

import java.util.*;

import static com.istiqomah.if2210_tb2_iq9.model.card.Cardfactory.createCard;

// Class CardManager untuk mengatur kartu-kartu yang ada
public class CardManager {
    // Map untuk menyimpan kartu-kartu yang ada
    private static Map<String, Map<String, Card>> cardMap = new HashMap<>();
    private static List<String> AnimalName = new ArrayList<>();
    private static List<String> PlantName = new ArrayList<>();
    private static List<String> ItemName = new ArrayList<>();
    private static List<String> ProductName = new ArrayList<>();

    // Konstruktor
    public CardManager() {
        initializeCardTypes();
        initializeCardName();
    }

    // Method untuk menginisialisasi nama kartu
    private static void initializeCardName() {
        initializeAnimalName();
        initializePlantName();
        initializeItemName();
        initializeProductName();
    }

    // Method untuk menginisialisasi nama hewan
    private static void initializeAnimalName() {
        AnimalName.add("Beruang");
        AnimalName.add("Ayam");
        AnimalName.add("Kuda");
        AnimalName.add("Domba");
        AnimalName.add("Sapi");
        AnimalName.add("Hiu_Darat");
    }

    // Method untuk menginisialisasi nama tanaman
    private static void initializePlantName() {
        PlantName.add("Biji_Jagung");
        PlantName.add("Biji_Labu");
        PlantName.add("Biji_Stroberi");
    }

    // Method untuk menginisialisasi nama item
    private static void initializeItemName() {
        ItemName.add("Accelerate");
        ItemName.add("Delay");
        ItemName.add("Instant_Harvest");
        ItemName.add("Destroy");
        ItemName.add("Protect");
        ItemName.add("Trap");
    }

    // Method untuk menginisialisasi nama produk
    private static void initializeProductName() {
        ProductName.add("Sirip_Hiu");
        ProductName.add("Susu");
        ProductName.add("Daging_Domba");
        ProductName.add("Daging_Kuda");
        ProductName.add("Telur");
        ProductName.add("Jagung");
        ProductName.add("Labu");
        ProductName.add("Stroberi");
    }

    // Method untuk menginisialisasi kartu-kartu
    private static void initializeCardTypes() {
        cardMap.put("animal", new HashMap<>());
        cardMap.put("plant", new HashMap<>());
        cardMap.put("item", new HashMap<>());
        cardMap.put("product", new HashMap<>());

        initializeAnimalCards();
        initializePlantCards();
        initializeItemCards();
        initializeProductCards();
    }

    // Method untuk menambahkan kartu ke manager
    public static void addCard(String type, String name, Card card) {
        cardMap.get(type).put(name, card);
    }

    // Method untuk mendapatkan kartu
    public static Card getCard(String type, String name) {
        int id = cardMap.get(type).get(name).getId();
        return Cardfactory.createCard(type, id);
    }

    // Method untuk menginisialisasi kartu hewan
    private static void initializeAnimalCards() {
        for (int i = 1; i <= 6; i++) {
            Card card = createCard("animal", i);
            addCard("animal", card.getName(), card);
        }
    }

    // Method untuk menginisialisasi kartu tanaman
    private static void initializePlantCards() {
        for (int i = 1; i <= 3; i++) {
            Card card = createCard("plant", i);
            addCard("plant", card.getName(), card);
        }
    }

    // Method untuk menginisialisasi kartu item
    private static void initializeItemCards() {
        for (int i = 1; i <= 6; i++) {
            Card card = createCard("item", i);
            addCard("item", card.getName(), card);
        }
    }

    // Method untuk menginisialisasi kartu produk
    private static void initializeProductCards() {
        for (int i = 1; i <= 9; i++) {
            Card card = createCard("product", i);
            addCard("product", card.getName(), card);
        }
    }

    // Method untuk mendapatkan kartu hewan atau tanaman secara acak
    public static Card getRandomCardAnimalPlant() {
        Random rand = new Random();
        int index = rand.nextInt(2);
        if (index == 0) {
            return getCard("animal", AnimalName.get(rand.nextInt(AnimalName.size())));
        } else {
            return getCard("plant", PlantName.get(rand.nextInt(PlantName.size())));
        }
    }

    // Method untuk mendapatkan kartu animal atau produk secara acak
    public static Card getRandomAnimal() {
        Random rand = new Random();
        return getCard("animal", AnimalName.get(rand.nextInt(AnimalName.size())));
    }

    // Method untuk mendapatkan kartu tanaman secara acak
    public static Card getRandomPlant() {
        Random rand = new Random();
        return getCard("plant", PlantName.get(rand.nextInt(PlantName.size())));
    }

    // Method untuk mendapatkan kartu item secara acak
    public static Card getRandomItem() {
        Random rand = new Random();
        System.out.println("test: " + ItemName.size());
        return getCard("item", ItemName.get(rand.nextInt(ItemName.size())));
    }

    // Method untuk mendapatkan kartu produk secara acak
    public static Card getRandomProduct() {
        Random rand = new Random();
        return getCard("product", ProductName.get(rand.nextInt(ProductName.size())));
    }

    // Method untuk mendapatkan nama hewan
    public static List<String> getAnimalNames() {
        return AnimalName;
    }

    // Method untuk mendapatkan nama tanaman
    public static List<String> getPlantNames() {
        return PlantName;
    }

    // Method untuk mendapatkan nama item
    public static List<String> getItemNames() {
        return ItemName;
    }

    // Method untuk mendapatkan nama produk
    public static List<String> getProductNames() {
        return ProductName;
    }
}

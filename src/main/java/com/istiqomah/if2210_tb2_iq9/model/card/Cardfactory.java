package com.istiqomah.if2210_tb2_iq9.model.card;

public class Cardfactory {
    public static Card createCard(String type, int id) {
        switch (type.toLowerCase()) {
            case "animal":
                if (id == 1){
                    String name = "Beruang";
                    String imagepath ="file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Hewan/bear.png";
                    int weight = 0;
                    int harvestWeight = 25;
                    String tipe = "Omnivora";
                    String product = "Daging Beruang";
                    return new Animal(id,name, imagepath, weight, harvestWeight, tipe, product);

                }else if (id == 2){
                    String name = "Ayam";
                    String imagepath ="file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Hewan/chicken.png";
                    int weight = 0;
                    int harvestWeight = 5;
                    String tipe = "Omnivora";
                    String product = "Telur";
                    return new Animal(id,name, imagepath, weight, harvestWeight, tipe, product);

                }else if (id == 3){
                    String name = "Kuda";
                    String imagepath ="file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Hewan/horse.png";
                    int weight = 0;
                    int harvestWeight = 14;
                    String tipe = "Herbivora";
                    String product = "Daging Kuda";
                    return new Animal(id,name, imagepath, weight, harvestWeight, tipe, product);

                }else if (id == 4){
                    String name = "Domba";
                    String imagepath ="file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Hewan/sheep.png";
                    int weight = 0;
                    int harvestWeight = 12;
                    String tipe = "Herbivora";
                    String product = "Daging Domba";
                    return new Animal(id,name, imagepath, weight, harvestWeight, tipe, product);

                }else if (id == 5){
                    String name = "Sapi";
                    String imagepath ="file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Hewan/cow.png";
                    int weight = 0;
                    int harvestWeight = 10;
                    String tipe = "Herbivora";
                    String product = "Susu";
                    return new Animal(id,name, imagepath, weight, harvestWeight, tipe, product);

                }else if (id == 6){
                    String name = "Hiu Darat";
                    String imagepath ="file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Hewan/hiu darat.png";
                    int weight = 0;
                    int harvestWeight = 20;
                    String tipe = "Karnivora";
                    String product = "Sirip Hiu";
                    return new Animal(id,name, imagepath, weight, harvestWeight, tipe, product);
                }
            case "plant":
                if (id == 1){
                    return new Plant(id, "Biji Jagung", "file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Tanaman/corn seeds.png", 0, 3, "Jagung");
                }else if (id == 2){
                    return new Plant(id, "Biji Labu", "file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Tanaman/pumpkin seeds.png", 0, 5, "Labu");
                }else if (id == 3) {
                    return new Plant(id, "Biji Stroberi", "file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Tanaman/strawberry seeds.png", 0, 4, "Stroberi");
                }
            case "item":
                AnimalEffect animalEffect;
                PlantEffect plantEffect;
                LadangEffect ladangEffect;
                String name;
                String imagePath;
                if (id == 1) {
                    name = "Accelerate";
                    imagePath = "file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Item/Accelerate.png";
                    animalEffect = new Accelerate();
                    plantEffect = new Accelerate();
                    ladangEffect = null;
                    return new Item(id,name, imagePath, animalEffect, plantEffect, ladangEffect);

                } else if (id == 2) {
                    name = "Delay";
                    imagePath = "file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Item/Delay.png";
                    animalEffect = new Delay();
                    plantEffect = new Delay();
                    ladangEffect = null;
                    return new Item(id,name, imagePath, animalEffect, plantEffect, ladangEffect);

                } else if (id == 3) {
                    name = "Instant Harvest";
                    imagePath = "file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Item/Instant Harvest.png";
                    animalEffect = new InstantHarvest();
                    plantEffect = new InstantHarvest();
                    ladangEffect = null;
                    return new Item(id,name, imagePath, animalEffect, plantEffect, ladangEffect);

                }else if (id == 4) {
                    name = "Destroy";
                    imagePath = "file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Item/Destroy.png";
                    ladangEffect = new Destroy();
                    animalEffect = null;
                    plantEffect = null;
                    return new Item(id,name, imagePath, animalEffect, plantEffect, ladangEffect);
                }

                else if (id == 5) {
                    name = "Protect";
                    imagePath = "file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Item/Protect.png";
                    animalEffect = new Protect();
                    plantEffect = new Protect();
                    ladangEffect = null;
                    return new Item(id,name, imagePath, animalEffect, plantEffect, ladangEffect);

                }

                else if (id == 6) {
                    name = "Trap";
                    imagePath = "file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Item/bear trap.png";
                    animalEffect = new Trap();
                    plantEffect = new Trap();
                    ladangEffect = null;
                    return new Item(id,name, imagePath, animalEffect, plantEffect, ladangEffect);
                }

            case "product":
                if (id == 1) {
                    return new Product(id, "Sirip Hiu", "file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Produk/shark-fin.png", 500, 12);
                }else if (id == 2) {
                    return new Product(id, "Susu", "file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Produk/susu.png", 100, 4);
                }else if (id == 3) {
                    return new Product(id, "Daging Domba", "file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Produk/Daging_Domba.png", 120, 6);
                } else if (id == 4) {
                    return new Product(id, "Daging Kuda", "file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Produk/Daging_Kuda.png", 150, 8);
                } else if (id == 5) {
                    return new Product(id, "Telur", "file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Produk/telur.png", 50, 2);
                }else if (id == 6) {
                    return new Product(id, "Jagung", "file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Produk/corn.png",    150, 3);
                }else if (id == 7) {
                    return new Product(id, "Labu", "file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Produk/pumpkin.png",    500, 10);
                }else if (id == 8) {
                    return new Product(id, "Stroberi", "file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Produk/strawberry.png",    350, 5);
                } else if (id == 9)
                {
                    return new Product((id), "Daging Beruang", "file:src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Produk/Daging_Beruang.png", 500, 12);
                }
            default:
                throw new IllegalArgumentException("Unknown card type: " + type);
        }

    }
}

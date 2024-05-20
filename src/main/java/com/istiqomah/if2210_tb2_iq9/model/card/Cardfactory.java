package com.istiqomah.if2210_tb2_iq9.model.card;

public class Cardfactory {
    public static Card createCard(String type, int id) {
        switch (type.toLowerCase()) {
            case "animal":
                if (id == 1){
                    String name = "Beruang";
                    String imagepath ="src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Hewan/bear.png";
                    int weight = 0;
                    int harvestWeight = 25;
                    int tipe = 2;
                    return new Animal(id,name, imagepath, weight, harvestWeight, tipe);


                }else if (id == 2){
                    String name = "Ayam";
                    String imagepath ="src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Hewan/chicken.png";
                    int weight = 0;
                    int harvestWeight = 5;
                    int tipe = 2;
                    return new Animal(id,name, imagepath, weight, harvestWeight, tipe);

                }else if (id == 3){
                    String name = "Kuda";
                    String imagepath ="src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Hewan/horse.png";
                    int weight = 0;
                    int harvestWeight = 14;
                    int tipe = 1;
                    return new Animal(id,name, imagepath, weight, harvestWeight, tipe);

                }else if (id == 4){
                    String name = "Domba";
                    String imagepath ="src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Hewan/sheep.png";
                    int weight = 0;
                    int harvestWeight = 12;
                    int tipe = 1;
                    return new Animal(id,name, imagepath, weight, harvestWeight, tipe);

                }else if (id == 5){
                    String name = "Sapi";
                    String imagepath ="src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Hewan/cow.png";
                    int weight = 0;
                    int harvestWeight = 10;
                    int tipe = 1;
                    return new Animal(id,name, imagepath, weight, harvestWeight, tipe);

                }else if (id == 6){
                    String name = "Hiu Darat";
                    String imagepath ="src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Hewan/hiu darat.png";
                    int weight = 0;
                    int harvestWeight = 20;
                    int tipe = 0;
                    return new Animal(id,name, imagepath, weight, harvestWeight, tipe);
                }
            case "plant":
                if (id == 1){
                    return new Plant(id, "Biji Jagung", "src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Tanaman/corn seeds.png", 0, 3);
                }else if (id == 2){
                    return new Plant(id, "Biji Labu", "src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Tanaman/pumpkin seeds.png", 0, 5);
                }else if (id == 3) {
                    return new Plant(id, "Biji Stroberi", "src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Tanaman/strawberry seeds.png", 0, 4);
                }
            case "item":
                if (id == 1) {
                    return new Item(id, "Accelerate", "src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Item/Accelerate.png");
                } else if (id == 2) {
                    return new Item(id, "Delay", "src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Item/Delay.png");
                } else if (id == 3) {
                    return new Item(id, "Instant harvest", "src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Item/Instant Harvest.png");
                }else if (id == 4) {
                    return new Item(id, "Destroy", "src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Item/Destroy.png");
                }else if (id == 5) {
                    return new Item(id, "Protect", "src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Item/Protect.png");
                }else if (id == 6) {
                    return new Item(id, "Trap", "src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Item/bear trap.png");
                }
                return new Item(name);
            case "product":
                if (id == 1) {
                    return new Product(id, "sirip hiu", "src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Produk/shark-fin.png", 500, 12);
                }else if (id == 2) {
                    return new Product(id, "susu", "src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Produk/susu.png", 100, 4);
                }else if (id == 3) {
                    return new Product(id, "daging domba", "src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Produk/Daging_Domba.png", 120, 6);
                } else if (id == 4) {
                    return new Product(id, "daging kuda", "src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Produk/Daging_Kuda.png", 150, 8);
                } else if (id == 5) {
                    return new Product(id, "telur", "src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Produk/telur.png", 50, 2);
                }else if (id == 6) {
                    return new Product(id, "jagung", "src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Produk/corn.png",    150, 3);
                }else if (id == 7) {
                    return new Product(id, "labu", "src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Produk/pumpkin.png",    500, 10);
                }else if (id == 8) {
                    return new Product(id, "stroberi", "src/main/resources/com/istiqomah/if2210_tb2_iq9/card/image/Produk/strawberry.png",    350, 5);
                }
            default:
                throw new IllegalArgumentException("Unknown card type: " + type);
        }

    }
}

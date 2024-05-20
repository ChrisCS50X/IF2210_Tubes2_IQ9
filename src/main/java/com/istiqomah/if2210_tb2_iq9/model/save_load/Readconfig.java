package com.istiqomah.if2210_tb2_iq9.model.save_load;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Readconfig{

    private ArrayList<Triple<String, Integer, String>>  kordinat_Ladang;
    private ArrayList<Triple<String, Integer, String>> kordinat_Card;
    private Integer Jumlah_Gulden;
    private Integer Jumlah_Deck;
    private Integer Umur_Berat;
    private ArrayList<ArrayList<String>> Item;
    private int currentTurn;
    private int jumlah_item_shop;
    private ArrayList<Pair<String, Integer>> item_shop;

    public Readconfig(String folder_name) {
        kordinat_Ladang = new ArrayList<>();
        kordinat_Card = new ArrayList<>();
        Item = new ArrayList<>();
        item_shop = new ArrayList<>();
        readPlayer(folder_name + "/config1.txt");
        readgameState(folder_name + "/gamestate.txt");
    }

    void readPlayer(String file_name) {
        try {
            Scanner scanner = new Scanner(new File(file_name));

            // Reading Jumlah_Gulden
            if (scanner.hasNextInt()) {
                Jumlah_Gulden = scanner.nextInt();
            }

            // Reading Jumlah_Deck
            if (scanner.hasNextInt()) {
                Jumlah_Deck = scanner.nextInt();
            }

            // Reading Jumlah_Deck_Aktif (M)
            int M = 0;
            if (scanner.hasNextInt()) {
                M = scanner.nextInt();
            }

            // Reading M pairs of <LOKASI_KARTU> <KARTU_DECK_AKTIF>
            for (int i = 0; i < M; i++) {
                if (scanner.hasNext()) {
                    String lokasiKartu = scanner.next();
                    if (scanner.hasNext()) {
                        String kartuDeckAktif = scanner.next();
                        String row = lokasiKartu.substring(0, 1);
                        int col = Integer.parseInt(lokasiKartu.substring(1));
                        kordinat_Card.add(new Triple<>(row, col, kartuDeckAktif));
                    }
                }
            }

            // Reading Jumlah_Kartu_Ladang (L)
            int L = 0;
            if (scanner.hasNextInt()) {
                L = scanner.nextInt();
            }

            // Reading L sets of <LOKASI_KARTU> <KARTU_LADANG> <UMUR/BERAT> <JUMLAH_ITEM_AKTIF> <ITEM_1> ... <ITEM_J>
            for (int i = 0; i < L; i++) {
                if (scanner.hasNext()) {
                    String lokasiKartuLadang = scanner.next();
                    if (scanner.hasNext()) {
                        String kartuLadang = scanner.next();
                        if (scanner.hasNextInt()) {
                            int umurBerat = scanner.nextInt();
                            ArrayList<String> items = new ArrayList<>();
                            if (scanner.hasNextInt()) {
                                int jumlahItemAktif = scanner.nextInt();
                                for (int j = 0; j < jumlahItemAktif; j++) {
                                    if (scanner.hasNext()) {
                                        items.add(scanner.next());
                                    }
                                }
                            }
                            String row = lokasiKartuLadang.substring(0, 1);
                            int col = Integer.parseInt(lokasiKartuLadang.substring(1));
                            kordinat_Ladang.add(new Triple<>(row, col, kartuLadang));
                            Item.add(items);
                        }
                    }
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    void readgameState(String file_name) {
        try {
            Scanner scanner = new Scanner(new File(file_name));

            // Reading currentTurn
            if (scanner.hasNextInt()) {
                currentTurn = scanner.nextInt();
            }

            // Reading jumlah_item_shop
            if (scanner.hasNextInt()) {
                jumlah_item_shop = scanner.nextInt();
            }

            // Reading N pairs of <SHOP_ITEM> <JUMLAH>
            for (int i = 0; i < jumlah_item_shop; i++) {
                if (scanner.hasNext()) {
                    String shopItem = scanner.next();
                    if (scanner.hasNextInt()) {
                        int jumlah = scanner.nextInt();
                        item_shop.add(new Pair<>(shopItem, jumlah));
                    }
                }
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    // Getter methods for the variables (if needed)
    public Integer getJumlahGulden() {
        return Jumlah_Gulden;
    }

    public Integer getJumlahDeck() {
        return Jumlah_Deck;
    }

    public ArrayList<Triple<String, Integer, String>> getKordinatCard() {
        return kordinat_Card;
    }

    public ArrayList<Triple<String, Integer, String>> getKordinatLadang() {
        return kordinat_Ladang;
    }

    public ArrayList<ArrayList<String>> getItem() {
        return Item;
    }

    public int getCurrentTurn() {
        return currentTurn;
    }

    public int getJumlahItemShop() {
        return jumlah_item_shop;
    }

    public ArrayList<Pair<String, Integer>> getItemShop() {
        return item_shop;
    }
}


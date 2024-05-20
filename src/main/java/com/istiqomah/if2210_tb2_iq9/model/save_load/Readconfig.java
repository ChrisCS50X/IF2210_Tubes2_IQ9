package com.istiqomah.if2210_tb2_iq9.model.save_load;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class Readconfig{

    private ArrayList<Triple<Integer, Integer, String>>  kordinat_Ladang;
    private ArrayList<Pair<Integer, String>>  kordinat_Card;
    private Integer Jumlah_Gulden;
    private Integer Jumlah_Deck;
    private Integer Umur_Berat;
    private ArrayList<ArrayList<String>> Item;

    public Readconfig(String folder_name) {
        kordinat_Ladang = new ArrayList<>();
        kordinat_Card = new ArrayList<>();
        Item = new ArrayList<>();
        readPlayer(folder_name + "/config1.txt");
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
                if (scanner.hasNextInt()) {
                    int lokasiKartu = scanner.nextInt();
                    if (scanner.hasNext()) {
                        String kartuDeckAktif = scanner.next();
                        kordinat_Card.add(new Pair<>(lokasiKartu, kartuDeckAktif));
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
                if (scanner.hasNextInt()) {
                    int lokasiKartuLadang = scanner.nextInt();
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
                            kordinat_Ladang.add(new Triple<>(lokasiKartuLadang, umurBerat, kartuLadang));
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

    // Getter methods for the variables (if needed)
    public Integer getJumlahGulden() {
        return Jumlah_Gulden;
    }

    public Integer getJumlahDeck() {
        return Jumlah_Deck;
    }

    public ArrayList<Pair<Integer, String>> getKordinatCard() {
        return kordinat_Card;
    }

    public ArrayList<Triple<Integer, Integer, String>> getKordinatLadang() {
        return kordinat_Ladang;
    }

    public ArrayList<ArrayList<String>> getItem() {
        return Item;
    }
}


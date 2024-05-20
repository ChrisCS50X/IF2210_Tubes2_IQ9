package com.istiqomah.if2210_tb2_iq9.model.save_load;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class Mainconfig {
    public static void main(String[] args) {
        Readconfig config = new Readconfig("src/main/java/com/istiqomah/if2210_tb2_iq9/model/save_load/config");

        System.out.println("Jumlah Gulden: " + config.getJumlahGulden());
        System.out.println("Jumlah Deck: " + config.getJumlahDeck());

        System.out.println("Kordinat Card:");
        for (Triple<String, Integer, String> card : config.getKordinatCard()) {
            System.out.println(card.getLeft() + " " + card.getMiddle() + " " + card.getRight());
        }

        System.out.println("Kordinat Ladang:");
        for (Triple<String, Integer, String> ladang : config.getKordinatLadang()) {
            System.out.println(ladang.getLeft() + " " + ladang.getMiddle() + " " + ladang.getRight());
        }

        System.out.println("Items:");
        for (ArrayList<String> items : config.getItem()) {
            for (String item : items) {
                System.out.print(item + " ");
            }
            System.out.println();
        }

        System.out.println("Current Turn: " + config.getCurrentTurn());
        System.out.println("Jumlah Item di Shop: " + config.getJumlahItemShop());

        System.out.println("Item Shop:");
        for (Pair<String, Integer> item : config.getItemShop()) {
            System.out.println(item.getLeft() + " " + item.getRight());
        }
    }
}



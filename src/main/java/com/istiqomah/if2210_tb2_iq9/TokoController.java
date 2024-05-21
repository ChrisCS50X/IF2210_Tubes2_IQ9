//package com.istiqomah.if2210_tb2_iq9;
//
//import com.istiqomah.if2210_tb2_iq9.model.toko.Toko;
//import com.istiqomah.if2210_tb2_iq9.model.card.Product;
//import javafx.fxml.FXML;
//import javafx.scene.control.Label;
//import javafx.scene.layout.VBox;
//
//import java.util.Map;
//
//public class TokoController {
//    @FXML
//    private Label label;
//
//    @FXML
//    private VBox kartuTokoContainer;
//
//    private Toko toko;
//
//    public TokoController(Toko toko) {
//        this.toko = toko;
//    }
//
//    @FXML
//    public void initialize() {
//        label.setText("Welcome to Toko!");
//        updateKartuToko();
//    }
//
//    public void updateKartuToko() {
//        kartuTokoContainer.getChildren().clear(); // Clear the container
//        Map<Product, Integer> products = toko.getAvailableProducts(); // Get the available products
//        for (Product product : products.keySet()) {
//            // For each product, create a new KartuToko and add it to the container
//            KartuToko kartuToko = new KartuToko(product, products.get(product));
//            kartuTokoContainer.getChildren().add(kartuToko);
//        }
//    }
//}
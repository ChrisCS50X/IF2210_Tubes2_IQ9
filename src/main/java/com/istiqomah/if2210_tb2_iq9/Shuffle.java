package com.istiqomah.if2210_tb2_iq9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

public class Shuffle extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL fxmlUrl = getClass().getResource("fxml/shuffle-view.fxml");
        if (fxmlUrl == null) {
            throw new IOException("FXML file not found");
        }

        VBox root = FXMLLoader.load(fxmlUrl);

        Scene scene = new Scene(root, 800, 600);

        primaryStage.setScene(scene);
        primaryStage.setTitle("Card Reshuffle App");
        primaryStage.show();
    }
}

package com.istiqomah.if2210_tb2_iq9;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainCard extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("fxml/Card.fxml"));
        Scene scene = new Scene(fxmlLoader.load());

        // Example of updating card details
        CardController controller = fxmlLoader.getController();
        // Create example card objects and update details


        primaryStage.setTitle("Animal Detail");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

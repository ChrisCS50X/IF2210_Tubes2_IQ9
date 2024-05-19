package com.istiqomah.if2210_tb2_iq9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main_pluggin extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("fxml/plugin.fxml"));
        primaryStage.setTitle("Plugin Loader");
        primaryStage.setScene(new Scene(root, 550, 450));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

package com.istiqomah.if2210_tb2_iq9;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class MainSave extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/Save.fxml")));
        primaryStage.setTitle("Save Example");
        primaryStage.setScene(new Scene(root, 750, 650));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

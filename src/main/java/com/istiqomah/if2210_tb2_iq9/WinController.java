package com.istiqomah.if2210_tb2_iq9;

import com.istiqomah.if2210_tb2_iq9.model.player.Player;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class WinController {
    @FXML
    private Label player1gulden;
    @FXML
    private Label player2gulden;
    @FXML
    private Label winner;
    @FXML
    private Button closeButton;

    @FXML
    private void handleCloseButton() {
        Stage stage = (Stage) closeButton.getScene().getWindow();
        stage.close();

        MainPage.closeApplication();
    }

    public void setWinner() {
        int gulden1 = Player.getPlayerByIdx(0).getGulden();
        int gulden2 = Player.getPlayerByIdx(1).getGulden();
        System.out.println(gulden1);
        System.out.println(gulden2);
        if (gulden1 > gulden2) {
            winner.setText("Player 1 Wins!");
        } else if (gulden2 > gulden1) {
            winner.setText("Player 2 Wins!");
        } else {
            winner.setText("Both are winners!");
        }

        player1gulden.setText(String.valueOf(gulden1));
        player2gulden.setText(String.valueOf(gulden2));
    }
}


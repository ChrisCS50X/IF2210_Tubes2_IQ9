package com.istiqomah.if2210_tb2_iq9.model.card;

import com.istiqomah.if2210_tb2_iq9.model.ladang.*;

public class Destroy implements LadangEffect {
    @Override
    public void applyEffect(Ladang ladang, int x, int y) {
        // Destroy the ladang
        ladang.removeCardFromPosition(x,y);
    }
}

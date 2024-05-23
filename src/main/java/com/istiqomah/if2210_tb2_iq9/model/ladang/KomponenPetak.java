package com.istiqomah.if2210_tb2_iq9.model.ladang;
import java.lang.String;
import com.istiqomah.if2210_tb2_iq9.model.card.Item;
import com.istiqomah.if2210_tb2_iq9.model.card.Product;

// Implementasi Design Pattern Composite
public interface KomponenPetak {
    void applyItem(Item item);
    String getDetails();
    boolean isHarvestable();
    Product harvest();
}
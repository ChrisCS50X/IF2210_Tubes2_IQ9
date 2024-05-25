module com.istiqomah.if2210_tb2_iq9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.xml;

    // Add the line below to open the package to javafx.fxml
    opens com.istiqomah.if2210_tb2_iq9.model.card to javafx.fxml;
    opens com.istiqomah.if2210_tb2_iq9.model.ladang to javafx.fxml;
    opens com.istiqomah.if2210_tb2_iq9.model.player to javafx.fxml;
    uses com.istiqomah.if2210_tb2_iq9.plugin.SaveLoadPlugin;

    opens com.istiqomah.if2210_tb2_iq9 to javafx.fxml;
    exports com.istiqomah.if2210_tb2_iq9;
    exports com.istiqomah.if2210_tb2_iq9.plugin;
    exports com.istiqomah.if2210_tb2_iq9.model.card;
    exports com.istiqomah.if2210_tb2_iq9.model.ladang;
    exports com.istiqomah.if2210_tb2_iq9.model.player;
}
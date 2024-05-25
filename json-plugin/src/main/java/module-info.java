module com.example.jsonplugin {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.core;
    requires com.istiqomah.if2210_tb2_iq9;

    provides com.istiqomah.if2210_tb2_iq9.plugin.SaveLoadPlugin with com.example.jsonplugin.JsonSaveLoadPlugin;
    opens com.example.jsonplugin to javafx.fxml;
    exports com.example.jsonplugin;
}

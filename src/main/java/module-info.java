module com.istiqomah.if2210_tb2_iq9 {
    requires javafx.controls;
    requires javafx.fxml;
    requires jdk.compiler;


    opens com.istiqomah.if2210_tb2_iq9 to javafx.fxml;
    exports com.istiqomah.if2210_tb2_iq9;
}
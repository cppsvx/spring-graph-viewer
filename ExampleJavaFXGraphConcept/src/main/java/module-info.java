module com.craneos.sgv {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    requires gs.core;
    requires gs.ui.javafx;

    opens com.craneos.sgv to javafx.fxml;
    exports com.craneos.sgv;
    exports com.craneos.sgv.controller;
    opens com.craneos.sgv.controller to javafx.fxml;
}
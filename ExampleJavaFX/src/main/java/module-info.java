module com.craneos.javafx.examplejavafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;

    opens com.craneos.example to javafx.fxml;
    exports com.craneos.example;
}
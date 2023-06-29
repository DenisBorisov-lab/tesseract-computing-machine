module tesseract.computing.machine {
    requires javafx.controls;
    requires javafx.graphics;
    requires javafx.fxml;
    requires java.logging;
    requires lombok;
    requires tess4j;
    requires java.desktop;
    requires javafx.swing;

    opens com.example.application;
    opens com.example.application.services;
}
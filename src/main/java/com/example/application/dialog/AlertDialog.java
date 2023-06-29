package com.example.application.dialog;

import javafx.scene.control.Alert;

public class AlertDialog {
    public static void show(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Warning");
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}

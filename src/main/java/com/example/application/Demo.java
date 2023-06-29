package com.example.application;

import com.example.application.config.RootConfig;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import lombok.SneakyThrows;

public class Demo extends Application {
    public static void main(String[] args) {
        launch();
    }

    @Override
    @SneakyThrows
    public void start(Stage stage) {
        RootConfig rootConfig = new RootConfig(stage);
        HBox root = rootConfig.getConfiguration();
        root.setPadding(new Insets(30, 50, 50, 50));

        Scene scene = new Scene(root, 900, 550);
        stage.getIcons().add(new Image("search_icon.png"));
        stage.setTitle("Tesseract-CM");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void openDocument(String path) {
        getHostServices().showDocument(path);
    }
}
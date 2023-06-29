package com.example.application.config;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class ComponentGenerator {
    public ProgressBar generateProgressBar() {
        ProgressBar bar = new ProgressBar();
        bar.setPrefWidth(150);
        bar.setPrefHeight(22);
        bar.setVisible(false);
        return bar;
    }

    public Button generateButton(String text) {
        Button button = new Button();
        button.setText(text);
        button.setPrefWidth(300);
        button.setPrefHeight(65);
        button.setStyle("-fx-font-size:15");
        return button;
    }

    public ImageView setImageView() {
        Image image = new Image("/no_image.png");
        ImageView view = new ImageView();
        view.setImage(image);
        view.setFitHeight(275);
        view.setFitWidth(300);

        view.setPreserveRatio(true);
        return view;
    }

    public Label generateLabel() {
        javafx.scene.control.Label banner = new Label();
        banner.setText("Processed text: ");
        banner.setStyle("-fx-font-size:30");
        return banner;
    }

    public TextArea generateTextArea() {
        TextArea textArea = new TextArea();
        textArea.setPrefColumnCount(20);
        textArea.setPrefRowCount(50);
        textArea.setStyle("-fx-font-size:15");
        return textArea;
    }
}

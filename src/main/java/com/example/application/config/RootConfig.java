package com.example.application.config;

import com.example.application.dialog.AlertDialog;
import com.example.application.services.ImageService;
import com.example.application.services.ScanService;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class RootConfig {

    private final Stage stage;
    private final ImageView imageView;
    private final Button getFromBufferButton;
    private final Button searchImageFromProviderButton;
    private final javafx.scene.control.Label label;
    private final TextArea area;
    private final ProgressBar progressBar;
    private final HBox board;
    private final ComponentGenerator generator;

    public RootConfig(Stage stage) {
        this.stage = stage;
        this.generator = new ComponentGenerator();
        this.imageView = generator.setImageView();
        this.getFromBufferButton = generator.generateButton("Скопировать из буфера обмена");
        this.searchImageFromProviderButton = generator.generateButton("Открыть проводник");
        this.label = generator.generateLabel();
        this.area = generator.generateTextArea();
        this.progressBar = generator.generateProgressBar();
        this.board = configureBoard();
        this.getFromBufferButton.setOnAction(actionEvent -> {
            Image image = Clipboard.getSystemClipboard().getImage();
            if (image != null) {
                ImageService.save(image);
                File file = ImageService.getImage();
                imageView.setImage(image);
                progressBar.setVisible(true);
                Thread thread = new Thread(() -> {
                    String result = new ScanService().scan(file);
                    area.setText(result);
                    progressBar.setVisible(false);
                });
                thread.start();
            } else {
                String header = "Clipboard error";
                String content = "Clipboard is empty or there is no image in the clipboard!";
                AlertDialog.show(header, content);
            }
        });

        this.searchImageFromProviderButton.setOnAction(actionEvent -> {
            try {
                FileChooser fileChooser = new FileChooser();
                File selectedFile = fileChooser.showOpenDialog(stage);
                String path = selectedFile.getAbsolutePath();
                String end = path.substring(path.length() - 3);
                List<String> extensions = List.of("img", "jpg", "png");
                if (extensions.contains(end)) {
                    imageView.setImage(new Image(path));
                    progressBar.setVisible(true);
                    Thread thread = new Thread(() -> {
                        String result = new ScanService().scan(selectedFile);
                        area.setText(result);
                        progressBar.setVisible(false);
                    });
                    thread.start();
                } else {
                    String header = "Incorrect Extension!";
                    String content = "You need to use extensions from the following list: img, jpg, png!";
                    AlertDialog.show(header, content);
                }

            } catch (Exception ex) {
                System.out.println("Window is cancelled");
            }
        });

        this.imageView.addEventHandler(MouseEvent.MOUSE_CLICKED, mouseEvent -> {
            Desktop dt = Desktop.getDesktop();
            try {
                dt.open(new File(imageView.getImage().getUrl().substring(6)));
            } catch (Exception e) {
                try {
                    dt.open(new File(imageView.getImage().getUrl()));
                } catch (Exception ex) {
                    Image image = imageView.getImage();
                    ImageService.save(image);
                    File file = ImageService.getImage();
                    try {
                        dt.open(file);
                    } catch (IOException exc) {
                        throw new RuntimeException(exc);
                    }
                }
            }
        });
    }

    public HBox getConfiguration() {
        GridPane panel = configurePanel();
        VBox dataView = configureDataView();
        return new HBox(50, panel, dataView);
    }

    private GridPane configurePanel() {
        VBox buttons = configureButtons();
        GridPane pane = configureGridPane();
        pane.add(imageView, 0, 0);
        pane.add(buttons, 0, 1);
        return pane;
    }

    private HBox configureBoard() {
        HBox box = new HBox(50, label, progressBar);
        box.setPrefWidth(430);
        box.setPrefHeight(label.getPrefHeight());
        box.setAlignment(Pos.CENTER);
        return box;
    }

    private VBox configureButtons() {
        VBox buttons = new VBox(15, searchImageFromProviderButton, getFromBufferButton);
        buttons.setPrefWidth(300);
        buttons.setPrefHeight(145);
        return buttons;
    }


    private GridPane configureGridPane() {
        GridPane pane = new GridPane();

        ColumnConstraints column1 = new ColumnConstraints();
        column1.setPercentWidth(100);
        pane.getColumnConstraints().add(column1);

        RowConstraints row1 = new RowConstraints();
        row1.setPercentHeight(70);
        pane.getRowConstraints().add(row1);


        RowConstraints row2 = new RowConstraints();
        row2.setPercentHeight(30);
        pane.getRowConstraints().add(row2);

        GridPane.setHalignment(imageView, HPos.CENTER);
        GridPane.setValignment(imageView, VPos.CENTER);

        return pane;
    }


    public VBox configureDataView() {
        VBox dataView = new VBox(10, board, area);
        dataView.setPrefHeight(450);
        dataView.setPrefWidth(430);
        return dataView;
    }
}

package com.example.application.services;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import lombok.SneakyThrows;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class ImageService {
    private static final String PATH = "data.png";

    @SneakyThrows
    public static void save(Image image) {
        File outputFile = new File(PATH);
        BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
        ImageIO.write(bImage, "png", outputFile);
    }

    public static File getImage() {
        return new File(PATH);
    }
}

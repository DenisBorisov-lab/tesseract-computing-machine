package com.example.application.services;

import lombok.SneakyThrows;
import net.sourceforge.tess4j.Tesseract;

import java.io.File;

public class ScanService {

    @SneakyThrows
    public String scan(File image) {
        Tesseract tesseract = new Tesseract();
        tesseract.setDatapath("models");
        tesseract.setLanguage("rus+eng+jpn+ita+deu+chi_sim+chi_tra+fra");
        tesseract.setPageSegMode(1);
        tesseract.setOcrEngineMode(1);
        return tesseract.doOCR(image);
    }
}

package com.lab01;

import org.apache.commons.io.FileUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class Main {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java -jar FileDownloader.jar <file_url>");
            return;
        }

        String fileUrl = args[0];

        try {
            // Lấy tên file từ URL
            String fileName = fileUrl.substring(fileUrl.lastIndexOf("/") + 1);
            File outputFile = new File(fileName);

            // Tải file
            System.out.println("Downloading: " + fileUrl);
            FileUtils.copyURLToFile(new URL(fileUrl), outputFile);
            System.out.println("Download complete: " + outputFile.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error downloading file: " + e.getMessage());
        }
    }
}
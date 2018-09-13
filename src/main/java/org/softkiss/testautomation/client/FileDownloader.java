package org.softkiss.testautomation.client;

import io.qameta.allure.Step;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.log4j.Logger;

public class FileDownloader {

    private static final int BUFFER_SIZE = 4096;
    private final static Logger LOGGER = Logger.getLogger(FileDownloader.class);

    private String fileURL;
    private String fileName;

    @Step
    public static void downloadFile(String fileURL, String fileName) throws IOException {
        URL url = new URL(fileURL);
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        int responseCode = httpConn.getResponseCode();

        // always check HTTP response code first
        if (responseCode == HttpURLConnection.HTTP_OK) {
            // opens input stream from the HTTP connection
            InputStream inputStream = httpConn.getInputStream();
            String saveFilePath = fileName;

            // opens an output stream to save into file
            FileOutputStream outputStream = new FileOutputStream(saveFilePath);

            int bytesRead = -1;
            byte[] buffer = new byte[BUFFER_SIZE];
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
            }
            outputStream.close();
            inputStream.close();
            LOGGER.info("File downloaded from url " + fileURL + " as " + fileName);
        } else {
            LOGGER.error("No file to download. Server replied HTTP code: " + responseCode);
        }
        httpConn.disconnect();
        LOGGER.info("HTTP connection is closed");
    }
}

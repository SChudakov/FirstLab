package com.sschudakov.utils;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;

/**
 * Created by Semen Chudakov on 17.09.2017.
 */
public class SiteDownloader {

    public static void downloadHTMLText(String url, String path) {

        File file = new File(path);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        URL site = null;
        try {
            site = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(site.openStream(), StandardCharsets.UTF_8));
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {

            String line;

            while ((line = reader.readLine()) != null) {
                writer.write(line);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

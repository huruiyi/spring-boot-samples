package com.example.http;

import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtils {

    public static void main(String[] args) {
        downloadFile("https://archive.apache.org/dist/zookeeper/zookeeper-3.4.14/zookeeper-3.4.14.tar.gz", "zookeeper-3.4.14.tar.gzxxxxxxxxxxx");
        //  downloadFile("https://archive.apache.org/dist/zookeeper/zookeeper-3.4.14/apache-zookeeper-3.4.14.tar.gz","zookeeper-3.4.14.tar.gzxxxxxxxxxxx");
    }

    public static void downloadFile(String downloadUrl, String path) {
        System.out.println(path);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            URL url = new URL(downloadUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            inputStream = new BufferedInputStream(connection.getInputStream());
            File file = new File(path);
            if (!file.exists()) {
                outputStream = new FileOutputStream(file);
                byte[] buffer = new byte[1024 * 1024 * 2];
                int len = 0;
                while ((len = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, len);
                }
                connection.disconnect();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(outputStream);
            IOUtils.closeQuietly(inputStream);
        }
    }
}

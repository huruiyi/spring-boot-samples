package com.example.http;

import com.example.xml.ch.xpath.Test;
import org.apache.commons.compress.utils.IOUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class FileDownload {

    public static void main(String[] args) throws IOException {
        List<String> versions = getUrl();
        for (String version : versions) {
            version = version.replace("v", "");
            List<String> urls = new ArrayList<>();
            urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/src/apache-tomcat-{0}-src.tar.gz", version));
            urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/src/apache-tomcat-{0}-src.zip", version));
            urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/apache-tomcat-{0}-deployer.tar.gz", version));
            urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/apache-tomcat-{0}-deployer.zip", version));
            urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/apache-tomcat-{0}-fulldocs.tar.gz", version));
            urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/apache-tomcat-{0}-windows-x64.zip", version));
            urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/apache-tomcat-{0}-windows-x86.zip", version));
            urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/apache-tomcat-{0}.exe", version));
            urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/apache-tomcat-{0}.tar.gz", version));
            urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/apache-tomcat-{0}.zip", version));
            urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/embed/apache-tomcat-{0}-embed.tar.gz", version));
            urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/embed/apache-tomcat-{0}-embed.zip", version));
            urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/extras/catalina-jmx-remote.jar", version));
            urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/extras/catalina-ws.jar", version));
            urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/extras/tomcat-juli-adapters.jar", version));
            urls.add(MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/extras/tomcat-juli.jar", version));
            String finalVersion = version;
            urls.forEach(url -> {
                String fileDirPath = System.getProperties().get("user.dir") + "\\" + "tomcat9" + "\\" + finalVersion + "\\";
                File file = new File(fileDirPath);
                try {
                    Thread.sleep(1000);
                    if (!file.exists() && !file.isDirectory()) {
                        file.mkdirs();
                    }
                    new Thread(() -> downloadFile1(url, fileDirPath + getFileName(url, finalVersion))).start();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            });
        }
    }

    static String getFileName(String url, String version) {
        String extrasStr = MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/extras/", version);
        String embedStr = MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/embed/", version);
        String binStr = MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/bin/", version);
        String srcStr = MessageFormat.format("https://archive.apache.org/dist/tomcat/tomcat-9/v{0}/src/", version);
        if (url.contains(extrasStr)) {
            return url.replace(extrasStr, "");
        } else if (url.contains(embedStr)) {
            return url.replace(embedStr, "");
        } else if (url.contains(binStr)) {
            return url.replace(binStr, "");
        } else if (url.contains(srcStr)) {
            return url.replace(srcStr, "");
        }
        return "";
    }


    public static void downloadFile1(String downloadUrl, String path) {
        System.out.println(path);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            URL url = new URL(downloadUrl);
            //这里没有使用 封装后的ResponseEntity 就是也是因为这里不适合一次性的拿到结果，放不下content,会造成内存溢出
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            //使用bufferedInputStream 缓存流的方式来获取下载文件，不然大文件会出现内存溢出的情况
            inputStream = new BufferedInputStream(connection.getInputStream());
            File file = new File(path);
            if (!file.exists()) {
                outputStream = new FileOutputStream(file);
                //这里也很关键每次读取的大小为5M 不一次性读取完
                byte[] buffer = new byte[1024 * 1024 * 2];// 2MB
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

    static List<String> getUrl() throws IOException {
        List<String> versions = new ArrayList<>();
        String path = Test.class.getClassLoader().getResource("tomcat/v9.txt").getPath();
        File file = new File(path);

        BufferedReader br = new BufferedReader(new FileReader(file));

        String version;
        while ((version = br.readLine()) != null) {
            versions.add(version);
        }
        br.close();

        return versions;
    }
}

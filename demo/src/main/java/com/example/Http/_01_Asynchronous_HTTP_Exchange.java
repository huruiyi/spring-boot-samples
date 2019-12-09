package com.example.Http;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.nio.client.CloseableHttpAsyncClient;
import org.apache.http.impl.nio.client.HttpAsyncClients;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

public class _01_Asynchronous_HTTP_Exchange {
    /**
     * This example demonstrates a basic asynchronous HTTP request / response exchange.
     * Response content is buffered in memory for simplicity.
     */

    public static void main(String[] args) throws ExecutionException, InterruptedException, IOException {
        CloseableHttpAsyncClient httpclient = HttpAsyncClients.createDefault();
        try {
            httpclient.start();
            HttpGet request = new HttpGet("http://httpbin.org/get");
            Future<HttpResponse> future = httpclient.execute(request, null);
            HttpResponse response = future.get();

            System.out.println("Response: " + response.getStatusLine());
            System.out.println("Shutting down");
        } finally {
            httpclient.close();
        }
        System.out.println("Done");
    }
}

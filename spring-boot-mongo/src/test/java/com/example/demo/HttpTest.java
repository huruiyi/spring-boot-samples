package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class HttpTest {

    @Test
    public void test_upload1() {
        Mono<String> resp = WebClient.create("http://localhost:9100/upload").post()
                .accept(MediaType.MULTIPART_FORM_DATA)
                .body(BodyInserters.fromMultipartData(getFromMultipartData()))
                .retrieve()
                .bodyToMono(String.class);
        System.out.println("Result:" + resp.block());
    }

    @Test
    public void test_upload2() {
        Mono<String> resp = WebClient.create().post().uri("http://localhost:9100/upload").contentType(MediaType.MULTIPART_FORM_DATA).body(BodyInserters.fromMultipartData(getFromMultipartData())).retrieve().bodyToMono(String.class);
        System.out.println("Result:" + resp.block());
    }

    @Test
    public void test_download1() throws IOException {
        // 下载文件
        Mono<ClientResponse> resp2 = WebClient.create().get()
                .uri("https://fairy.vip/images/A4.png")
                .accept(MediaType.APPLICATION_OCTET_STREAM).exchange();

        ClientResponse clientResponse = resp2.block();
        if (clientResponse.statusCode().equals(HttpStatus.OK)) {
            Resource resource = clientResponse.bodyToMono(Resource.class).block();
            resourceToFile(resource, "d:/A4.png");
        }
    }

    @Test
    public void test_download2() throws IOException {
        // 下载文件
        Mono<Resource> resp3 = WebClient.create().get()
                .uri("https://fairy.vip/images/A4.jpg")
                .accept(MediaType.APPLICATION_OCTET_STREAM)
                .exchangeToMono(response -> {
                    if (response.statusCode().equals(HttpStatus.OK)) {
                        System.out.println("ok...");
                        return response.bodyToMono(Resource.class);
                    } else {
                        System.out.println("not ok...");
                        return response.createException().flatMap(Mono::error);
                    }
                });
        Resource resource = resp3.block();
        resourceToFile(resource, "d:/A4.jpg");
    }

    public MultiValueMap<String, Object> getFromMultipartData() {
        // 上传图片
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_JPEG);
        HttpEntity<ClassPathResource> entity = new HttpEntity<>(new ClassPathResource("static/images/github.png"), headers);

        MultiValueMap<String, Object> parts = new LinkedMultiValueMap<>();
        parts.add("file", entity);
        return parts;
    }

    public void resourceToFile(Resource resource, String destination) throws IOException {
        InputStream input = resource.getInputStream();
        int index;
        byte[] bytes = new byte[1024];
        FileOutputStream downloadFile = new FileOutputStream(destination);
        while ((index = input.read(bytes)) != -1) {
            downloadFile.write(bytes, 0, index);
            downloadFile.flush();
        }
        downloadFile.close();
        input.close();
    }

}

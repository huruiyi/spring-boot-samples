package com.example.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;


@JsonIgnoreProperties(ignoreUnknown = true)
public class Greeting {
    private long id;
    private String content;

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Greeting{" + "id=" + id + ", content='" + content + '\'' + '}';
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }
}

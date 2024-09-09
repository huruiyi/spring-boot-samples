package com.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Greeting {

    private final long id;
    private final String content;

    public Greeting(long id, String content) {
        this.id = id;
        this.content = content;
    }

}

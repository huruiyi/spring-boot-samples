package com.example.websocketchatroom.model;

/*
 * The endpoint accepts messages containing the sender name
 * and a text in a STOMP message whose body is a JSON object
 *
 * By default, Spring will use the Jackson library to convert
 * our model object to and from JSON.
 */
public class Message {
    private String from;
    private String text;

    public String getFrom() {
        return from;
    }

    public void setFrom(final String from) {
        this.from = from;
    }

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }
}

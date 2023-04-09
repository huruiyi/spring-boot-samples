package com.example.websocketstomp.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Stock implements Serializable {
    private static final long serialVersionUID = 1L;
    private static final String DATE_FORMAT = "MMM dd yyyy HH:mm:ss";

    private String code;
    private double price;
    private LocalDateTime dateTime;

    public Stock() {
    }

    public Stock(final String code, final double price) {
        this.code = code;
        this.price = price;
    }

    public String getDateTimeFormatted() {
        return dateTime.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(final LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}

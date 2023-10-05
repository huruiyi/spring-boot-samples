package com.example.batch.model;

import lombok.Data;

@Data
public class Singer {

  private String firstName;
  private String lastName;
  private String song;

  public Singer() {
  }

  public Singer(String firstName, String lastName, String song) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.song = song;
  }

  @Override
  public String toString() {
    return "firstName: " + firstName + ", lastName: " + lastName + ", song: " + song;
  }
}

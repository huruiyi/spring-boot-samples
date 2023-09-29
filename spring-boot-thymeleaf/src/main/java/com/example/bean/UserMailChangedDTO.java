package com.example.bean;

public class UserMailChangedDTO {

  private boolean firstName;
  private boolean lastName;
  private boolean displayName;
  private boolean office;
  private boolean email;

  public UserMailChangedDTO(boolean firstName, boolean lastName, boolean displayName, boolean office, boolean email) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.displayName = displayName;
    this.office = office;
    this.email = email;
  }

  public boolean isFirstName() {
    return firstName;
  }

  public void setFirstName(boolean firstName) {
    this.firstName = firstName;
  }

  public boolean isLastName() {
    return lastName;
  }

  public void setLastName(boolean lastName) {
    this.lastName = lastName;
  }

  public boolean isDisplayName() {
    return displayName;
  }

  public void setDisplayName(boolean displayName) {
    this.displayName = displayName;
  }

  public boolean isOffice() {
    return office;
  }

  public void setOffice(boolean office) {
    this.office = office;
  }

  public boolean isEmail() {
    return email;
  }

  public void setEmail(boolean email) {
    this.email = email;
  }
}

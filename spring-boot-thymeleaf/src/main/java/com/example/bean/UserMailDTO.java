package com.example.bean;

public class UserMailDTO {

  private String samAccountName;
  private String email;
  private String firstName;
  private String lastName;
  private String displayName;
  private String office;
  private String givenName;
  private String sn;
  private String physicalDeliveryOfficeName;
  private UserMailChangedDTO changed;

  public UserMailDTO(String samAccountName, String email, String displayName, String givenName, String sn, String physicalDeliveryOfficeName) {
    this.samAccountName = samAccountName;
    this.email = email;
    this.displayName = displayName;
    this.givenName = givenName;
    this.sn = sn;
    this.physicalDeliveryOfficeName = physicalDeliveryOfficeName;
  }

  public UserMailDTO(String samAccountName, String email, String firstName, String lastName, String displayName, String office,
      UserMailChangedDTO changed) {
    this.samAccountName = samAccountName;
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.displayName = displayName;
    this.office = office;
    this.changed = changed;
  }

  public UserMailDTO(String samAccountName, String email, String displayName, String office) {
    this.samAccountName = samAccountName;
    this.email = email;
    this.displayName = displayName;
    this.office = office;
  }

  public String getSamAccountName() {
    return samAccountName;
  }

  public void setSamAccountName(String samAccountName) {
    this.samAccountName = samAccountName;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getDisplayName() {
    return displayName;
  }

  public void setDisplayName(String displayName) {
    this.displayName = displayName;
  }

  public String getOffice() {
    return office;
  }

  public void setOffice(String office) {
    this.office = office;
  }

  public String getGivenName() {
    return givenName;
  }

  public void setGivenName(String givenName) {
    this.givenName = givenName;
  }

  public String getSn() {
    return sn;
  }

  public void setSn(String sn) {
    this.sn = sn;
  }

  public String getPhysicalDeliveryOfficeName() {
    return physicalDeliveryOfficeName;
  }

  public void setPhysicalDeliveryOfficeName(String physicalDeliveryOfficeName) {
    this.physicalDeliveryOfficeName = physicalDeliveryOfficeName;
  }

  public UserMailChangedDTO getChanged() {
    return changed;
  }

  public void setChanged(UserMailChangedDTO changed) {
    this.changed = changed;
  }
}

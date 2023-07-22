package com.example.Localization.Listing_6;

/*------------------------------------------------------------------------------
 * Oracle Certified Professional Java SE 8 Programmer Exam 1Z0-809 
 * A Comprehensive OCPJP 8 Certification Guide
 * by SG Ganesh, Hari Kiran and Tushar Sharma
------------------------------------------------------------------------------*/

import java.util.ListResourceBundle;

// Italian version
public class ResBundle_it_IT extends ListResourceBundle {

  static final Object[][] contents = {
      {"MovieName", "Che Bella Giornata"},
      {"GrossRevenue", (Long) 43000000L}, // in euros
      {"Year", (Integer) 2011}
  };

  public Object[][] getContents() {
    return contents;
  }
}


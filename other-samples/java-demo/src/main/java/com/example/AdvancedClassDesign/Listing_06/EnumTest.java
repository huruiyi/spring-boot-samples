package com.example.AdvancedClassDesign.Listing_06;

// define an enum for classifying printer types 
enum PrinterType {
  DOTMATRIX, INKJET, LASER
}

// test the enum now 
public class EnumTest {

  PrinterType printerType;

  public EnumTest(PrinterType pType) {
    printerType = pType;
  }

  public static void main(String[] args) {
    EnumTest enumTest = new EnumTest(PrinterType.LASER);
    enumTest.feature();
  }

  public void feature() {
    // switch based on the printer type passed in the constructor
    switch (printerType) {
      case DOTMATRIX:
        System.out.println("Dot-matrix printers are economical and almost obsolete");
        break;
      case INKJET:
        System.out.println("Inkjet printers provide decent quality prints");
        break;
      case LASER:
        System.out.println("Laser printers provide best quality prints");
        break;
    }
  }
}

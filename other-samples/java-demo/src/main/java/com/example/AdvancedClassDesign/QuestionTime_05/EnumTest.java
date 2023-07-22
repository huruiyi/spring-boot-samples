package com.example.AdvancedClassDesign.QuestionTime_05;

public class EnumTest {

  PrinterType printerType;

  public EnumTest(PrinterType pType) {
    printerType = pType;
  }

  public static void main(String[] args) {
    EnumTest enumTest = new EnumTest(PrinterType.LASER);
  }

  enum PrinterType {
    INKJET, DOTMATRIX, LASER
  }
}

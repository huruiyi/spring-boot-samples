package com.example.AdvancedClassDesign.QuestionTime_06;

public enum PrinterType {

  DOTMATRIX(5),
  INKJET(10),
  LASER(50);

  public int pagePrintCapacity;

  private PrinterType(int pagePrintCapacity) {
    this.pagePrintCapacity = pagePrintCapacity;
  }

  public int getPrintPageCapacity() {
    return pagePrintCapacity;
  }
}

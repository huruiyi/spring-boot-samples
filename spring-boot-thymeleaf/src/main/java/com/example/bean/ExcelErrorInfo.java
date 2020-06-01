package com.example.bean;

public class ExcelErrorInfo {
    private int rowIndex;
    private int cellIndex;
    private String headTitle;
    private String Message;

    public int getRowIndex() {
        return rowIndex;
    }

    public void setRowIndex(int rowIndex) {
        this.rowIndex = rowIndex;
    }

    public int getCellIndex() {
        return cellIndex;
    }

    public void setCellIndex(int cellIndex) {
        this.cellIndex = cellIndex;
    }

    public String getHeadTitle() {
        return headTitle;
    }

    public void setHeadTitle(String headTitle) {
        this.headTitle = headTitle;
    }

    public String getMessage() {
        return Message;
    }

    public void setMessage(String message) {
        Message = message;
    }

    @Override
    public String toString() {
        return "ExcelErrorInfo{" +
                "rowIndex=" + rowIndex +
                ", cellIndex=" + cellIndex +
                ", headTitle='" + headTitle + '\'' +
                ", Message='" + Message + '\'' +
                '}';
    }
}

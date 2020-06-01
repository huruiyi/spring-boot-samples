package com.example.bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class ExcelData {

    @ExcelColumn(desc = "列0")
    public String col0;

    @ExcelColumn(desc = "列1")
    public Byte col1;

    @ExcelColumn(desc = "列11")
    public Short col11;

    @ExcelColumn(desc = "列111")
    public Long col111;

    @ExcelColumn(desc = "列2")
    public Integer col2;

    @ExcelColumn(desc = "列3", precision = 5)
    public BigDecimal col3;

    @ExcelColumn(desc = "列33", precision = 4)
    public float col33;

    @ExcelColumn(desc = "列333", precision = 3)
    public Float col333;


    @ExcelColumn(desc = "列5", precision = 3)
    public double col5;

    @ExcelColumn(desc = "列55", precision = 3)
    public Double col55;


    @ExcelColumn(desc = "列4", dateFormat = "yyyy-MM-dd")
    private Date col4;

    @ExcelColumn(desc = "列44")
    private LocalDateTime col44;

    @ExcelColumn(desc = "列444")
    private LocalDate col444;

    @ExcelColumn(errorInfoField = true)
    private List<ExcelErrorInfo> errorInfos;


    //---------------------------------------------------------------


    public String getCol0() {
        return col0;
    }

    public void setCol0(String col0) {
        this.col0 = col0;
    }

    public Byte getCol1() {
        return col1;
    }

    public void setCol1(Byte col1) {
        this.col1 = col1;
    }

    public Short getCol11() {
        return col11;
    }

    public void setCol11(Short col11) {
        this.col11 = col11;
    }

    public Long getCol111() {
        return col111;
    }

    public void setCol111(Long col111) {
        this.col111 = col111;
    }

    public Integer getCol2() {
        return col2;
    }

    public void setCol2(Integer col2) {
        this.col2 = col2;
    }

    public BigDecimal getCol3() {
        return col3;
    }

    public void setCol3(BigDecimal col3) {
        this.col3 = col3;
    }

    public float getCol33() {
        return col33;
    }

    public void setCol33(float col33) {
        this.col33 = col33;
    }

    public Float getCol333() {
        return col333;
    }

    public void setCol333(Float col333) {
        this.col333 = col333;
    }

    public double getCol5() {
        return col5;
    }

    public void setCol5(double col5) {
        this.col5 = col5;
    }

    public Double getCol55() {
        return col55;
    }

    public void setCol55(Double col55) {
        this.col55 = col55;
    }

    public Date getCol4() {
        return col4;
    }

    public void setCol4(Date col4) {
        this.col4 = col4;
    }

    public LocalDateTime getCol44() {
        return col44;
    }

    public void setCol44(LocalDateTime col44) {
        this.col44 = col44;
    }

    public LocalDate getCol444() {
        return col444;
    }

    public void setCol444(LocalDate col444) {
        this.col444 = col444;
    }

    public List<ExcelErrorInfo> getErrorInfos() {
        return errorInfos;
    }

    public void setErrorInfos(List<ExcelErrorInfo> errorInfos) {
        this.errorInfos = errorInfos;
    }

    @Override
    public String toString() {
        return "ExcelData{" +
                "col0='" + col0 + '\'' +
                ", col1=" + col1 +
                ", col11=" + col11 +
                ", col111=" + col111 +
                ", col2=" + col2 +
                ", col3=" + col3 +
                ", col33=" + col33 +
                ", col333=" + col333 +
                ", col5=" + col5 +
                ", col55=" + col55 +
                ", col4=" + col4 +
                ", col44=" + col44 +
                ", col444=" + col444 +
                ", errorInfos=" + errorInfos +
                '}';
    }
}

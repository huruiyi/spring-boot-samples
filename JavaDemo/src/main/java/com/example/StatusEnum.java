package com.example;

public enum StatusEnum {
    Draf("草稿"),
    Submit("提交审核"),
    Confirm("审核通过"),
    Refuse("拒绝"),
    Cancel("删除");

    private final String status;

    private StatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static void main(String[] args) {
        System.out.println(StatusEnum.Draf);
        System.out.println(StatusEnum.Draf.toString());

        String status = StatusEnum.Draf.getStatus();
        System.out.println(status);

        StatusEnum draf = StatusEnum.valueOf("Draf");

        System.out.println(draf.status);


         for (StatusEnum value : StatusEnum.values()) {
            System.out.println(value.toString() + " " + value.getStatus());
        }

        System.out.println(StatusEnum.valueOf("Draf").status);

    }
}

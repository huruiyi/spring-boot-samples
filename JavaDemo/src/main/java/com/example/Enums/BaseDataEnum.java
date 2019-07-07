package com.example.Enums;

public enum BaseDataEnum {

//	英文名 大洲名		BM_COUNTRY  国家
//	英文名  州英文名 国家英文	BM_CITY		城市
//	英文名			BM_PORT		港口
//	英文名（去空格）		BM_FACILITY	码头

//	DATA_VAL_NME 	CARRIER		承运人
//	BIZ_CODE 		BM_CNTR_TYPE	箱型尺寸
//	BIZ_CODE 		BM_PKG_TYPE		包装代码
//	DATA_VAL_NME 	SEAL_TYPE	铅封类型

//	BIZ_CODE 		EXT_REF_TYPE	外部参考号
//	SYSTEM_CDE 		CUSTOMS_BROKER	报关行

    BM_COUNTRY("国家","BM_COUNTRY"),
    BM_CITY("城市","BM_CITY"),
    BM_PORT("港口","BM_PORT"),
    BM_FACILITY("码头","BM_FACILITY"),

    CARRIER("承运人","CARRIER"),
    BM_CNTR_TYPE("箱型尺寸","BM_CNTR_TYPE"),
    BM_PKG_TYPE("包装代码","BM_PKG_TYPE"),
    SEAL_TYPE("铅封类型","SEAL_TYPE"),

    EXT_REF_TYPE("外部参考号","EXT_REF_TYPE"),
    CUSTOMS_BROKER("报关行","CUSTOMS_BROKER");

    private String text;
    private String value;

    private BaseDataEnum(String text, String value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }

}

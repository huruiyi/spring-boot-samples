package com.example.Spring.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * 公司：TBK
 * 作者：胡睿毅
 * 文件名：EpanAccount
 * 日期：2019/4/26 8:45
 **/
@Data
public class EpanAccount  {

    private static final long serialVersionUID = 1L;

    private Long accountId;

    /**
     * 其企业识别码
     */
    private Long enterpriseId;

    /**
     * 企业预存款类型：0：泛亚 1：中集 2：东南亚
     */
    private String accountType;

    /**
     * 账户余额
     */
    private BigDecimal balance;

    /**
     * 创建时间
     */
    private LocalDateTime recCreDt;

    /**
     * 创建人
     */
    private String recCreUser;

    /**
     * 更新时间
     */
    private LocalDateTime recUpdDt;

    /**
     * 更新人
     */
    private String recUpdUser;

    /**
     * 版本号
     */
    private Long recVer;


    /**************扩展字段---开始*****************/

    /**
     * 用户Id USER_ID
     */
    private Long userId;


    private Long accountDetailId;


    /**
     * 交易类型0：收入 1：支出
     */
    private Integer tradeType;

    /**
     * 业务编号（订单号或者流水号等）
     */
    private String bizCode;

    /**
     * 交易金额
     */
    private BigDecimal tradeAmt;


    /**
     * 当前账户余额
     */
    private BigDecimal currentBalance;


    /**
     * 收入金额
     */
    private BigDecimal incomeAmt;

    /**
     * 支出金额
     */
    private BigDecimal expenditureAmt;

    /**
     * 备注-REMARK
     */
    private String remark;


    /**************扩展字段---结束*****************/


    @Override
    public String toString() {
        return "EpanAccount{" +
                ", accountId=" + accountId +
                ", enterpriseId=" + enterpriseId +
                ", accountType=" + accountType +
                ", balance=" + balance +
                ", recCreDt=" + recCreDt +
                ", recCreUser=" + recCreUser +
                ", recUpdDt=" + recUpdDt +
                ", recUpdUser=" + recUpdUser +
                ", recVer=" + recVer +
                "}";
    }
}

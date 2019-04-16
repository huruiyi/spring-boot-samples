package com.example.springbootmybatisplus.model;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;

/**
 * <p>
 * 用户---企业主信息
 * </p>
 *
 * @author coscon-liyzh
 * @since 2019-04-04
 */
@TableName("EPAN_ENTERPRISE")
public class EpanEnterprise extends Model<EpanEnterprise> {

    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @TableId("ENTERPRISE_ID")
    private Long enterpriseId;

    /**
     * 企业类型：个人/公司

个人：Personal

公司：Enterprise
     */
    @TableField("ENTERPRISE_TYPE")
    private String enterpriseType;

    /**
     * 企业名称（中文）
     */
    @TableField("ENTERPRISE_CNNME")
    private String enterpriseCnnme;

    /**
     * 是否内贸客户
     */
    @TableField("IS_DOMESTIC")
    private Integer isDomestic;

    /**
     * 是否拖车客户
     */
    @TableField("IS_TRUCK")
    private Integer isTruck;

    /**
     * new cfp 分配的ID
     */
    @TableField("SAP_ID")
    private String sapId;

    /**
     * 车队代码
     */
    @TableField("TRUCK_CDE")
    private String truckCde;

    /**
     * 数据来源

0：线上

1：内贸电子订舱
     */
    @TableField("SOURCE")
    private String source;

    /**
     * 企业主账号USERID
     */
    @TableField("MASTER_USER_ID")
    private Long masterUserId;

    /**
     * 父企业ID
     */
    @TableField("PARENT_ENTERPRISE_ID")
    private Long parentEnterpriseId;

    @TableField("REC_CRE_USER")
    private String recCreUser;

    @TableField("REC_CRE_DT")
    private LocalDateTime recCreDt;

    @TableField("REC_UPD_USER")
    private String recUpdUser;

    @TableField("REC_UPD_DT")
    private LocalDateTime recUpdDt;


    public Long getEnterpriseId() {
        return enterpriseId;
    }

    public void setEnterpriseId(Long enterpriseId) {
        this.enterpriseId = enterpriseId;
    }

    public String getEnterpriseType() {
        return enterpriseType;
    }

    public void setEnterpriseType(String enterpriseType) {
        this.enterpriseType = enterpriseType;
    }

    public String getEnterpriseCnnme() {
        return enterpriseCnnme;
    }

    public void setEnterpriseCnnme(String enterpriseCnnme) {
        this.enterpriseCnnme = enterpriseCnnme;
    }

    public Integer getIsDomestic() {
        return isDomestic;
    }

    public void setIsDomestic(Integer isDomestic) {
        this.isDomestic = isDomestic;
    }

    public Integer getIsTruck() {
        return isTruck;
    }

    public void setIsTruck(Integer isTruck) {
        this.isTruck = isTruck;
    }

    public String getSapId() {
        return sapId;
    }

    public void setSapId(String sapId) {
        this.sapId = sapId;
    }

    public String getTruckCde() {
        return truckCde;
    }

    public void setTruckCde(String truckCde) {
        this.truckCde = truckCde;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public Long getMasterUserId() {
        return masterUserId;
    }

    public void setMasterUserId(Long masterUserId) {
        this.masterUserId = masterUserId;
    }

    public Long getParentEnterpriseId() {
        return parentEnterpriseId;
    }

    public void setParentEnterpriseId(Long parentEnterpriseId) {
        this.parentEnterpriseId = parentEnterpriseId;
    }

    public String getRecCreUser() {
        return recCreUser;
    }

    public void setRecCreUser(String recCreUser) {
        this.recCreUser = recCreUser;
    }

    public LocalDateTime getRecCreDt() {
        return recCreDt;
    }

    public void setRecCreDt(LocalDateTime recCreDt) {
        this.recCreDt = recCreDt;
    }

    public String getRecUpdUser() {
        return recUpdUser;
    }

    public void setRecUpdUser(String recUpdUser) {
        this.recUpdUser = recUpdUser;
    }

    public LocalDateTime getRecUpdDt() {
        return recUpdDt;
    }

    public void setRecUpdDt(LocalDateTime recUpdDt) {
        this.recUpdDt = recUpdDt;
    }

    @Override
    protected Serializable pkVal() {
        return this.enterpriseId;
    }

    @Override
    public String toString() {
        return "EpanEnterprise{" +
        ", enterpriseId=" + enterpriseId +
        ", enterpriseType=" + enterpriseType +
        ", enterpriseCnnme=" + enterpriseCnnme +
        ", isDomestic=" + isDomestic +
        ", isTruck=" + isTruck +
        ", sapId=" + sapId +
        ", truckCde=" + truckCde +
        ", source=" + source +
        ", masterUserId=" + masterUserId +
        ", parentEnterpriseId=" + parentEnterpriseId +
        ", recCreUser=" + recCreUser +
        ", recCreDt=" + recCreDt +
        ", recUpdUser=" + recUpdUser +
        ", recUpdDt=" + recUpdDt +
        "}";
    }
}

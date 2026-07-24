package com.example.world.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author fairy.vip
 * @since 2023-07-23
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("Code")
    private String code;

    @TableField("Name")
    private String name;

    @TableField("Continent")
    private String continent;

    @TableField("Region")
    private String region;

    @TableField("SurfaceArea")
    private BigDecimal surfacearea;

    @TableField("IndepYear")
    private Integer indepyear;

    @TableField("Population")
    private Integer population;

    @TableField("LifeExpectancy")
    private BigDecimal lifeexpectancy;

    @TableField("GNP")
    private BigDecimal gnp;

    @TableField("GNPOld")
    private BigDecimal gnpold;

    @TableField("LocalName")
    private String localname;

    @TableField("GovernmentForm")
    private String governmentform;

    @TableField("HeadOfState")
    private String headofstate;

    @TableField("Capital")
    private Integer capital;

    @TableField("Code2")
    private String code2;


}

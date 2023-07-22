package com.example.demo.world.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author fairy.vip
 * @since 2023-07-22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class Countrylanguage implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId("CountryCode")
    private String CountryCode;

    @TableField("Language")
    private String Language;

    @TableField("IsOfficial")
    private String IsOfficial;

    @TableField("Percentage")
    private BigDecimal Percentage;


}

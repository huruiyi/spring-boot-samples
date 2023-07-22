package com.example.demo.world.entity;

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
public class City implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableField("Name")
    private String name;

    @TableField("CountryCode")
    private String countrycode;

    @TableField("District")
    private String district;

    @TableField("Population")
    private Integer population;


}

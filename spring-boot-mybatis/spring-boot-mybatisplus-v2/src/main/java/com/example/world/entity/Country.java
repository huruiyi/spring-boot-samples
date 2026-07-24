package com.example.world.entity;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author huruiyi
 * @since 2023-08-07 17:22:10
 */
@Getter
@Setter
public class Country extends Model<Country> {

    private static final long serialVersionUID = 1L;

    private String Code;

    private String Name;

    private String Continent;

    private String Region;

    private BigDecimal SurfaceArea;

    private Short IndepYear;

    private Integer Population;

    private BigDecimal LifeExpectancy;

    private BigDecimal GNP;

    private BigDecimal GNPOld;

    private String LocalName;

    private String GovernmentForm;

    private String HeadOfState;

    private Integer Capital;

    private String Code2;

    @Override
    public Serializable pkVal() {
        return this.Code;
    }
}

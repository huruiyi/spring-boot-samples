package com.example.demo.world.entity;

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
 * @since 2023-07-23 08:50:02
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

    @Override
    public String toString() {
        return "Country{" +
            "Code='" + Code + '\'' +
            ", Name='" + Name + '\'' +
            ", Continent='" + Continent + '\'' +
            ", Region='" + Region + '\'' +
            ", SurfaceArea=" + SurfaceArea +
            ", IndepYear=" + IndepYear +
            ", Population=" + Population +
            ", LifeExpectancy=" + LifeExpectancy +
            ", GNP=" + GNP +
            ", GNPOld=" + GNPOld +
            ", LocalName='" + LocalName + '\'' +
            ", GovernmentForm='" + GovernmentForm + '\'' +
            ", HeadOfState='" + HeadOfState + '\'' +
            ", Capital=" + Capital +
            ", Code2='" + Code2 + '\'' +
            '}';
    }
}

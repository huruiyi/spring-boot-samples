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
public class Countrylanguage extends Model<Countrylanguage> {

    private static final long serialVersionUID = 1L;

    private String CountryCode;

    private String Language;

    private String IsOfficial;

    private BigDecimal Percentage;

    @Override
    public Serializable pkVal() {
        return this.Language;
    }
}

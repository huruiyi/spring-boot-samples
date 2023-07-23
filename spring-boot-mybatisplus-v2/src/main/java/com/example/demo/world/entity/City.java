package com.example.demo.world.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;
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
public class City extends Model<City> {

    private static final long serialVersionUID = 1L;

    @TableId(value = "ID", type = IdType.AUTO)
    private Integer ID;

    private String Name;

    private String CountryCode;

    private String District;

    private Integer Population;

    @Override
    public Serializable pkVal() {
        return this.ID;
    }
}

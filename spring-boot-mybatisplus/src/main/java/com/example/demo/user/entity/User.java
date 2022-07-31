package com.example.demo.user.entity;

import java.math.BigDecimal;
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
 * @since 2022-08-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private BigDecimal weight;

    private Boolean disabled;

    private String email;

    private String pSign;

    private String password;

    private String sex;

    private String telPhone;


}

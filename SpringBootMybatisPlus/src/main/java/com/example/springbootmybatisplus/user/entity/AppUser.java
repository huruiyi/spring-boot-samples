package com.example.springbootmybatisplus.user.entity;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 *
 * </p>
 *
 * @author jobob
 * @since 2019-03-20
 */
@Data
@Accessors(chain = true)
public class AppUser {

    private static final long serialVersionUID = 1L;

    private String ssoId;

    private String password;

    private String firstName;

    private String lastName;

    private String email;

    private String state;


}

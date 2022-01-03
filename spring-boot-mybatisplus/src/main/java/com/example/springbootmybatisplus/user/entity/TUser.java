package com.example.springbootmybatisplus.user.entity;

import java.time.LocalDateTime;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author jobob
 * @since 2022-01-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class TUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;

    private LocalDateTime loginTime;

    private String mobile;

    private String note;

    private String hobbies;

    private Integer positionId;

    private Integer sex;

    private String userName;


}

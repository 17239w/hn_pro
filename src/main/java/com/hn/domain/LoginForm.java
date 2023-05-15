package com.hn.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 将登陆时的信息封装到LoginForm类中
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginForm {

    private String username;
    private String password;
    private String verifiCode;
    private Integer userType;
}

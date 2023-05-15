package com.hn.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName account_management
 */
@TableName(value ="account_management")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountManagement implements Serializable {
    private Long accountManagementId;

    private Integer systemId;

    private String ipAddress;

    private String post;

    private String accountName;

    private Object accountClass;

    private Date createTime;

    private Date updateTime;

    private Object loginMode;

    private Object accountRole;

    private Object accountAuthority;

    private Date authorizationDate;

    private Date expirationDate;

    private Object passwordStrength;

    private Object passwordChange;

    private Date passwordLastChange;

    private Object responsibleUnit;

    private String responsiblePerson;

    private Integer conta;

    private String user;

    private String userUnit;

    private Integer userContact;

    private String fwaf;

    private static final long serialVersionUID = 1L;
}
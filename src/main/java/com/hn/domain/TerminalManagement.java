package com.hn.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName terminal_management
 */
@TableName(value ="terminal_management")
@Data
public class TerminalManagement implements Serializable {
    private Long terminalManagementId;

    private Object responsibleUnit;

    private String responsiblePerson;

    private Object terminalType;

    private String theThirdPartyName;

    private String userName;

    private Integer contact;

    private String osType;

    private Date createTime;

    private Date updateTime;

    private String ip1;

    private String mac1;

    private String ip2;

    private String mac2;

    private Object passwordCompliance;

    private Object firewallOn;

    private Object antivirusSoftware;

    private Object updatePatches;

    private Object sensitiveFileEncrypt;

    private Date verificationDate;

    private String commitmentLetter;

    private String fcwa;

    private String wagaf;

    private static final long serialVersionUID = 1L;
}
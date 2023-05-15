package com.hn.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName usr_admin
 */
@TableName(value ="usr_admin")
@Data
public class UsrAdmin implements Serializable {
    private Long adminId;

    private String name;

    private String password;

    private Date createTime;

    private Date updateTime;

    private Long deletedFlag;

    private static final long serialVersionUID = 1L;
}
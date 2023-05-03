package com.hn.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName usr_root
 */
@TableName(value ="usr_root")
@Data
public class UsrRoot implements Serializable {
    private Long rootId;

    private String name;

    private String password;

    private static final long serialVersionUID = 1L;
}
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
 * @TableName tables
 */
@TableName(value ="tables")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Tables implements Serializable {
    private Long tablesId;

    private String name;

    private String chineseName;

    private Date updateTime;

    private String fkTables;

    private Date createTime;

    private String templatePath;

    private Boolean isMultiParents;

    private String flagCurrentParentMap;

    private static final long serialVersionUID = 1L;
}
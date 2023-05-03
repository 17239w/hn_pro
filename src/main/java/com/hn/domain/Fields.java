package com.hn.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * @TableName fields
 */
@TableName(value ="fields")
@Data
public class Fields implements Serializable {
    private Long fieldsId;

    private String name;

    private String chineseName;

    private String description;

    private String type;

    private Integer size;

    private Integer isPrimary;

    private Integer isNonnull;

    private String defaultValue;

    private String filterType;

    private String enumMap;

    private String regexCondition;

    private Long tablesTablesId;

    private static final long serialVersionUID = 1L;
}
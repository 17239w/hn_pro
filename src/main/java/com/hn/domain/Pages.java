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
 * @TableName pages
 */
@TableName(value ="pages")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pages implements Serializable {
    private Long pagesId;

    private Long tablesId;

    private String name;

    private String description;

    private String url;

    private String superior;

    private String defaultDisplay;

    private Date createTime;

    private Date updateTime;

    private String defaultSearch;

    private Boolean hasPromptBox;

    private String promptCondition;

    private String promptMsg;

    private String promptDisplayFields;

    private static final long serialVersionUID = 1L;
}
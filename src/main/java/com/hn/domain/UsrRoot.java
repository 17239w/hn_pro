package com.hn.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName usr_root
 */
@TableName(value ="usr_root")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UsrRoot implements Serializable {

    @ExcelProperty("rootId")
    private Long rootId;

    @ExcelProperty("name")
    private String name;

    private String password;

    @ExcelProperty("createTime")
    private Date createTime;

    @ExcelProperty("updateTime")
    private Date updateTime;

    @ExcelProperty("deletedFlag")
    private Long deletedFlag;

    private static final long serialVersionUID = 1L;
}
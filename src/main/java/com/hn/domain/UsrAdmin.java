package com.hn.domain;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @TableName usr_admin
 */
@TableName(value ="usr_admin")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UsrAdmin implements Serializable {

    @ExcelProperty("amdinId")
    private Long adminId;

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
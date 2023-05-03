package com.hn.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName files
 */
@TableName(value ="files")
@Data
public class Files implements Serializable {
    private Long filesId;

    private String name;

    private Boolean isDir;

    private Long father;

    private String path;

    private Long size;

    private String ext;

    private Date createTime;

    private Date updateTime;

    private static final long serialVersionUID = 1L;
}
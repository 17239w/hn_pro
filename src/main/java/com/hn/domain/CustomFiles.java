package com.hn.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName custom_files
 */
@TableName(value ="custom_files")
@Data
public class CustomFiles implements Serializable {
    private Long customFilesId;

    private String name;

    private Long size;

    private String path;

    private Boolean isUsed;

    private String ext;

    private Date createTime;

    private static final long serialVersionUID = 1L;
}
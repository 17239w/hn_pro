package com.hn.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName project_management
 */
@TableName(value ="project_management")
@Data
public class ProjectManagement implements Serializable {
    private Long projectManagementId;

    private String projectName;

    private String projectManager;

    private String department;

    private String projectYear;

    private String projectAttribute;

    private Date createTime;

    private Date updateTime;

    private Object threeSynonymousRequests;

    private Date finishTime;

    private Date riskAssessmentTime;

    private Date fillingTime;

    private String maintenanceUnit;

    private String maintenanceManager;

    private Date systemOnlineTime;

    private Date initialInspectionTime;

    private String remarks;

    private static final long serialVersionUID = 1L;
}
package com.hn.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @TableName system_template
 */
@TableName(value ="system_template")
@Data
public class SystemTemplate implements Serializable {
    private Long systemTemplateId;

    private Long customFilesId;

    private String systemId;

    private String systemName;

    private String systemDescription;

    private Date createTime;

    private Date updateTime;

    private String protectingMeasures;

    private Object selectItem;

    private Object lifeCycle;

    private String constructionCompany;

    private Date constructionTime;

    private Object trafficMaintenance;

    private Object maintenance;

    private Date timeOfMaintenance;

    private Date crossDimensionTime;

    private Object gradeRecord;

    private String gradingUnitName;

    private Object gradeRecordLevel;

    private Date gradeRecordTime;

    private String subordinDepartment;

    private Object maintenanceUnit;

    private String responsiblePerson;

    private Integer contact;

    private static final long serialVersionUID = 1L;
}
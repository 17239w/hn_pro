package com.hn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hn.domain.ProjectManagement;
import com.hn.service.ProjectManagementService;
import com.hn.mapper.ProjectManagementMapper;
import org.springframework.stereotype.Service;

/**
* @author 15170
* @description 针对表【project_management】的数据库操作Service实现
* @createDate 2023-05-03 10:37:34
*/
@Service
public class ProjectManagementServiceImpl extends ServiceImpl<ProjectManagementMapper, ProjectManagement>
    implements ProjectManagementService{

}





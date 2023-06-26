package com.hn.service;

import com.hn.domain.UsrAdmin;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.ServletOutputStream;

/**
* @author 15170
* @description 针对表【usr_admin】的数据库操作Service
* @createDate 2023-05-08 11:42:34
*/
public interface UsrAdminService extends IService<UsrAdmin> {

    UsrAdmin selectUsrAdminByNameAndPassword(String usrName,String password);

    UsrAdmin selectUsrAdminById(Long usrId);

    void downloadExcel_UsrAdmin(ServletOutputStream outputStream);
}

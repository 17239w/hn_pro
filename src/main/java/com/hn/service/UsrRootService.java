package com.hn.service;

import com.hn.domain.UsrRoot;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.ServletOutputStream;

/**
* @author 15170
* @description 针对表【usr_root】的数据库操作Service
* @createDate 2023-05-08 11:42:47
*/
public interface UsrRootService extends IService<UsrRoot> {

    UsrRoot selectUsrRootByNameAndPassword(String username, String password);

    UsrRoot selectUsrRootById(Long userId);

    void downloadExcel_UsrRoot(ServletOutputStream outputStream);
}

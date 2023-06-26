package com.hn.service.impl;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.EasyExcelFactory;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hn.domain.UsrAdmin;
import com.hn.service.UsrAdminService;
import com.hn.mapper.UsrAdminMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
* @author 15170
* @description 针对表【usr_admin】的数据库操作Service实现
* @createDate 2023-05-08 11:42:34
*/
@Service
public class UsrAdminServiceImpl extends ServiceImpl<UsrAdminMapper, UsrAdmin>
    implements UsrAdminService{

    @Resource
    private UsrAdminMapper usrAdminMapper;

    /**
     * 根据用户名和密码查询admin用户，用于登录
     * @param usrName
     * @param password
     * @return
     */
    @Override
    public UsrAdmin selectUsrAdminByNameAndPassword(String usrName, String password) {
        QueryWrapper<UsrAdmin> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("usrAdmin_name",usrName).eq("password",password);
        return usrAdminMapper.selectOne(queryWrapper);
    }

    /**
     * 根据用户id查询admin用户
     * @param usrId
     * @return
     */
    @Override
    public UsrAdmin selectUsrAdminById(Long usrId) {
        QueryWrapper<UsrAdmin> queryWrapper= new QueryWrapper<>();
        queryWrapper.eq("usrAdmin_id",usrId);
        return usrAdminMapper.selectOne(queryWrapper);
    }

    /**
     * 导出excel表格(不会导出密码)
     * @param outputStream
     */
    @Override
    public void downloadExcel_UsrAdmin(ServletOutputStream outputStream) {
        EasyExcel.write(outputStream, UsrAdmin.class).sheet("UsrAdmin").doWrite(this::getUsrAdminList);
    }

    /**
     * 获取所有的UsrAdmin用户信息(不显示密码)
     * @return
     */
    private List<UsrAdmin> getUsrAdminList() {
        //查询所有的UsrAdmin用户，不显示密码这一栏
        List<UsrAdmin> usrAdminList = usrAdminMapper.selectList(new QueryWrapper<UsrAdmin>().select(UsrAdmin.class,
                info -> !info.getColumn().equals("password")));
        return usrAdminList;
    }

}





package com.hn.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hn.domain.UsrRoot;
import com.hn.service.UsrRootService;
import com.hn.mapper.UsrRootMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
* @author 15170
* @description 针对表【usr_root】的数据库操作Service实现
* @createDate 2023-05-08 11:42:47
*/
@Service
public class UsrRootServiceImpl extends ServiceImpl<UsrRootMapper, UsrRoot>
    implements UsrRootService{

    @Resource
    private UsrRootMapper usrRootMapper;

    /**
     * 根据用户名和密码查询root用户，用于登录
     * @param username
     * @param password
     * @return
     */
    @Override
    public UsrRoot selectUsrRootByNameAndPassword(String username, String password) {
        QueryWrapper<UsrRoot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("usrRoot_name",username).eq("password",password);
        return usrRootMapper.selectOne(queryWrapper);
    }

    /**
     * 根据用户id查询root用户
     * @param userId
     * @return
     */
    @Override
    public UsrRoot selectUsrRootById(Long userId) {
        QueryWrapper<UsrRoot> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("usrRoot_id",userId);
        return usrRootMapper.selectOne(queryWrapper);
    }
}





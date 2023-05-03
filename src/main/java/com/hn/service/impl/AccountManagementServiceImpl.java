package com.hn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hn.domain.AccountManagement;
import com.hn.service.AccountManagementService;
import com.hn.mapper.AccountManagementMapper;
import org.springframework.stereotype.Service;

/**
* @author 15170
* @description 针对表【account_management】的数据库操作Service实现
* @createDate 2023-05-03 10:36:30
*/
@Service
public class AccountManagementServiceImpl extends ServiceImpl<AccountManagementMapper, AccountManagement>
    implements AccountManagementService{

}





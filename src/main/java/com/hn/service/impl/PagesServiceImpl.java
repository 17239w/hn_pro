package com.hn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hn.domain.Pages;
import com.hn.service.PagesService;
import com.hn.mapper.PagesMapper;
import org.springframework.stereotype.Service;

/**
* @author 15170
* @description 针对表【pages】的数据库操作Service实现
* @createDate 2023-05-03 10:37:24
*/
@Service
public class PagesServiceImpl extends ServiceImpl<PagesMapper, Pages>
    implements PagesService{

}





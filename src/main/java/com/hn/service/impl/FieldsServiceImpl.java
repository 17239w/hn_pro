package com.hn.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hn.domain.Fields;
import com.hn.service.FieldsService;
import com.hn.mapper.FieldsMapper;
import org.springframework.stereotype.Service;

/**
* @author 15170
* @description 针对表【fields】的数据库操作Service实现
* @createDate 2023-05-03 10:37:08
*/
@Service
public class FieldsServiceImpl extends ServiceImpl<FieldsMapper, Fields>
    implements FieldsService{

}





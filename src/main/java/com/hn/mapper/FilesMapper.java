package com.hn.mapper;

import com.hn.domain.Files;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
* @author 15170
* @description 针对表【files】的数据库操作Mapper
* @createDate 2023-05-03 10:37:16
* @Entity com.hn.domain.Files
*/
@Mapper
public interface FilesMapper extends BaseMapper<Files> {

}





package com.htck.modules.business.dao;

import com.htck.modules.business.entity.PostQueryEntity;
import com.htck.modules.business.entity.PostEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 职位信息表
 */
@Mapper
public interface PostDao extends BaseMapper<PostEntity> {

    List<PostEntity> list(PostQueryEntity postQueryEntity);
	
}

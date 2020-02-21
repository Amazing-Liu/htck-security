package com.htck.modules.business.dao;

import com.htck.modules.business.entity.CandidateInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * 职位申请人信息表
 */
@Mapper
public interface CandidateInfoDao extends BaseMapper<CandidateInfoEntity> {

    List<CandidateInfoEntity> list(Map<String, Object> params);
}

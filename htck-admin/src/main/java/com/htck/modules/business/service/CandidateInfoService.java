package com.htck.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.htck.common.utils.PageUtils;
import com.htck.modules.business.entity.CandidateInfoEntity;

import java.util.Map;

/**
 * 职位申请人信息表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-02-20 16:24:23
 */
public interface CandidateInfoService extends IService<CandidateInfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
}


package com.htck.modules.business.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.htck.common.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.htck.modules.business.dao.CandidateInfoDao;
import com.htck.modules.business.entity.CandidateInfoEntity;
import com.htck.modules.business.service.CandidateInfoService;


@Service("candidateInfoService")
public class CandidateInfoServiceImpl extends ServiceImpl<CandidateInfoDao, CandidateInfoEntity> implements CandidateInfoService {

    @Autowired
    private CandidateInfoDao candidateInfoDao;

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
//        IPage<CandidateInfoEntity> page = this.page(
//                new Query<CandidateInfoEntity>().getPage(params),
//                new QueryWrapper<CandidateInfoEntity>()
//        );
//
//        return new PageUtils(page);

        int startPage =  Integer.parseInt(params.get("page").toString());
        int limit = Integer.parseInt(params.get("limit").toString());
        Page<?> page = PageHelper.startPage(startPage, limit);
        List<CandidateInfoEntity> list = candidateInfoDao.list(params);

        return new PageUtils(list, (int)page.getTotal(), limit, startPage);
    }

}

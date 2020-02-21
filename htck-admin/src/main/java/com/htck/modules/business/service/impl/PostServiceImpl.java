package com.htck.modules.business.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.htck.common.utils.PageUtils;
import com.htck.modules.business.entity.PostQueryEntity;
import com.htck.modules.business.enums.PostStateEnum;
import com.htck.modules.business.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.htck.common.utils.Query;

import com.htck.modules.business.dao.PostDao;
import com.htck.modules.business.entity.PostEntity;


@Service("postService")
public class PostServiceImpl extends ServiceImpl<PostDao, PostEntity> implements PostService {

    @Autowired
    private PostDao postDao;


    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<PostEntity> page = this.page(
                new Query<PostEntity>().getPage(params),
                new QueryWrapper<PostEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public void operate(Integer postId, Integer type) {
        PostEntity postEntity = new PostEntity();
        postEntity.setPostId(postId);
        postEntity.setPostState(type);
        if(type == PostStateEnum.ACTIVE.getValue()){
            postEntity.setPublishTime(new Date());
        }
        this.updateById(postEntity);
    }

    @Override
    public PageInfo<PostEntity> listByPage(PostQueryEntity postQueryEntity) {
        PageHelper.startPage(postQueryEntity.getPage(),postQueryEntity.getRows());
        return new PageInfo<>(list(postQueryEntity));
    }

    @Override
    public List<PostEntity> list(PostQueryEntity postQueryEntity) {
        return postDao.list(postQueryEntity);
    }



}

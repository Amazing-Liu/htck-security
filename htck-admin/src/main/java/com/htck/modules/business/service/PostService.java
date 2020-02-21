package com.htck.modules.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.github.pagehelper.PageInfo;
import com.htck.common.utils.PageUtils;
import com.htck.modules.business.entity.PostQueryEntity;
import com.htck.modules.business.entity.PostEntity;

import java.util.List;
import java.util.Map;

/**
 * 职位信息表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-02-19 15:10:17
 */
public interface PostService extends IService<PostEntity> {

    PageUtils queryPage(Map<String, Object> params);

    /**
     * 职位上下线
     * @param postId
     * @param type
     */
    void operate(Integer postId, Integer type);


    /**
     * 分页获取职位列表
     * @param postQueryEntity
     * @return
     */
    PageInfo<PostEntity> listByPage(PostQueryEntity postQueryEntity);

    /**
     * 获取职位列表
     * @param postQueryEntity
     * @return
     */
    List<PostEntity> list(PostQueryEntity postQueryEntity);

}


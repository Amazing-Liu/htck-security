package com.htck.modules.business.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.htck.common.utils.PageUtils;
import com.htck.common.utils.R;
import com.htck.common.validator.ValidatorUtils;
import com.htck.modules.business.entity.PostQueryEntity;
import com.htck.modules.business.service.PostService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.htck.modules.business.entity.PostEntity;


/**
 * 职位信息表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-02-19 15:10:17
 */
@RestController
@RequestMapping("business/post")
public class PostController {
    @Autowired
    private PostService postService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:post:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = postService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{postId}")
    @RequiresPermissions("sys:post:info")
    public R info(@PathVariable("postId") Integer postId){
        PostEntity post = postService.getById(postId);

        return R.ok().put("post", post);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:post:save")
    public R save(@RequestBody PostEntity post){
        postService.save(post);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:post:update")
    public R update(@RequestBody PostEntity post){
        ValidatorUtils.validateEntity(post);
        postService.updateById(post);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:post:delete")
    public R delete(@RequestBody Integer[] postIds){
        postService.removeByIds(Arrays.asList(postIds));

        return R.ok();
    }

    /**
     * 职位上下架操作
     */
    @RequestMapping("/operate")
    @RequiresPermissions("sys:post:update")
    public R operate(Integer postId,Integer type){
        postService.operate(postId,type);
        return R.ok();
    }


    @ApiOperation("获取职位列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postName", value = "职位名称", paramType = "query", required = false, dataType = "string"),
            @ApiImplicitParam(name = "page", value = "页码,默认第一页 ", paramType = "query", required = false, dataType = "int"),
            @ApiImplicitParam(name = "rows", value = "每页大小，默认10", paramType = "query", required = false, dataType = "int")
    })
    @GetMapping("/listPost")
    public R listPost(PostQueryEntity postQueryEntity){
        PageInfo<PostEntity> pageInfo = postService.listByPage(postQueryEntity);
        return R.ok().put("page",pageInfo);
    }

    @ApiOperation("获取职位信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "postId", value = "职位名称", paramType = "query", required = true, dataType = "int"),
    })
    @GetMapping("/getPost")
    public R getPost(Integer postId){
        PostEntity postEntity = postService.getById(postId);
        return R.ok().put("post",postEntity);
    }

    @ApiOperation("根据id数组获取职位信息列表")
    @GetMapping("/listByIds")
    public R listByIds(@RequestParam("postIds")List<Integer> postIds){
        List<PostEntity> posts = (List<PostEntity>) postService.listByIds(postIds);
        return R.ok().put("posts",posts);
    }


}

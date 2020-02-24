package com.htck.modules.business.controller;

import java.io.*;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.htck.common.utils.DateUtils;
import com.htck.common.utils.PageUtils;
import com.htck.common.utils.R;
import com.htck.common.validator.ValidatorUtils;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import com.htck.modules.business.entity.CandidateInfoEntity;
import com.htck.modules.business.service.CandidateInfoService;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;


/**
 * 职位申请人信息表
 *
 * @author Mark
 * @email sunlightcs@gmail.com
 * @date 2020-02-20 16:24:23
 */
@RestController
@RequestMapping("business/candidateinfo")
public class CandidateInfoController {
    @Autowired
    private CandidateInfoService candidateInfoService;
    @Value("${uploadFile}")
    private String uploadFile;

    /**
     * 列表
     */
    @RequestMapping("/list")
    @RequiresPermissions("sys:candidateinfo:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = candidateInfoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    @RequiresPermissions("sys:candidateinfo:info")
    public R info(@PathVariable("id") Integer id){
        CandidateInfoEntity candidateInfo = candidateInfoService.getById(id);

        return R.ok().put("candidateInfo", candidateInfo);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    @RequiresPermissions("sys:candidateinfo:save")
    public R save(@RequestBody CandidateInfoEntity candidateInfo){
        candidateInfoService.save(candidateInfo);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    @RequiresPermissions("sys:candidateinfo:update")
    public R update(@RequestBody CandidateInfoEntity candidateInfo){
        ValidatorUtils.validateEntity(candidateInfo);
        candidateInfoService.updateById(candidateInfo);
        
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    @RequiresPermissions("sys:candidateinfo:delete")
    public R delete(@RequestBody Integer[] ids){
        candidateInfoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


    @ApiOperation("职位申请")
    @PostMapping("/apply")
    public R apply(CandidateInfoEntity candidateInfo, MultipartFile file){
        String originalFilename = file.getOriginalFilename();
        File dir = new File(uploadFile+"/"+candidateInfo.getPostId());
        if(!dir.exists()){
            dir.mkdirs();
        }
        String newFileName = DateUtils.format(new Date(),"yyyyMMddHHmmss")+"-"+originalFilename;
        File dest = new File(dir,newFileName);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
        }
        candidateInfo.setCurriculumVitaePath(dest.getPath());
        candidateInfo.setApplyDate(new Date());
        candidateInfoService.save(candidateInfo);
        return R.ok();
    }

    /**
     * 简历下载
     * @param candidateId 职位申请id
     * @return
     */
    @RequestMapping(value = "/downLoad", method = RequestMethod.GET)
    @ResponseBody
    public R downLoad(Integer candidateId, HttpServletResponse response){
        CandidateInfoEntity candidateInfo  = candidateInfoService.getById(candidateId);
        if(candidateInfo == null){
            return R.error("简历不存在");
        }
        String filePath = candidateInfo.getCurriculumVitaePath();
        File file = new File(filePath);
        String fileName = file.getName();
        byte[] b = null ;
        try(InputStream is = new FileInputStream(file); OutputStream outputStream = response.getOutputStream()) {
            fileName = URLEncoder.encode(fileName, "UTF-8");
            response.setHeader("Content-Disposition", "attachment;fileName=\""+ fileName+"\"");
            b = new byte[is.available()];
            is.read(b);
            outputStream.write(b);
            outputStream.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}

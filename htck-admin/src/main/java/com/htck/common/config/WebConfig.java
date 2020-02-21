/**
 * Copyright (c) 2016-2019 人人开源 All rights reserved.
 *
 * https://www.renren.io
 *
 * 版权所有，侵权必究！
 */

package com.htck.common.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.unit.DataSize;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.MultipartConfigElement;
import java.io.File;

/**
 * WebMvc配置
 *
 * @author Mark sunlightcs@gmail.com
 */
@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 项目所在路径
        String projectPath = new File("").getAbsolutePath();
        String resourcePath = String.format("file:%s%s",projectPath,File.separator);
        registry.addResourceHandler("/resource/**").addResourceLocations(resourcePath);
        registry.addResourceHandler("/statics/**").addResourceLocations("classpath:/statics/");
    }


    /**
     * 文件上传配置
     */
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        // 获取项目路径
        String projectPath = new File("").getAbsolutePath();
        log.info("获取项目路径:{}",projectPath);
        // 设置上传文件临时存放路径
        factory.setLocation(projectPath);
        // 单个文件最大
        factory.setMaxFileSize(DataSize.ofMegabytes(50));
        // 设置总上传数据总大小
        factory.setMaxRequestSize(DataSize.ofMegabytes(100));
        return factory.createMultipartConfig();
    }

}
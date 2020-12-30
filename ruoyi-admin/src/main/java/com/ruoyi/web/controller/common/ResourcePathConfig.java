package com.ruoyi.web.controller.common;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.io.File;

/**
 * vueweb页面的一些配置
 *
 * @author 崔玉国
 */
@Slf4j
@Configuration
public class ResourcePathConfig implements WebMvcConfigurer {
    public final static String basePath = com.ruoyi.web.controller.common.SystemConfigration.getHomePath();
    public final static String uploadPath = basePath + "/upload";

    /**
     * vue页面资源相关配置
     */
    @Value("${vue.patterns:/**}")
    private String vueResPatterns;

    /**
     * vue页面资源相关配置
     */
    @Value("${vue.deploy:false}")
    private boolean vueDeploy;

    /**
     * vue页面资源相关配置
     */
    @Value("${vue.path:vueweb}")
    private String vuePath;

    @Value("${vue.tipspath}")
    private String tipspath;
    /**
     * 用户头像相关资源配置
     */
    @Value("${header.patterns:/header/**}")
    private String headerPatterns;



    //本地文件目录也可以在浏览器中访问
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/Document/**").addResourceLocations("file:/E:/openSource/y_project-RuoYi-Vue-master/RuoYi-Vue/domain/upload/header/");
//        WebMvcConfigurer.super.addResourceHandlers(registry);
        if (vueDeploy) {
            String path = "/"+vuePath.replace("/", "")+"/";
            setResource(registry, vueResPatterns, basePath + path);
        }
        setResource(registry, headerPatterns, tipspath + "/upload/header/");
    }

    private void setResource(ResourceHandlerRegistry registry, String patterns, String path) {
        if (StringUtils.hasText(patterns) &&
            StringUtils.hasText(path)) {
            if (!patterns.trim().startsWith("/")) {
                patterns = "/" + patterns.trim();
            }
            if (!path.trim().startsWith("/")) {
                path = "/" + path.trim();
            }
            if (!path.trim().endsWith("/")) {
                path = path.trim() + "/";
            }
            File pathFile = new File(path);
            if (!pathFile.exists()) {
                pathFile.mkdirs();
            }
            log.info("**************add resource path:{}****************", path);
            registry.addResourceHandler(patterns)
                    .addResourceLocations("file:"+path);
        }
    }
}

package com.hn.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class SpringMvcConfigure implements WebMvcConfigurer {

    @Value("${upload.file.location}")
    private String fileLocation;

    /**
     * 添加静态资源访问
     */
    public void addResourceHanlers(ResourceHandlerRegistry registry) {
        //解决附件上传需要重新部署才能访问到文件的问题
        if (!registry.hasMappingForPattern("/upload/**")) {
            registry.addResourceHandler("/upload/**").addResourceLocations(
                    "file:"+ fileLocation);
        }
    }

}

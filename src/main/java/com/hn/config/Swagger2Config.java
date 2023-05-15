package com.hn.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket webApiConfig(){

        //添加head参数start
        List<Parameter> pars = new ArrayList<>();
        ParameterBuilder tokenPar = new ParameterBuilder();
        tokenPar.name("userId")
                .description("用户ID")
                .defaultValue("1")
                .modelRef(new springfox.documentation.schema.ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        pars.add(tokenPar.build());

        ParameterBuilder tmpPar = new ParameterBuilder();
        tmpPar.name("userTempId")
                .description("临时用户ID")
                .defaultValue("1")
                .modelRef(new springfox.documentation.schema.ModelRef("string"))
                .parameterType("header")
                .required(false)
                .build();
        pars.add(tmpPar.build());
        //添加head参数end

        return new Docket(springfox.documentation.spi.DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(webApiInfo())
                .select()
                .apis(springfox.documentation.builders.RequestHandlerSelectors.basePackage("com.hn.controller"))
                .build()
                .globalOperationParameters(pars);

    }

    private ApiInfo webApiInfo(){

        return new ApiInfoBuilder()
                .title("网站-API文档")
                .description("本文档描述了网站接口定义")
                .version("1.0")
                .contact(new springfox.documentation.service.Contact("hn", "http://localhost:8080/doc.html","1517009589@qq.com"))
                .build();
    }

    private ApiInfo adminApiInfo(){

            return new ApiInfoBuilder()
                    .title("后台管理系统-API文档")
                    .description("本文档描述了后台管理系统接口定义")
                    .version("1.0")
                    .contact(new springfox.documentation.service.Contact("hn", "http://localhost:8080/doc.html","1517009589@qq.com"))
                    .build();
    }
}

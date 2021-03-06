package cn.ncepu.mydeveloping.config;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author: Guodong
 * @Description:
 * @Date: Create in 16:27 2021/7/10
 * @Modified By:
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket webApiConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(webApiInfo())
                .select()
                //扫描该包下面的API注解
                .apis(RequestHandlerSelectors.basePackage("cn.ncepu.mydeveloping.controller"))
                .paths(Predicates.not(PathSelectors.regex("/admin/.*")))
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo webApiInfo(){
        return new ApiInfoBuilder()
                .title("大创信息管理平台项目API文档")
                .description("本文档描述了后台数据传输接口定义")
                .version("1.0")
                .contact(new Contact("Guodong", "https://baidu.com", "944814039@qq.com"))
                .build();
    }
}

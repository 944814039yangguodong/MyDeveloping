package cn.ncepu.mydeveloping.config.satoken;


import cn.dev33.satoken.interceptor.SaAnnotationInterceptor;
import cn.dev33.satoken.interceptor.SaRouteInterceptor;
import cn.dev33.satoken.router.SaRouterUtil;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Arrays;
import java.util.Collections;


/**
 * [Sa-Token 权限认证] 配置类
 *
 * @author Guodong
 */
@Configuration
public class SaTokenConfigure implements WebMvcConfigurer {

    /**
     * 注册sa-token的拦截器，打开注解式鉴权功能
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 注册注解拦截器
        registry.addInterceptor(new SaAnnotationInterceptor()).addPathPatterns("/**").excludePathPatterns("");

        // 注册路由拦截器，自定义验证规则
        registry.addInterceptor(new SaRouteInterceptor((request, response, handler) -> {

            // 登录验证 -- 排除多个路径
            SaRouterUtil.match(Collections.singletonList("/**"), Arrays.asList(
                    "/api/file/download",
                    "/api/notice/public/**",
                    "/static/**",
                    "/common.js",
                    "/package/**",
                    "/images/**",
                    "/layui/**",
                    "/",
                    "/loginPage",
                    "/index.html",
                    "/api/user/login",
                    "/doc.html",
                    "/webjars/**",
                    "/img.icons/**",
                    "/swagger-resources/**",
                    "/v2/api-docs",
                    "/swagger**/**",
                    "/webjars/**",
                    "/v3/**",
                    "/doc.html"
            ), StpUtil::checkLogin);
        })).addPathPatterns("/**");
    }
}

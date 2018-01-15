package com.shixianghui.base.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.shixianghui.interceptor.LoginInterceptor;



/**
 * 控制层注册配置类
 * @author master
 *
 */
@Configuration
public class WebAppConfig extends WebMvcConfigurerAdapter{
	
	/**
	 * 登录拦截器的添加
	 */
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
//                .excludePathPatterns("/index/*")
//                .excludePathPatterns("/address/*")
//                .excludePathPatterns("/doctor/*")
//                .excludePathPatterns("/org/*")
//                .excludePathPatterns("/specialty/*")
//                .excludePathPatterns("/team/listTeams")
//                .excludePathPatterns("/team/getTeam")
//                .excludePathPatterns("/evaluate/selectEvaluatesByPage")
//                .excludePathPatterns("/team/getPackage")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/error")
                .excludePathPatterns("/url/*")
                .excludePathPatterns("/comm/uploadByFile")
                .excludePathPatterns("/comm/getImgByUrl")
                .excludePathPatterns("//");
    }
}

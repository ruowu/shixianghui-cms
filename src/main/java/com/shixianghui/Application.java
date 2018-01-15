package com.shixianghui;

import javax.security.auth.message.config.AuthConfigFactory;

import org.apache.catalina.authenticator.jaspic.AuthConfigFactoryImpl;
import org.mybatis.spring.annotation.MapperScan;
//import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@ServletComponentScan
@Configuration 
@ComponentScan
@EnableAutoConfiguration
@MapperScan("com.shixianghui.base.mapper")  //这个标签可以替代MybatisMapperScanner来扫描mapper
public class Application{
	public static void main(String[] args) throws Exception {
		if (AuthConfigFactory.getFactory() == null) {
            AuthConfigFactory.setFactory(new AuthConfigFactoryImpl());
        }
		SpringApplication.run(Application.class, args);
	}
}

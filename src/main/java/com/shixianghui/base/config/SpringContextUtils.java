package com.shixianghui.base.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * spring强制注入类
 * @author master
 *
 */
@Component
public class SpringContextUtils implements ApplicationContextAware{

	private static ApplicationContext applicationContext; 
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		SpringContextUtils.applicationContext = arg0;
	}
	
	public static ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	
	public static Object getBean(String beanName){
		return applicationContext.getBean(beanName);
	}
}

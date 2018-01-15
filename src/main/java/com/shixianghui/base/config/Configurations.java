package com.shixianghui.base.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.properties")
public class Configurations {
    @Value("${application.salt}")
    private static String MD5_SALT;
    
    @Value("${application.adminUserIds}")
    private static String ADMIN_USER_IDS;

	public static String getMD5_SALT() {
		return MD5_SALT;
	}
	public static String getADMIN_USER_IDS() {
		return ADMIN_USER_IDS;
	}
}
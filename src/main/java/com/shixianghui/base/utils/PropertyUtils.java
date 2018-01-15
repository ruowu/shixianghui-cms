package com.shixianghui.base.utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 		属性文件访问工具类
 * 		加载方式分为一次性加载和每次访问都重新加载
 * 		propsDefault是一次性加载
 * 		propsDynamic是每次动态加载
 */
public class PropertyUtils {

	private static Properties propsDefault = new Properties();
	private static Properties propsDynamic = new Properties();
	
	static {
		try {
			propsDefault.load(PropertyUtils.class.getClassLoader().getResourceAsStream("config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 读取config.properties
	 * @param key
	 * @return
	 */
	public static String get(String key){
		return propsDefault.getProperty(key);
	}
	
	/**
	 * 读取config.properties
	 * @param key
	 * @return
	 */
	public static int getInt(String key){
		String val = propsDefault.getProperty(key);
		if(val != null){
			int v = Integer.parseInt(val);
			return v;
		}
		return Integer.MIN_VALUE;
	}
	
	/**
	 * 读取config.properties
	 * @param key
	 * @return
	 */
	public static long getLong(String key){
		String val = propsDefault.getProperty(key);
		if(val != null){
			long v = Long.parseLong(val);
			return v;
		}
		return Long.MIN_VALUE;
	}

	/**
	 * 读取指定属性文件
	 * @param 	fileName 属性文件名
	 * @param 	key 属性名
	 * return	属性值(没有找到文件或属性,返回null)
	 */
	public static String get(String fileName,String key){
		String value = null;
		try {
			InputStream in = PropertyUtils.class.getClassLoader().getResourceAsStream(fileName);
			propsDynamic.load(in);
			value = propsDynamic.getProperty(key);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return value;
	}
	
	/**
	 * 读取指定属性文件
	 * @param 	fileName 属性文件名
	 * @param 	key 属性名
	 * return	属性值(没有找到文件或属性,返回Integer.MIN_VALUE)
	 */
	public static int getInt(String fileName,String key){
		String val = get(fileName,key);
		if(val != null){
			int v = Integer.parseInt(val);
			return v;
		}
		return Integer.MIN_VALUE;
	}
	
	/**
	 * 读取指定属性文件
	 * @param 	fileName 属性文件名
	 * @param 	key 属性名
	 * return	属性值(没有找到文件或属性,返回Long.MIN_VALUE)
	 */
	public static long getLong(String fileName,String key){
		String val = get(fileName,key);
		if(val != null){
			long v = Long.parseLong(val);
			return v;
		}
		return Long.MIN_VALUE;
	}
	
}
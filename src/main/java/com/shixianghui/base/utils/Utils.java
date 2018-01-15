package com.shixianghui.base.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Utils {
	/**
	 * 判断字符串是否为空，空或者空字符串返回true，非空字符串返回false
	 * 
	 * @param str
	 * @return
	 */
	public static Boolean isBlack(String str) {
		return (str == null || str == "");
	}

	/**
	 * token生成
	 */
	public static String createToken(String userId) {
		if(isBlack(userId)){
			return null;
		}
		String str = userId + PropertyUtils.get("application.salt");
		//拿到md5字符串
		String md5Str = getMD5(str);
		//拿到base64字符串
		String token = getBase64(md5Str);
		return userId + "." + token;
	}
	
	 /**
     * 将 s 进行 BASE64 编码
     *
     * @return String
     * @author lifq
     * @date 2015-3-4 上午09:24:02
     */
    public static String getBase64(String s) {
        if (s == null)
            return null;
        String res = "";
        try {
            res = new sun.misc.BASE64Encoder().encode(s.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return res;
    }


	/**
	 * MD5生成(大写)
	 */
	public static String getMD5(String userId) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
				'A', 'B', 'C', 'D', 'E', 'F' };

		try {
			byte[] btInput = userId.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * 验证token
	 */
	public static Boolean verifyToken(String token) {
		String[] split = token.split("\\.");
		String userId = split[0];
		String token2 = createToken(userId);
		return token2.equals(token);
	}

	/**
	 * 利用spring工具类将两个bean对象对应转换,注意调用强制转换
	 * 
	 * @param <BOUser>
	 * @param source
	 *            源javabean对象
	 * @param target
	 *            目标javabean对象
	 * @return
	 */
	public static <T> T exchangeObject(Object source, Class<T> target) {
		if (StringUtils.isEmpty(source)) {
			return null;
		}
		T t = null;
		try {
			t = target.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			e.printStackTrace();
		}
		BeanUtils.copyProperties(source, t);
		return t;
	}

	/**
	 * 获取当前时间
	 * 
	 * @return
	 */
	public static String getNowTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		return df.format(new Date());
	}

	/**
	 * 将json字符串转化为对应的Object对象
	 * 
	 * @param jsonStr
	 * @param clazz
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public static <T> T jsonStrToObject(String jsonStr, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		return new ObjectMapper().readValue(jsonStr, clazz);
	}

	/**
	 * 将对象转化为json字符串
	 * 
	 * @param object
	 * @return
	 * @throws JsonProcessingException
	 */
	public static String objectToJsonStr(Object object)
			throws JsonProcessingException {
		return new ObjectMapper().writeValueAsString(object);
	}

	/**
	 * 生日转换年龄(年)
	 * 
	 * @param birthTime
	 * @return
	 * @throws ParseException
	 */
	public static int getAge(String birthTime) throws ParseException {
		if (StringUtils.isEmpty(birthTime)) {
			return 0;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();

		if (cal.before(birthTime)) {
			throw new IllegalArgumentException(
					"The birthDay is before Now.It's unbelievable!");
		}
		int yearNow = cal.get(Calendar.YEAR);
		int monthNow = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);
		cal.setTime(sdf.parse(birthTime));
		int yearBirth = cal.get(Calendar.YEAR);
		int monthBirth = cal.get(Calendar.MONTH) + 1;
		int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

		int age = yearNow - yearBirth;

		if (monthNow <= monthBirth) {
			if (monthNow == monthBirth) {
				// monthNow==monthBirth
				if (dayOfMonthNow < dayOfMonthBirth) {
					age--;
				}
			} else {
				// monthNow>monthBirth
				age--;
			}
		}
		return age;
	}

	/**
	 * 从request的header里面取得token
	 * 
	 * @param request
	 * @return
	 */
	public static String getToken(HttpServletRequest request) {
		return request.getHeader("token");
	}
	
	/**
	 * 从request的header里面取得token
	 * 
	 * @param request
	 * @return
	 */
	public static String getUserId(HttpServletRequest request) {
		String token = getToken(request);
		return token.split("\\.")[0];
	}
	
	/**
	 * 拼接字典前缀
	 */
	public static String getPreStr(String type){
		int length = type.length();
		StringBuilder strBuilder = new StringBuilder("");
		length = 5-length;
		while(length>0){
			strBuilder.append("0");
			length--;
		}
		strBuilder.append(type);
		type = strBuilder.toString();
		return type;
	}

	/**
	 * 根据传进来的时间字符串，得到时间
	 * @param saleTimeStr
	 * @return
	 */
	public static Date getTimeByStr(String saleTimeStr) {
		DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		try {
			return format.parse(saleTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
	
}

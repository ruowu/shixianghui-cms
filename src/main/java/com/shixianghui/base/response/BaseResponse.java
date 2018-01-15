package com.shixianghui.base.response;

public class BaseResponse {
	public static final String SECCUSE_CODE = "000000";
	public static final String ERROR_CODE = "100001";
	public static final String FAILED_CODE = "100002";
	public static final String LOGIN_OVER_TIME_CODE = "999999";

	public static final String LOGIN_OVER_TIME = "登录超时，请重新登录";
	public static final String SECCUSE_MESSAGE = "操作成功";
	public static final String ERROR_MESSAGE = "系统繁忙";
	public static final String FAILED_MESSAGE = "操作失败";

	/**
	 * 返回码
	 */
	private String code;
	
	/**
	 * 返回消息
	 */
	private String message;
	
	/**
	 * 用来装对象
	 */
	private Object object;
	
	/**
	 * 返回状态 (0000: 操作成功, 0001 :身份证格式错误, 0002 :身份证和姓名不匹配, 0003 :验证失败)
	 */
	private String state;
	
	/**
	 * 用来装对象(预留)
	 */
	private Object object2;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getObject() {
		return object;
	}

	public void setObject(Object object) {
		this.object = object;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Object getObject2() {
		return object2;
	}

	public void setObject2(Object object2) {
		this.object2 = object2;
	}
}

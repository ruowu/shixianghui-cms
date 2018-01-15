package com.shixianghui.interceptor;


import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.shixianghui.base.response.BaseResponse;
import com.shixianghui.base.utils.Utils;

/**
 * 登录拦截器
 * @author master
 *
 */
@Component
public class LoginInterceptor extends HandlerInterceptorAdapter {
	
	@Override		
	public void afterCompletion(HttpServletRequest request,
			HttpServletResponse response, Object object, Exception e)
			throws Exception {
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response,
			Object object, ModelAndView modelAndView) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object object) throws Exception {
		String token = Utils.getToken(request);
		if(Utils.isBlack(token)){
			response.setCharacterEncoding("UTF-8");  
		    response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
	        out.print("{\"code\" : \""+ BaseResponse.LOGIN_OVER_TIME_CODE +"\", \"message\" : \""+ BaseResponse.LOGIN_OVER_TIME +"\"}");
			return false;
		}
		if(!Utils.verifyToken(token)){
			response.setCharacterEncoding("UTF-8");  
		    response.setContentType("application/json; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.print("{\"code\" : \""+ BaseResponse.LOGIN_OVER_TIME_CODE +"\", \"message\" : \""+ BaseResponse.LOGIN_OVER_TIME +"\"}");
			return false;
		}
//		String handleUri = request.getRequestURI();
//		String[] urlSplit = handleUri.split("/");
//		if("user".equals(urlSplit[1])){//需要初始用户才能操作user控制器的内容
//			String userId = Utils.getUserId(request);
//			if(adminUserIds.indexOf(userId)==-1){//没有此权限
//				response.setCharacterEncoding("UTF-8");  
//			    response.setContentType("application/json; charset=utf-8");
//				PrintWriter out = response.getWriter();
//				out.print("{\"code\" : \""+ BaseResponse.FAILED_CODE +"\", \"message\" : \""+ "对不起，你没有操作这个页面的权限" +"\"}");
//				return false;
//			}
//		}
		return true;
	}
	
}

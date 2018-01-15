package com.shixianghui.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * 使用注解标注过滤器
 */
@WebFilter(filterName="myFilter",urlPatterns="/*")
public class MyFilter implements Filter {

//	private final Logger log = LoggerFactory.getLogger(MyFilter.class);
		
    @Override
    public void destroy() {
//        log.debug("过滤器销毁");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
//        log.debug("执行过滤操作");
    	HttpServletRequest httpServletRequest = (HttpServletRequest)request;
    	 HttpServletResponse httpServletResponse = (HttpServletResponse)response;
    	 httpServletRequest.setCharacterEncoding("UTF-8");
    	 httpServletResponse.setCharacterEncoding("UTF-8");
        chain.doFilter(request, response);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
//        log.debug("过滤器初始化");
    }

}

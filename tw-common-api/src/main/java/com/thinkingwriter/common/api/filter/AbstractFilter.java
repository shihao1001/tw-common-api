package com.thinkingwriter.common.api.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.thinkingwriter.common.api.HttpCustomRequestWrapper;
import com.thinkingwriter.common.api.HttpCustomResponseWrapper;

public class AbstractFilter implements Filter {
	//
	private MultipartResolver multipartResolver;

	public void init(FilterConfig filterConfig) throws ServletException {
		WebApplicationContext webCtx = WebApplicationContextUtils.getWebApplicationContext(filterConfig.getServletContext());	//spring-main.xml容器
		try {
			multipartResolver = webCtx.getBean(MultipartResolver.class);
		} catch (BeansException e) {
			e.printStackTrace();
		}
		multipartResolver = new CommonsMultipartResolver();
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain filterChain) throws IOException, ServletException {
		HttpServletRequest httpServletRequest =  (HttpServletRequest) request;
		/*if(this.multipartResolver.isMultipart((HttpServletRequest) request)){
			 httpServletRequest = multipartResolver.resolveMultipart((HttpServletRequest) request);
			 httpServletRequest = new HttpCustomRequestWrapper((HttpServletRequest) request);
		}*/
		HttpCustomResponseWrapper httpServletResponse = new HttpCustomResponseWrapper((HttpServletResponse) response);
		filterChain.doFilter(request, response);
		httpServletResponse.flushBuffer();
	}

	public void destroy() {
		
	}

}

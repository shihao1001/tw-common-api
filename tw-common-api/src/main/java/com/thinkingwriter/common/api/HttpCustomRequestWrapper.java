package com.thinkingwriter.common.api;

import java.io.IOException;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.springframework.web.multipart.MultipartRequest;
import org.springframework.web.multipart.support.DefaultMultipartHttpServletRequest;

public class HttpCustomRequestWrapper extends HttpServletRequestWrapper {
	
	private MultipartRequest multipartRequest;
	private ServletInputStream inputStream;
	
	public HttpCustomRequestWrapper(HttpServletRequest request) throws IOException {
		super(request);
		this.multipartRequest = new DefaultMultipartHttpServletRequest(request);
		this.inputStream = request.getInputStream();
	}

	@Override
	public ServletInputStream getInputStream() throws IOException {
		return this.inputStream;
	}

}
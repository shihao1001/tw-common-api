package com.thinkingwriter.common.api;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class HttpCustomResponseWrapper extends HttpServletResponseWrapper {
	
	private ServletOutputStream  outputStream;
	private PrintWriter printWriter;
	private ServletOutputStreamCopy servletOutputStreamCopy;

	public HttpCustomResponseWrapper(HttpServletResponse response) {
		super(response);
	}

	@Override
	public ServletOutputStream getOutputStream() throws IOException {
		if(printWriter != null){
			throw new IllegalStateException("getWriter() has already been called on this response.");
		}
		if(outputStream == null){
			outputStream = this.getResponse().getOutputStream();
			servletOutputStreamCopy = new ServletOutputStreamCopy(outputStream);
		}
		return servletOutputStreamCopy;
	}

	@Override
	public PrintWriter getWriter() throws IOException {
		if(outputStream != null){
			throw new IllegalStateException("getOutputStream() has already been called on this response.");
		}
		if(printWriter == null){
			servletOutputStreamCopy = new ServletOutputStreamCopy(this.getResponse().getOutputStream());
			printWriter = new PrintWriter(new OutputStreamWriter(servletOutputStreamCopy,this.getResponse().getCharacterEncoding()),true);
		}
		return printWriter;
	}

	@Override
	public void flushBuffer() throws IOException {
		if(printWriter != null){
			printWriter.flush();
		}else if(outputStream != null){
			servletOutputStreamCopy.flush();
		}
	}
	
	public byte[] getCopy(){
		if(servletOutputStreamCopy != null){
			return servletOutputStreamCopy.getCopy();
		}else{
			return new byte[0];
		}
		
	}
	
}

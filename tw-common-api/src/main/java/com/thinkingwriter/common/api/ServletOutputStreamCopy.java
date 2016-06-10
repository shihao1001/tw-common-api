package com.thinkingwriter.common.api;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.servlet.ServletOutputStream;

public class ServletOutputStreamCopy extends ServletOutputStream {
	
	private ServletOutputStream  outputStream;
	private ByteArrayOutputStream byteOutputStream;
	
	public ServletOutputStreamCopy(ServletOutputStream outputStream) {
		this.outputStream = outputStream;
		this.byteOutputStream = new ByteArrayOutputStream(2048);
	}
	
	@Override
	public void write(int b) throws IOException {
		outputStream.write(b);
		byteOutputStream.write(b);
	}
	
	public byte[] getCopy(){
		return byteOutputStream.toByteArray();
	}




	

}

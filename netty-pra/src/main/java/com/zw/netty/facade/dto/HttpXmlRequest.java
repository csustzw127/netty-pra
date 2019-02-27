package com.zw.netty.facade.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.netty.handler.codec.http.FullHttpRequest;

public class HttpXmlRequest {

	private FullHttpRequest req;
	private Object obj;
	
	public FullHttpRequest getReq() {
		return req;
	}
	public void setReq(FullHttpRequest req) {
		this.req = req;
	}
	public Object getObj() {
		return obj;
	}
	public void setObj(Object obj) {
		this.obj = obj;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
	
}

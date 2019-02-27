package com.zw.netty.facade.dto;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import io.netty.handler.codec.http.FullHttpResponse;

public class HttpXmlResponse {

	private FullHttpResponse res;
	private Object obj;
	
	public FullHttpResponse getRes() {
		return res;
	}
	public void setRes(FullHttpResponse res) {
		this.res = res;
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

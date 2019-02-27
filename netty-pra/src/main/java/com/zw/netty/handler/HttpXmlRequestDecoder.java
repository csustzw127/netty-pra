package com.zw.netty.handler;

import java.util.List;

import com.zw.netty.facade.dto.HttpXmlRequest;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

public class HttpXmlRequestDecoder extends AbstractHttpXmlDecoder<FullHttpRequest> {

	private Class clazz ;
	
	public HttpXmlRequestDecoder(Class clazz) {
		super();
		this.clazz = clazz;
	}
	@Override
	protected void decode(ChannelHandlerContext ctx, FullHttpRequest msg, List<Object> out) throws Exception {
		Object obj = decode(ctx,msg.content(),clazz);
		HttpXmlRequest  req = new HttpXmlRequest();
		req.setReq(msg);
		req.setObj(obj);
		out.add(req);
	 }

}

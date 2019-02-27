package com.zw.netty.handler;

import java.net.InetAddress;
import java.util.List;

import com.zw.netty.facade.dto.HttpXmlRequest;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpRequest;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpMethod;
import io.netty.handler.codec.http.HttpVersion;

/**
 * 1、通过调用父类的encode方法编码pojo对象为xml
 * 2、设置requestheader的信息和requestcontent
 * @author zhouwei
 *
 */
public class HttpXmlRequestEncoder extends AbstractHttpXmlEncoder<HttpXmlRequest> {

	@Override
	protected void encode(ChannelHandlerContext ctx, HttpXmlRequest msg, List<Object> out) throws Exception {
		ByteBuf xmlBuf = encode0(ctx, msg.getObj());
		FullHttpRequest req = msg.getReq();
		if(req == null ) {
			req = new DefaultFullHttpRequest(HttpVersion.HTTP_1_1, HttpMethod.GET, "/do",xmlBuf);
			
			HttpHeaders headers = req.headers();
			headers.set(HttpHeaders.Names.HOST,"127.0.0.1:8081");
			
			headers.set(HttpHeaders.Names.CONNECTION,HttpHeaders.Values.CLOSE);
			headers.set(HttpHeaders.Names.ACCEPT_ENCODING,HttpHeaders.Values.GZIP.toString()+","+HttpHeaders.Values.DEFLATE.toString());
			
			headers.set(HttpHeaders.Names.ACCEPT_CHARSET,"ISO-8895-1,UTF-8;q=0.7,*;q=0.7");
			headers.set(HttpHeaders.Names.USER_AGENT,"Netty xml http Client side");
			headers.set(HttpHeaders.Names.ACCEPT,"text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,image/apng,*/*;q=0.8");
			
		}
		
		HttpHeaders.setContentLength(req, xmlBuf.readableBytes());
		out.add(req);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		super.exceptionCaught(ctx, cause);
		cause.printStackTrace();
	}

	
}

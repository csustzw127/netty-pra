package com.zw.netty.handler;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zw.netty.facade.dto.SubscribeReqProto;
import com.zw.netty.facade.dto.SubscribeReqProto.SubscrieReq.Builder;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ProtoBufClientChannelHandler extends ChannelHandlerAdapter{

	private static Logger logger = LoggerFactory.getLogger(ProtoBufClientChannelHandler.class);
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("client recieve resp: " + msg.toString());
	}

	
	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		for(int i=0;i<10;i++) {
			Builder builder = SubscribeReqProto.SubscrieReq.newBuilder();
			builder.setSubReqID(i);
			builder.setUserName("zhouwei");
			builder.setProductName("volt");
			List<String> addrs = new ArrayList<String>();
			addrs.add("nanjing bank");
			builder.addAddress("nanjing bank");
			ctx.write(builder.build());
//			SubscribeReq req = new SubscribeReq();
//			req.setReqID(i);
//			req.setProductName("netty marshalling");
//			req.setAddress("nanjing");
//			req.setUserName("zhouwei");
//			ctx.write(req);
		}
		ctx.flush();
	}


	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}


	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		logger.info(" - " + ctx.toString());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
		cause.printStackTrace();
		ctx.close();
	}

}

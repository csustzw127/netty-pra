package com.zw.netty.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zw.netty.facade.dto.SubscribeReqProto;
import com.zw.netty.facade.dto.SubscribeRespProto;
import com.zw.netty.facade.dto.SubscribeRespProto.SubscrieResp.Builder;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class ProtoBufServerChannelHandler extends ChannelHandlerAdapter{

	private static Logger logger = LoggerFactory.getLogger(ProtoBufServerChannelHandler.class);
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		logger.info("server recieve req: " + msg.toString());
		Builder builder = SubscribeRespProto.SubscrieResp.newBuilder(); 
		builder.setSubReqID(((SubscribeReqProto.SubscrieReq)msg).getSubReqID());
		builder.setRespCode(0);
		builder.setDesc("success");
		ctx.writeAndFlush(builder.build());
//		SubscribeResp resp = new SubscribeResp();
//		resp.setReqID(((SubscribeReq)msg).getReqID());
//		resp.setRespCode(0);
//		ctx.writeAndFlush(resp);
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

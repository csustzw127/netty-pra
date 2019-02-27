package com.zw.netty.server;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import com.zw.netty.facade.dto.SubscribeReqProto;
import com.zw.netty.facade.dto.SubscribeRespProto;
import com.zw.netty.handler.MySimpleChannelHandler;
import com.zw.netty.handler.ProtoBufClientChannelHandler;
import com.zw.netty.handler.ProtoBufServerChannelHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;

public class ProtoBufNettyClient {

	private EventLoopGroup group;
	private Bootstrap clientStrap;
	
	public static void main(String[] args) {
		new ProtoBufNettyClient().start();
	}
	
	public void start() {
		clientStrap = new Bootstrap();
		group = new NioEventLoopGroup();
		clientStrap.group(group)
				   .channel(NioSocketChannel.class)
				   .handler(new ChannelInitializer<Channel>() {

					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(new ProtobufVarint32FrameDecoder())
									 .addLast(new ProtobufDecoder(SubscribeRespProto.SubscrieResp.getDefaultInstance()))
									 .addLast(new ProtobufVarint32LengthFieldPrepender())
									 .addLast(new ProtobufEncoder())
									 .addLast(new ProtoBufClientChannelHandler());
					}
					   
				});
		ChannelFuture f = clientStrap.connect(new InetSocketAddress("127.0.0.1",8081)).syncUninterruptibly();
//		f.channel().writeAndFlush(f.channel().alloc().buffer().writeBytes("hello \r\n".getBytes()));
		f.channel().closeFuture().syncUninterruptibly();
		group.shutdownGracefully();
	}
}

package com.zw.netty.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zw.netty.facade.dto.SubscribeReqProto;
import com.zw.netty.handler.ProtoBufServerChannelHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.protobuf.ProtobufDecoder;
import io.netty.handler.codec.protobuf.ProtobufEncoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32FrameDecoder;
import io.netty.handler.codec.protobuf.ProtobufVarint32LengthFieldPrepender;

public class ProtoBufNettyServer {
	
	private static Logger logger = LoggerFactory.getLogger(ProtoBufNettyServer.class);
	// accept group
	private EventLoopGroup bossGroup;
	// 与client交互io事件的group
	private EventLoopGroup workGroup;
	
	private ServerBootstrap server;
	
	public static void main(String[] args) {
		new ProtoBufNettyServer().start();
	}
	public void start() {
		
		try {
			server = new ServerBootstrap();
			bossGroup = new NioEventLoopGroup();
			workGroup = new NioEventLoopGroup();
			server.group(bossGroup,workGroup)
				  .channel(NioServerSocketChannel.class)
				  .childHandler(new ChannelInitializer<Channel>() {

					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(new ProtobufVarint32FrameDecoder())
									 .addLast(new ProtobufDecoder(SubscribeReqProto.SubscrieReq.getDefaultInstance()))
									 .addLast(new ProtobufVarint32LengthFieldPrepender())
									 .addLast(new ProtobufEncoder())
									 .addLast(new ProtoBufServerChannelHandler());
					}
					  
				  });
			ChannelFuture f = server.bind(8081).sync();
			f.channel().closeFuture().sync();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			bossGroup.shutdownGracefully();
			workGroup.shutdownGracefully();
		}
		
	}
}

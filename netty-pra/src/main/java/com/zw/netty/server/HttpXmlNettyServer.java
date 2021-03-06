package com.zw.netty.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zw.netty.facade.dto.Order;
import com.zw.netty.handler.HttpXmlRequestDecoder;
import com.zw.netty.handler.MySimpleChannelHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpXmlNettyServer {
	
	private static Logger logger = LoggerFactory.getLogger(HttpXmlNettyServer.class);
	// accept group
	private EventLoopGroup bossGroup;
	// 与client交互io事件的group
	private EventLoopGroup workGroup;
	
	private ServerBootstrap server;
	
	public static void main(String[] args) {
		new HttpXmlNettyServer().start();
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
						ch.pipeline().addLast(new HttpServerCodec())
									 .addLast(new HttpObjectAggregator(64 * 1024))
									 .addLast(new ChunkedWriteHandler()) // 为了写大文件，防止内存溢出
									 .addLast(new HttpXmlRequestDecoder(Order.class))
									 .addLast(new MySimpleChannelHandler());
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

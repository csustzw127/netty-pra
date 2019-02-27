package com.zw.netty.server;

import java.nio.charset.Charset;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zw.netty.handler.MySimpleChannelHandler;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;

public class NettyServer {
	
	private static Logger logger = LoggerFactory.getLogger(NettyServer.class);
	// accept group
	private EventLoopGroup bossGroup;
	// 与client交互io事件的group
	private EventLoopGroup workGroup;
	
	private ServerBootstrap server;
	
	public static void main(String[] args) {
		new NettyServer().start();
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
						ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()[0]))
									 .addLast(new StringDecoder(Charset.forName("UTF-8")))
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

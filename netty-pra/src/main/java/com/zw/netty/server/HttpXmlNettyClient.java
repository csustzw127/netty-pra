package com.zw.netty.server;

import java.net.InetSocketAddress;

import com.zw.netty.handler.HttpXmlRequestEncoder;
import com.zw.netty.handler.SimpleClientChannelHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpRequestEncoder;
import io.netty.handler.codec.http.HttpResponseDecoder;
import io.netty.handler.stream.ChunkedWriteHandler;

public class HttpXmlNettyClient {

	private EventLoopGroup group;
	private Bootstrap clientStrap;
	
	public static void main(String[] args) {
		new HttpXmlNettyClient().start();
	}
	
	public void start() {
		clientStrap = new Bootstrap();
		group = new NioEventLoopGroup();
		clientStrap.group(group)
				   .channel(NioSocketChannel.class)
				   .handler(new ChannelInitializer<Channel>() {

					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(new HttpObjectAggregator(64 * 1024))
						 .addLast(new HttpRequestEncoder())
						 .addLast(new HttpResponseDecoder())
						 .addLast(new ChunkedWriteHandler()) // 为了写大文件，防止内存溢出
						 .addLast(new HttpXmlRequestEncoder())
						 .addLast(new SimpleClientChannelHandler());
						
					}
					   
				});
		ChannelFuture f = clientStrap.connect(new InetSocketAddress("127.0.0.1",8081)).syncUninterruptibly();
//		f.channel().writeAndFlush(f.channel().alloc().buffer().writeBytes("hello \r\n".getBytes()));
		f.channel().closeFuture().syncUninterruptibly();
		group.shutdownGracefully();
	}
}

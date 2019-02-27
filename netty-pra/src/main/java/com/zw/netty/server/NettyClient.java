package com.zw.netty.server;

import java.net.InetSocketAddress;
import java.nio.charset.Charset;

import com.zw.netty.handler.MySimpleChannelHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.Delimiters;
import io.netty.handler.codec.string.StringDecoder;

public class NettyClient {

	private EventLoopGroup group;
	private Bootstrap clientStrap;
	
	public static void main(String[] args) {
		new NettyClient().start();
	}
	
	public void start() {
		clientStrap = new Bootstrap();
		group = new NioEventLoopGroup();
		clientStrap.group(group)
				   .channel(NioSocketChannel.class)
				   .handler(new ChannelInitializer<Channel>() {

					@Override
					protected void initChannel(Channel ch) throws Exception {
						ch.pipeline().addLast(new DelimiterBasedFrameDecoder(1024, Delimiters.lineDelimiter()[0]))
						 			 .addLast(new StringDecoder(Charset.forName("UTF-8")))
						 			 .addLast(new MySimpleChannelHandler());
					}
					   
				});
		ChannelFuture f = clientStrap.connect(new InetSocketAddress("127.0.0.1",8081)).syncUninterruptibly();
		f.channel().writeAndFlush(f.channel().alloc().buffer().writeBytes("hello \r\n".getBytes()));
		f.channel().closeFuture().syncUninterruptibly();
		group.shutdownGracefully();
	}
}

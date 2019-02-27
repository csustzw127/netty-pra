package com.zw.netty.handler;

import com.zw.netty.bindgen.JibxXmlCodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;

public abstract class AbstractHttpXmlDecoder<I> extends MessageToMessageDecoder<I> {

	// TODO 探讨一下这里解码和编码是否是线程安全的
	protected Object decode(ChannelHandlerContext ctx,ByteBuf content,Class clazz) throws Exception {
		
		return JibxXmlCodec.newInstance().decode(content.toString(JibxXmlCodec.getCharset()),clazz);
		
	}
}

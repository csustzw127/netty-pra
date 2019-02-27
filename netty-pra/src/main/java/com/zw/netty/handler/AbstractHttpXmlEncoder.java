package com.zw.netty.handler;

import com.zw.netty.bindgen.JibxXmlCodec;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageEncoder;

/**
 * 编码pojo对象为xml格式的string对象
 * @author zhouwei
 *
 * @param <T>
 */
public abstract class AbstractHttpXmlEncoder<T> extends MessageToMessageEncoder<T> {
	
	JibxXmlCodec jibxCodec;
	
	public AbstractHttpXmlEncoder() {
		this(JibxXmlCodec.newInstance());
	}
	
    public AbstractHttpXmlEncoder(JibxXmlCodec jibxCodec) {
		this.jibxCodec = jibxCodec;
	}

	// TODO 这里对于protected修饰的类又是什么作用？
    protected ByteBuf encode0(ChannelHandlerContext ctx,Object obj) throws Exception {
    	String xmlStr = jibxCodec.encode(obj);
        ByteBuf buf = ctx.alloc().buffer();
        buf.writeBytes(xmlStr.getBytes(JibxXmlCodec.getCharset()));
        return buf;
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    	// 释放资源
//    	JibxXmlCodec.close();
    	if(jibxCodec != null) {
    		jibxCodec.close();
    	}
    	
    	cause.printStackTrace();
    }
}

package com.zw.netty.marshalling;

import org.jboss.marshalling.MarshallerFactory;
import org.jboss.marshalling.Marshalling;
import org.jboss.marshalling.MarshallingConfiguration;

import io.netty.handler.codec.marshalling.DefaultMarshallerProvider;
import io.netty.handler.codec.marshalling.DefaultUnmarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallerProvider;
import io.netty.handler.codec.marshalling.MarshallingDecoder;
import io.netty.handler.codec.marshalling.MarshallingEncoder;
import io.netty.handler.codec.marshalling.UnmarshallerProvider;

public class MarshallingCodecFactory {
	
	public static MarshallingDecoder buildMarshallingDecoder() {
		final MarshallerFactory marshallingFactory = 
				Marshalling.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration c = new MarshallingConfiguration();
		c.setVersion(5);
		UnmarshallerProvider p = new DefaultUnmarshallerProvider(marshallingFactory, c);
		MarshallingDecoder decoder = new MarshallingDecoder(p,1024);
		return decoder;
	}
	
	public static MarshallingEncoder buildMarshallingEncoder() {
		final MarshallerFactory marshallingFactory = 
				Marshalling.getProvidedMarshallerFactory("serial");
		final MarshallingConfiguration c = new MarshallingConfiguration();
		c.setVersion(5);
		MarshallerProvider p = new DefaultMarshallerProvider(marshallingFactory, c);
		MarshallingEncoder encoder = new MarshallingEncoder(p);
		return encoder;
	}
}

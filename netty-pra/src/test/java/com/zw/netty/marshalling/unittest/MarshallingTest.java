package com.zw.netty.marshalling.unittest;

import org.testng.annotations.Test;

import com.zw.netty.marshalling.MarshallingCodecFactory;

public class MarshallingTest {

	@Test
	public void test() {
		MarshallingCodecFactory.buildMarshallingDecoder();
	}
}

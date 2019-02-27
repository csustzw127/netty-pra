package com.zw.netty.proto.unittest;

import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

import com.google.protobuf.InvalidProtocolBufferException;
import com.zw.netty.facade.dto.SubscribeReqProto;
import com.zw.netty.facade.dto.SubscribeReqProto.SubscrieReq.Builder;

public class ProtoVoTest {

	public static byte[] encode(SubscribeReqProto.SubscrieReq req) {
		return req.toByteArray();
	}
	
	public static SubscribeReqProto.SubscrieReq decode(byte[] bs) throws InvalidProtocolBufferException {
		return SubscribeReqProto.SubscrieReq.parseFrom(bs);
	}
	
	public static SubscribeReqProto.SubscrieReq createSubscribeReq() {
		Builder builder = SubscribeReqProto.SubscrieReq.newBuilder();
		builder.setSubReqID(1);
		builder.setUserName("zhouwei");
		builder.setProductName("volt");
		List<String> addrs = new ArrayList<String>();
		addrs.add("nanjing bank");
		builder.addAllAddress(addrs);
		return builder.build();
	}
	
	@Test
	public void test() throws InvalidProtocolBufferException {
		SubscribeReqProto.SubscrieReq req = createSubscribeReq();
		System.out.println(req.toString());
		SubscribeReqProto.SubscrieReq req2 = decode(encode(req));
		System.out.println(req2.toString());
		System.out.println(req.equals(req2));
	}
}

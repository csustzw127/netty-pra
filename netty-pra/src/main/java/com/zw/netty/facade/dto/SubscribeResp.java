package com.zw.netty.facade.dto;

public class SubscribeResp {
	
	private int reqID;
	private int respCode;
	
	public int getReqID() {
		return reqID;
	}
	public void setReqID(int reqID) {
		this.reqID = reqID;
	}
	public int getRespCode() {
		return respCode;
	}
	public void setRespCode(int respCode) {
		this.respCode = respCode;
	}
}

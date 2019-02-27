package com.zw.netty.enumrate;

public enum Shipping {

	NORMAL("normal","普通快递"),
	HOMEDELIVERY("homedelivery","宅急送");
	
	private String  desc;
	private String  code;
	
	private Shipping(String desc, String code) {
		this.desc = desc;
		this.code = code;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}

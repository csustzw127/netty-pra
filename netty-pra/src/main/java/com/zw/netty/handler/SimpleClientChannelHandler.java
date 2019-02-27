package com.zw.netty.handler;

import com.zw.netty.enumrate.Shipping;
import com.zw.netty.facade.dto.Customer;
import com.zw.netty.facade.dto.HttpXmlRequest;
import com.zw.netty.facade.dto.Order;

import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;

public class SimpleClientChannelHandler extends ChannelHandlerAdapter{

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		 // 创建一个 Order 实例
    	Customer c = new Customer();
    	c.setCustomerID(123);
    	c.setCustomerName("zhouwei");
        Order order = new Order();
        order.setShip(Shipping.HOMEDELIVERY);
        order.setSum(12.01f);
        order.setC(c);
        HttpXmlRequest req = new HttpXmlRequest();
        req.setObj(order);
        
        ctx.writeAndFlush(req);
	}

}

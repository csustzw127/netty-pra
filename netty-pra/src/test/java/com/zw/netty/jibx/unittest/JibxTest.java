package com.zw.netty.jibx.unittest;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.io.StringWriter;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;
import org.jibx.runtime.JiBXException;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.zw.netty.enumrate.Shipping;
import com.zw.netty.facade.dto.Agency;
import com.zw.netty.facade.dto.Customer;
import com.zw.netty.facade.dto.Order;

public class JibxTest {
	private IBindingFactory factory = null;
    private StringWriter writer = null;
    private StringReader reader = null;
    private final static String CHARSET_NAME = "UTF-8";

    private String encode2Xml(Order order) throws JiBXException, IOException {
        factory = BindingDirectory.getFactory(Order.class);
        writer = new StringWriter();
        IMarshallingContext mctx = factory.createMarshallingContext();
        mctx.setIndent(2);
        mctx.marshalDocument(order, CHARSET_NAME, null, writer);
        String xmlStr = writer.toString();
        writer.close();
        System.out.println(xmlStr.toString());
        return xmlStr;
    }

    private Order decode2Order(String xmlBody) throws JiBXException {
        reader = new StringReader(xmlBody);
        IUnmarshallingContext uctx = factory.createUnmarshallingContext();
        Order order = (Order) uctx.unmarshalDocument(reader);
        return order;
    }

    @Test
    public void test() throws JiBXException, IOException {

        // 创建一个 Order 实例
    	Customer c = new Customer();
    	c.setCustomerID(123);
    	c.setCustomerName("zhouwei");
        Order order = new Order();
        order.setShip(Shipping.HOMEDELIVERY);
        order.setSum(12.01f);
        order.setC(c);
        // 转换成 xml
        String body = encode2Xml(order);
//        System.out.println(body);
        // 转换回对象
        Order order2 = decode2Order(body);

        System.out.println(order2);
    }
    
    @Test
    public void readLocalJson() throws IOException {
    	String key="E:/ods_itfin_wjfl.20190120.dat";
    	File file = new File(key);
		FileInputStream fileInput = null;
		try {
			fileInput = new FileInputStream(file);
		} catch (FileNotFoundException e1) {
			throw new RuntimeException(e1);
		}
		BufferedReader reader = new BufferedReader(new InputStreamReader(fileInput));
		StringBuilder JSON_ARRAY_STR = new StringBuilder();
		String tmp;
		while((tmp=reader.readLine()) != null) {
			JSON_ARRAY_STR.append(tmp);
		}
		JSONArray jsonArray = JSON.parseArray(JSON_ARRAY_STR.toString());
		
		 //遍历方式1
        int size = jsonArray.size();
        for (int i = 0; i < size; i++){
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Agency a = JSONObject.toJavaObject(jsonObject, Agency.class);
            System.out.println(a);
        }
    }
    
    @Test
    public void generate20Wline() throws IOException {
    	int line  = 20;
    	String key="E:/ods_itfin_wjfl.20190120.dat";
    	File file = new File(key);
    	FileOutputStream fileOut = new FileOutputStream(file);
    	BufferedOutputStream bo = new BufferedOutputStream(fileOut);
    	StringBuilder sb = new StringBuilder();
    	bo.write("[".getBytes());
//    	sb.append("[");
    	for(int i=0;i<line;i++) {
    		String data = "{ \"acctnbr\" : \""+ (i+100000000000L) +"\" ,\"payacctnbr\" : "
    				+" \""+ (i+100000000000L) +"\", " + " \"amt\" "+ ": \""+i+"\"}";
    		     		bo.write(data.getBytes());
    		if(i != line-1)
    			bo.write(",".getBytes());
//    		sb.append(data);
    	}
    	bo.write("]".getBytes());
    	bo.flush();
//    	sb.append("]");
    }
    
    @Test
    public void test1() {
    	String data = "{ \"ownerid\" : \""+ (1+100000000000L) +"\" ,\"acctnbr\" : "
				+" \""+ (1+100000000000L) +"\", " + " \"decimal\" "+ ": \""+1+"\"}";
    	System.err.println(data);
    	
    	String t = "123456789";
    	System.out.println(t.substring(t.length()-3,t.length()-1));
    }
}

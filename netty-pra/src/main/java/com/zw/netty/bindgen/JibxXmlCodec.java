package com.zw.netty.bindgen;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.nio.charset.Charset;

import org.jibx.runtime.BindingDirectory;
import org.jibx.runtime.IBindingFactory;
import org.jibx.runtime.IMarshallingContext;
import org.jibx.runtime.IUnmarshallingContext;

public class JibxXmlCodec {
	private IBindingFactory factory = null;
    private StringWriter writer = null;
    private StringReader reader = null;
//    private static JibxXmlCodec instance = null;
//    private StringReader reader = null;
    // TODO 对于这类不会改变的变量用 final 和 static 修饰有什么用？
    protected final static String CHARSET_NAME = "UTF-8";
    protected final static Charset UTF_8 = Charset.forName(CHARSET_NAME);
    
    private JibxXmlCodec() {
    	
    }
    
    public String encode(Object obj) throws Exception {
    	factory = BindingDirectory.getFactory(obj.getClass());
        writer = new StringWriter();
        IMarshallingContext mctx = factory.createMarshallingContext();
        mctx.setIndent(2);
        mctx.marshalDocument(obj, CHARSET_NAME, null, writer);
        String xmlStr = writer.toString();
        writer.close();
        System.out.println(xmlStr.toString());
       
        return xmlStr;
    }
    
    public Object decode(String xml,Class clazz) throws Exception {
    	factory = BindingDirectory.getFactory(clazz);
    	reader = new StringReader(xml);
        IUnmarshallingContext uctx = factory.createUnmarshallingContext();
        Object obj =  uctx.unmarshalDocument(reader);
    	return obj;
    }
    
    
    // 懒加载的非线程安全的，可以加上同步
//    public static JibxXmlCodec getInstance() {
//    	
//    	if(instance == null) {
//    		instance = new JibxXmlCodec();
//    		return instance;
//    	}
//    	return instance;
//    }

	public static Charset getCharset() {
		return UTF_8;
	}

	public void close() throws IOException {
		
		if(writer != null) {
			writer.close();
			writer = null;
		}
		if(reader != null) {
			reader.close();
			reader = null;
		}
	} 

	public static JibxXmlCodec newInstance() {
		// TODO Auto-generated method stub
		return new JibxXmlCodec();
	}
    
}

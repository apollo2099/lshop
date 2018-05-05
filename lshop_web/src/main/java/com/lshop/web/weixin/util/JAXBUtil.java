package com.lshop.web.weixin.util;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

/**
 * 由于项目中使用了第3方的jaxb库：jaxb-impl-2.1.13.jar,jaxb-api-2.1.jar，所以只能引用第3方jaxb库中的类：com.sun.xml.bind.marshaller.CharacterEscapeHandler，
 * 而不能使用jdk自带库中的类，com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler
 * JinJian 2015-01-08
 */
import com.sun.xml.bind.marshaller.CharacterEscapeHandler;
//import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

public class JAXBUtil {	
    public static String convertToXml(Object obj) {  
        String result = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(obj.getClass());  
            Marshaller marshaller = context.createMarshaller();  
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, false);  
            marshaller.setProperty(CharacterEscapeHandler.class.getName(),
    				new CharacterEscapeHandler() {

    					@Override
    					public void escape(char[] arg0, int arg1, int arg2,
    							boolean arg3, Writer arg4) throws IOException {
    						arg4.write(arg0, arg1, arg2);
    					}
    				});            
            marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");  
  
            StringWriter writer = new StringWriter();  
            marshaller.marshal(obj, writer);  
            result = writer.toString();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return result; 
    } 
    
    @SuppressWarnings("unchecked")
	public static <T> T convertToJavaBean(String xml, Class<T> c) {
        T t = null;  
        try {  
            JAXBContext context = JAXBContext.newInstance(c);  
            Unmarshaller unmarshaller = context.createUnmarshaller();  
            t = (T) unmarshaller.unmarshal(new StringReader(xml));  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
        return t;  
    }
}

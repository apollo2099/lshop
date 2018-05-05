package com.lshop.common.spring;

import java.util.Enumeration;
import java.util.Properties;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import com.gv.core.util.cryption.DES;

public class PropertyPlaceholderConfigurerImpl extends
		PropertyPlaceholderConfigurer {

	@Override
	protected void processProperties(
			ConfigurableListableBeanFactory beanFactory, Properties props)
			throws BeansException {
		Enumeration<Object> keys=	props.keys();
		while (keys.hasMoreElements()) {
			String key = (String) keys.nextElement();
			if(key.endsWith(".des")){
				String value = props.getProperty(key);
				if (value != null) {// **解密过程**
					
					String strDes =DES.decrypt(DES.decrypt(value.trim()));// 密文解密成明文
					props.remove(key);
					props.setProperty(key.substring(0, key.length()-4), strDes);// 赋值
				}
			}
			
		}
		super.processProperties(beanFactory, props);// 调用父方法

	}
	

}

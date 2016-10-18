package com.pauu.javahence.jdk5;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class BeanFactory {
	private Properties props = new Properties();
	public BeanFactory(InputStream ips){
		try {
			props.load(ips);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Object getBean(String name){
		String className = props.getProperty(name);
		Object bean = null;
		try {
			Class clazz = Class.forName(className);
			bean = clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		if(bean instanceof ProxyFactoryBean){
			ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean) bean;
			try {
				proxyFactoryBean.setAdvice((Advice)Class.forName(props.getProperty(name+".advice")).newInstance());
				proxyFactoryBean.setTarget(Class.forName(props.getProperty(name+".target")).newInstance());
			} catch (Exception e) {
				e.printStackTrace();
			} 
			Object proxy = proxyFactoryBean.getProxy();
			return proxy;
		}
		return bean;
	}
}

package com.pauu.javahence.jdk5;

import java.io.InputStream;

public class AopTest {
	public static void main(String[] args) {
		InputStream ips = AopTest.class.getResourceAsStream("proxy.properties");
		Object bean = new BeanFactory(ips).getBean("xxx");
		System.out.println(bean.getClass().getName());
	}
}

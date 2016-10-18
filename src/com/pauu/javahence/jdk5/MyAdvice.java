package com.pauu.javahence.jdk5;

import java.lang.reflect.Method;

public class MyAdvice implements Advice {
	long beginTime = 0;
	long endTime = 0;
	@Override
	public void beforeMethod(Method method) {
		beginTime = System.currentTimeMillis();
		System.out.println("beforeMethod....");
	}

	@Override
	public void afterMethod(Method method) {
		endTime = System.currentTimeMillis();
		System.out.println(method.getName()+" hosts "+(endTime - beginTime));
		System.out.println("afterMethod....");
	}

}

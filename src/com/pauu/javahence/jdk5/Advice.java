package com.pauu.javahence.jdk5;

import java.lang.reflect.Method;

public interface Advice {
	public abstract void beforeMethod(Method method);
	public abstract void afterMethod(Method method);
}

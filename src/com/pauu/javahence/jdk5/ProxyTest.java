package com.pauu.javahence.jdk5;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;

public class ProxyTest {
	public static void main(String[] args) throws Exception {
		Class clazzProxy1 = Proxy.getProxyClass(Collection.class.getClassLoader(), Collection.class);
		System.out.println(clazzProxy1.getName());
		/*
		 * 打印出所有的构造方法以及方法
		 * 样式：$Proxy0(),$Proxy(String,int)
		 */
		Constructor[] constructors = clazzProxy1.getConstructors();
		for(Constructor constructor:constructors){
			String constructorName = constructor.getName();
			StringBuilder sBuilder = new StringBuilder(constructorName);
			sBuilder.append("(");
			Class[] clazzParams = constructor.getParameterTypes();
			for(Class clazzParam:clazzParams){
				sBuilder.append(clazzParam.getName()).append(",");
			}
			if(clazzParams!=null&&clazzParams.length!=0){
				sBuilder.deleteCharAt(sBuilder.length()-1);
			}
			sBuilder.append(")");
			System.out.println(sBuilder.toString());
		}
		
		Method[] methods = clazzProxy1.getMethods();
		for(Method method:methods){
			String methodName = method.getName();
			StringBuilder sBuilder = new StringBuilder(methodName);
			sBuilder.append("(");
			Class[] clazzParams = method.getParameterTypes();
			for(Class clazzParam:clazzParams){
				sBuilder.append(clazzParam.getName()).append(",");
			}
			if(clazzParams!=null&&clazzParams.length!=0){
				sBuilder.deleteCharAt(sBuilder.length()-1);
			}
			sBuilder.append(")");
			System.out.println(sBuilder.toString());
		}
		class MyInvocationHandler implements InvocationHandler{

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return null;
			}
			
		}
		Constructor constructor = clazzProxy1.getConstructor(InvocationHandler.class);
		Collection proxy1 = (Collection) constructor.newInstance(new MyInvocationHandler());
		System.out.println(proxy1);
		
		Collection proxy2 = (Collection) constructor.newInstance(new InvocationHandler(){

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return null;
			}
			
		});
		
		//合成一步生成代理对象
		final ArrayList target = new ArrayList();
		Collection proxy3 = (Collection) getProxy(target,new MyAdvice());
		proxy3.add("zxx");
		proxy3.add("bxd");
		proxy3.add("lhm");
		System.out.println(proxy3.size());
	}

	private static Object getProxy(final Object target,final Advice advice) {
		Object proxy3 = Proxy.newProxyInstance(
				target.getClass().getClassLoader(), 
				target.getClass().getInterfaces(), 
				new InvocationHandler() {
					@Override
					public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
						advice.beforeMethod(method);
						Object retVal = method.invoke(target, args);
						advice.afterMethod(method);
						return retVal;
					}
				}
				);
		return proxy3;
	}
}

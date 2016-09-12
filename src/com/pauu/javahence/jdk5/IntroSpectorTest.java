package com.pauu.javahence.jdk5;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.PropertyUtils;

public class IntroSpectorTest {
	public static void main(String[] args) throws Exception {
		ReflectionPoint pt = new ReflectionPoint(3, 5);
		String propertyName = "x";
		System.out.println(getProperty(pt, propertyName));
		
		setProperty(pt, propertyName);
		System.out.println(pt.getX());
		
		//BeanUtils π”√
		System.out.println(BeanUtils.getProperty(pt, "x"));
		System.out.println(BeanUtils.getProperty(pt, "x").getClass().getName());//java.lang.String
		BeanUtils.setProperty(pt, "x", "9");
		System.out.println(pt.getX());
		System.out.println(PropertyUtils.getProperty(pt, "x"));
		System.out.println(PropertyUtils.getProperty(pt, "x").getClass().getName());//java.lang.Integer
		PropertyUtils.setProperty(pt,"x", 10);
		System.out.println(pt.getX());
	}

	private static void setProperty(ReflectionPoint pt, String propertyName)
			throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		PropertyDescriptor pd2 = new PropertyDescriptor(propertyName, pt.getClass());
		Method methodSetX = pd2.getWriteMethod();
		Object obj = 7;
		methodSetX.invoke(pt,obj);
	}

	private static Object getProperty(ReflectionPoint pt, String propertyName)
			throws IntrospectionException, IllegalAccessException, InvocationTargetException {
		/*PropertyDescriptor pd1 = new PropertyDescriptor(propertyName, pt.getClass());
		Method methodGetX = pd1.getReadMethod();
		Object retVal = methodGetX.invoke(pt);
		return retVal;*/
		BeanInfo beanInfo = Introspector.getBeanInfo(pt.getClass());
		PropertyDescriptor[] pds = beanInfo.getPropertyDescriptors();
		Object retVal = null;
		for(PropertyDescriptor pd : pds){
			if(pd.getName().equals(propertyName)){
				Method methodGetX = pd.getReadMethod();
				retVal = methodGetX.invoke(pt);
			}
		}
		return retVal;
	}
}

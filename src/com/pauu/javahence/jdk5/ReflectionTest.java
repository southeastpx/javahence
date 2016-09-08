package com.pauu.javahence.jdk5;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectionTest {
	public static void main(String[] args) throws Exception{
		String str1 = "abc";
		Class cls1 = str1.getClass();
		Class cls2 = String.class;
		Class cls3 = Class.forName("java.lang.String");
		System.out.println(cls1==cls2);//true,表示内存中只存储一份字节码文件
		System.out.println(cls1==cls3);//true,表示内存中只存储一份字节码文件
		System.out.println(cls1.isPrimitive());//false,String不是基本类型
		System.out.println(int.class.isPrimitive());//true,int是基本类型
		System.out.println(Integer.class.isPrimitive());//false,Integer不是基本类型
		System.out.println(int.class == Integer.class);//false
		System.out.println(int.class == Integer.TYPE);//true
		System.out.println(int[].class.isPrimitive());//false,数组不是基本类型
		System.out.println(int[].class.isArray());//true
		
		Constructor constructor = String.class.getConstructor(StringBuffer.class);
		String str2 = (String) constructor.newInstance(new StringBuffer("abc"));
		System.out.println(str2.charAt(2));//c
		
		ReflectionPoint point = new ReflectionPoint(3, 5);
		Field fieldY = point.getClass().getField("y");
		System.out.println(fieldY.get(point));//5
		//Field fieldX = point.getClass().getField("x");//报错，getField()方法不能访问private声明的变量
		Field fieldX = point.getClass().getDeclaredField("x");
		fieldX.setAccessible(true);//必须设置可访问，不然下行报错
		System.out.println(fieldX.get(point));
		
		changeStringValue(point);
		System.out.println(point);
		
		Method methodCharAt = String.class.getMethod("charAt",int.class);
		System.out.println(methodCharAt.invoke(str1, 1));//b
		System.out.println(methodCharAt.invoke(str1, new Object[]{2}));//c
		
	}

	private static void changeStringValue(Object point) throws Exception {
		Field[] fields = point.getClass().getFields();
		for(Field field : fields){
			if(field.getType()==String.class){
				String oldValue = (String) field.get(point);
				String newValue = oldValue.replace('b', 'a');
				field.set(point, newValue);
			}
		}
	}
}

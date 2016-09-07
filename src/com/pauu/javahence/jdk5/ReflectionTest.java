package com.pauu.javahence.jdk5;

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
	}
}

package com.pauu.javahence.jdk5;

import java.util.Date;

public class ClassLoaderTest {
	public static void main(String[] args) throws Exception {
		System.out.println(ClassLoaderTest.class.getClassLoader().getClass().getName());//sun.misc.Launcher$AppClassLoader
		System.out.println(System.class.getClassLoader());//null,表示由顶层的BootStrap类加载器加载
		ClassLoader loader = ClassLoaderTest.class.getClassLoader();
		while(loader!=null){
			System.out.println(loader.getClass().getName());
			loader = loader.getParent();
		}
		
		//System.out.println(new ClassLoaderAttchment().toString());
		Class clazz = new MyClassLoader("itcastbin").loadClass("com.pauu.javahence.jdk5.ClassLoaderAttchment");
		Date d1 = (Date) clazz.newInstance();
		System.out.println(d1);
	}
}

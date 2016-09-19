package com.pauu.javahence.jdk5;

public class AnnotationTest {
	//@SuppressWarnings压缩警告
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		System.runFinalizersOnExit(true);
	}
	
	//@Deprecated表示此方法已过时
	@Deprecated
	public static void sayHello(){
		System.out.println("hello,java!");
	}
}

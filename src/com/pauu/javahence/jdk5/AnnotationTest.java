package com.pauu.javahence.jdk5;

public class AnnotationTest {
	//@SuppressWarningsѹ������
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {
		System.runFinalizersOnExit(true);
	}
	
	//@Deprecated��ʾ�˷����ѹ�ʱ
	@Deprecated
	public static void sayHello(){
		System.out.println("hello,java!");
	}
}

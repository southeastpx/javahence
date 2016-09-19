package com.pauu.javahence.jdk5;

import java.lang.annotation.Annotation;

@ItcastAnnotation(color="red",value="abc",arrayAttr={1,2,3},annotationAttr=@MetaAnnotation(value="flx"))
public class AnnotationTest {
	//@SuppressWarningsѹ������
	@SuppressWarnings("deprecation")
	@ItcastAnnotation("xyz")
	public static void main(String[] args) {
		System.runFinalizersOnExit(true);
		if(AnnotationTest.class.isAnnotationPresent(ItcastAnnotation.class)){//��AnnotationTest����ע��
			//�õ�ע��
			ItcastAnnotation annotation = (ItcastAnnotation)AnnotationTest.class.getAnnotation(ItcastAnnotation.class);
			System.out.println(annotation.color());
			System.out.println(annotation.value());
			System.out.println(annotation.arrayAttr().length);
			System.out.println(annotation.lamp().nextLamp().name());
			System.out.println(annotation.annotationAttr().value());
		}
	}
	
	//@Deprecated��ʾ�˷����ѹ�ʱ
	@Deprecated
	public static void sayHello(){
		System.out.println("hello,java!");
	}
}

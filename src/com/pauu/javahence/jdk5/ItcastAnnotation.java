package com.pauu.javahence.jdk5;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//����Ԫע�⣬��ע�Ᵽ�������н׶�,Ĭ��ΪRetentionPolicy.CLASS
@Retention(RetentionPolicy.RUNTIME)
//����Ԫע�⣬ָ��ע���������Щ�ɷ���
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface ItcastAnnotation {
	//Ϊע���������,�ͽӿ�һ��������ķ�������public abstract�ģ���ʡ��
	String color() default "blue";
	String value();
	int[] arrayAttr() default {4,5,6};
	EnumTest.TrafficLamp lamp() default EnumTest.TrafficLamp.GREEN;
	MetaAnnotation annotationAttr() default @MetaAnnotation(value="lhm");
}

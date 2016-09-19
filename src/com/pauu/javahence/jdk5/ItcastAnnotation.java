package com.pauu.javahence.jdk5;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

//定义元注解，将注解保留到运行阶段,默认为RetentionPolicy.CLASS
@Retention(RetentionPolicy.RUNTIME)
//定义元注解，指定注解添加在哪些成分上
@Target({ElementType.METHOD,ElementType.TYPE})
public @interface ItcastAnnotation {
	//为注解添加属性,和接口一样，下面的方法都是public abstract的，可省略
	String color() default "blue";
	String value();
	int[] arrayAttr() default {4,5,6};
	EnumTest.TrafficLamp lamp() default EnumTest.TrafficLamp.GREEN;
	MetaAnnotation annotationAttr() default @MetaAnnotation(value="lhm");
}

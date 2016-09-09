package com.pauu.javahence.jdk5;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

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
		
		//TestArguments.main(new String[]{"aaa","bbb","ccc"});
		//用反射调用其他类的main方法
		String className = args[0];
		Method mainMethod = Class.forName(className).getMethod("main", String[].class);
		//下面两行效果相同
		mainMethod.invoke(null, new Object[]{new String[]{"111","222","333"}});
		mainMethod.invoke(null, (Object)new String[]{"111","222","333"});
		
		//数组，只有当类型相同，并且维度相同时，才是同一份字节码
		int[] a1 = new int[3];
		int[] a2 = new int[4];
		int[][] a3 = new int[2][3];
		String[] a4 = new String[3];
		System.out.println(a1.getClass() == a2.getClass());//true
		//System.out.println(a1.getClass() == a3.getClass());//false
		//System.out.println(a1.getClass() == a4.getClass());//false
		System.out.println(a1.getClass().getName());//[I
		System.out.println(a1.getClass().getSuperclass().getName());//java.lang.Object
		System.out.println(a3.getClass().getSuperclass().getName());//java.lang.Object
		
		int[] a5 = new int[]{1,2,3};
		String[] a6 = new String[]{"a","b","c"};
		Integer[] a7 = new Integer[]{4,5,6};
		System.out.println(Arrays.asList(a5));//[[I@18a49e0]
		System.out.println(Arrays.asList(a6));//[a, b, c]
		System.out.println(Arrays.asList(a7));//[4, 5, 6]
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

class TestArguments{
	public static void main(String[] args) {
		for(String arg : args){
			System.out.println(arg);
		}
	}
}

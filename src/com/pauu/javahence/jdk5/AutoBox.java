package com.pauu.javahence.jdk5;

public class AutoBox {
	public static void main(String[] args) {
		Integer i=3;//自动装箱
		System.out.println(i+12);//自动拆箱
		//享元模式的应用,设计者认为-128~127的整数是经常使用的，
		//所以做了一个共享池，每次都从共享池中取，取得的是同一个对象
		Integer i1 = 3;
		Integer i2 = 3;
		System.out.println(i1==i2);//====>true
		Integer i3 = 138;
		Integer i4 = 138;
		System.out.println(i3==i4);//====>false
		Integer i5 = new Integer(3);
		Integer i6 = new Integer(3);
		System.out.println(i5==i6);//====>false
		Integer a = new Integer(133);
		Integer b = new Integer(133);
		System.out.println(a==b);//====>false
		String i7 = "abc";
		String i8 = "abc";
		System.out.println(i7==i8);//====>true
		String i9 = new String("abc");
		String i10 = new String("abc");
		System.out.println(i9==i10);//====>false
	}
}

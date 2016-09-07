package com.pauu.javahence.jdk5;

public class ReflectionPoint {
	private int x;
	public int y;
	public String str1 = "ball";
	public String str2 = "basketball";
	public String str3 = "itcast";
	public ReflectionPoint(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	@Override
	public String toString() {
		return "ReflectionPoint [str1=" + str1 + ", str2=" + str2 + ", str3=" + str3 + "]";
	}
	
}

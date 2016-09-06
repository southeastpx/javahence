package com.pauu.javahence.jdk5;
/**
 * jdk5新特性，可变参数,增强的for循环
 * @author peng.xing
 *
 */
public class VariableParameter {
	public static void main(String[] args) {
		System.out.println(add(5,6));
		System.out.println(add(2,3,4));
	}
	public static int add(int x,int...args){
		int sum = x;
		/*for(int i=0;i<args.length;i++){
			sum+=args[i];
		}*/
		for(int arg : args){
			sum+=arg;
		}
		return sum;
	}
}

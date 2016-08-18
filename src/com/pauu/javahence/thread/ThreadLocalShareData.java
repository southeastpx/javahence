package com.pauu.javahence.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
/**
 * 线程范围内的共享变量（线程内共享，线程外独立）
 * @author peng.xing
 *
 */
public class ThreadLocalShareData {
	private static ThreadLocal<Integer> threadLocal = new ThreadLocal<Integer>();
	public static void main(String[] args) {
		for(int i=0;i<2;i++){
			new Thread(new Runnable() {
				
				@Override
				public void run() {
				    int data = new Random().nextInt();
					System.out.println(Thread.currentThread().getName()+" has put data:"+data);
					threadLocal.set(data);
					MyThreadShareData myData = MyThreadShareData.getThreadInstance();
					myData.setName("name"+data);
					myData.setAge(data);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					new A().get();
					new B().get();
					
				}
			}).start();
		}
	}
	
	static class A{
		public void get(){
			int data = threadLocal.get();
			MyThreadShareData myData = MyThreadShareData.getThreadInstance();
			System.out.println("A from "+Thread.currentThread().getName()+" get data:"+myData.getName()+" age:"+myData.getAge());
		}
	}
	
	static class B{
		public void get(){
			int data = threadLocal.get();
			MyThreadShareData myData = MyThreadShareData.getThreadInstance();
			System.out.println("B from "+Thread.currentThread().getName()+" get data:"+myData.getName()+" age:"+myData.getAge());
		}
	}
}

class MyThreadShareData{
	private String name;
	private int age;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	private static ThreadLocal<MyThreadShareData> myThreadLocal = new ThreadLocal<MyThreadShareData>();
	private MyThreadShareData(){}
	public static MyThreadShareData getThreadInstance(){
		MyThreadShareData instance = myThreadLocal.get();
		if(instance==null){
			instance = new MyThreadShareData();
			myThreadLocal.set(instance);
		}
		return instance;
	}
	
}

package com.pauu.javahence.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
	public static void main(String[] args) {
		new LockTest().init();
	}
	
	public void init(){
		final Outputer outputer = new Outputer();//内部类只能访问被final修饰的局部变量
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output("zhangxiaoxiang");
				}
			}
		}).start();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					outputer.output("lihuoming");
				}
			}
		}).start();
	}

	
	static class Outputer{
		Lock lock = new ReentrantLock();
		//使用lock实现与synchronized相同的同步效果
		public void output(String name){
			int len = name.length();
			lock.lock();
			try {
				for (int i = 0; i < len; i++) {
					System.out.print(name.charAt(i));
				} 
			} finally {
				lock.unlock();
			}
			System.out.println();
		}
		
	}
}


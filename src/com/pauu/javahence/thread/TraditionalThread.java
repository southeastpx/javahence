package com.pauu.javahence.thread;

public class TraditionalThread {
	public static void main(String[] args) {
		/*
		 * 继承Thread类方式
		 * ①定义类，继承Thread
		 * ②覆写Thread类的run()方法
		 * ③调用start方法
		 */
		Thread thread = new Thread(){
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("继承Thread:"+Thread.currentThread().getName());
				}
			}
		};
		thread.start();
		
		/*
		 * 实现Runnable接口
		 * ①定义类，实现Runnable接口
		 * ②覆写Runnable接口的run()方法
		 * ③将Runnable接口的子类对象作为参数传递给Thread类的构造函数
		 * ④调用Thread类的start()方法
		 * 
		 * 实现Runnable接口好处：
		 * ①避免了单线程的局限性
		 * ②可以对资源进行共享
		 */
		Thread thread2 = new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("实现Runnable接口:"+Thread.currentThread().getName());
				}
			}
		});
		thread2.start();
		
		/*
		 * 当Thread中覆写了run()方法时，不会调用Runnable接口中的run方法
		 */
		new Thread(new Runnable() {
			@Override
			public void run() {
				while(true){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("Runnable中的run():"+Thread.currentThread().getName());
				}
			}
		}){
			public void run() {
				while(true){
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("thread中的run():"+Thread.currentThread().getName());
				}
			}
		}.start();
	}
}

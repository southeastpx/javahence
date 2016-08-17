package com.pauu.javahence.thread;

public class TraditionalThreadSynchronized {
	public static void main(String[] args) {
		new TraditionalThreadSynchronized().init();
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
					outputer.output3("lihuoming");
				}
			}
		}).start();
	}

	
	static class Outputer{
		public void output(String name){
			int len = name.length();
			//同步代码块,同步代码块的锁一般写this就可以了
			synchronized (Outputer.class) {
				for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
				}
			}
			System.out.println();
		}
		
		//同步函数的锁是this
		public synchronized void output2(String name){
			int len = name.length();
			for(int i=0;i<len;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
		//静态同步函数的锁是类名.class
		public static synchronized void output3(String name){
			int len = name.length();
			for(int i=0;i<len;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
	}
}


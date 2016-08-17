package com.pauu.javahence.thread;

public class TraditionalThreadSynchronized {
	public static void main(String[] args) {
		new TraditionalThreadSynchronized().init();
	}
	
	public void init(){
		final Outputer outputer = new Outputer();//�ڲ���ֻ�ܷ��ʱ�final���εľֲ�����
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
			//ͬ�������,ͬ����������һ��дthis�Ϳ�����
			synchronized (Outputer.class) {
				for(int i=0;i<len;i++){
					System.out.print(name.charAt(i));
				}
			}
			System.out.println();
		}
		
		//ͬ������������this
		public synchronized void output2(String name){
			int len = name.length();
			for(int i=0;i<len;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
		
		//��̬ͬ����������������.class
		public static synchronized void output3(String name){
			int len = name.length();
			for(int i=0;i<len;i++){
				System.out.print(name.charAt(i));
			}
			System.out.println();
		}
	}
}


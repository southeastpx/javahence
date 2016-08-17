package com.pauu.javahence.thread;

public class TraditionalThread {
	public static void main(String[] args) {
		/*
		 * �̳�Thread�෽ʽ
		 * �ٶ����࣬�̳�Thread
		 * �ڸ�дThread���run()����
		 * �۵���start����
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
					System.out.println("�̳�Thread:"+Thread.currentThread().getName());
				}
			}
		};
		thread.start();
		
		/*
		 * ʵ��Runnable�ӿ�
		 * �ٶ����࣬ʵ��Runnable�ӿ�
		 * �ڸ�дRunnable�ӿڵ�run()����
		 * �۽�Runnable�ӿڵ����������Ϊ�������ݸ�Thread��Ĺ��캯��
		 * �ܵ���Thread���start()����
		 * 
		 * ʵ��Runnable�ӿںô���
		 * �ٱ����˵��̵߳ľ�����
		 * �ڿ��Զ���Դ���й���
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
					System.out.println("ʵ��Runnable�ӿ�:"+Thread.currentThread().getName());
				}
			}
		});
		thread2.start();
		
		/*
		 * ��Thread�и�д��run()����ʱ���������Runnable�ӿ��е�run����
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
					System.out.println("Runnable�е�run():"+Thread.currentThread().getName());
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
					System.out.println("thread�е�run():"+Thread.currentThread().getName());
				}
			}
		}.start();
	}
}

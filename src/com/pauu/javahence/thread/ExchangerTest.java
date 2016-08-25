package com.pauu.javahence.thread;

import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExchangerTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final Exchanger exchanger = new Exchanger();
		service.execute(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep((long) (Math.random()*10000));
					String data1 = "money";
					System.out.println("�߳�"+Thread.currentThread().getName()+"���ڰ�Ǯ����ȥ");
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("�߳�"+Thread.currentThread().getName()+"���صĶ���Ϊ"+data2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		service.execute(new Runnable() {
			@Override
			public void run() {
				try {
					Thread.sleep((long) (Math.random()*10000));
					String data1 = "apple";
					System.out.println("�߳�"+Thread.currentThread().getName()+"���ڰ�ƻ������ȥ");
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("�߳�"+Thread.currentThread().getName()+"���صĶ���Ϊ"+data2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}

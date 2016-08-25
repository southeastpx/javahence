package com.pauu.javahence.thread;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatchTest {
	public static void main(String[] args) {
		final CountDownLatch cdOrder = new CountDownLatch(1);
		final CountDownLatch cdAnswer = new CountDownLatch(3);
		ExecutorService service = Executors.newCachedThreadPool();
		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					try {
						System.out.println("�߳�"+Thread.currentThread().getName()+"��׼���������");
						cdOrder.await();
						System.out.println("�߳�"+Thread.currentThread().getName()+"�ѽ������");
						Thread.sleep((long) (Math.random()*10000));
						System.out.println("�߳�"+Thread.currentThread().getName()+"��Ӧ���������");
						cdAnswer.countDown();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			};
			service.execute(runnable);
		}
		try {
			Thread.sleep((long) (Math.random()*10000));
			System.out.println("main�̼߳����������");
			cdOrder.countDown();
			System.out.println("main�߳��ѷ�������,���ڵȴ������");
			cdAnswer.await();
			System.out.println("main�߳����յ�������Ӧ�����");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		service.shutdown();
	}
}

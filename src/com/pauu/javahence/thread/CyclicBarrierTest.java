package com.pauu.javahence.thread;

import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CyclicBarrierTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final CyclicBarrier cyclicBarrier = new CyclicBarrier(3);
		for(int i=0;i<3;i++){
			Runnable runnable = new Runnable() {
				
				@Override
				public void run() {
					try {
						Thread.sleep((long) (Math.random()*10000));
						System.out.println("�߳�"+Thread.currentThread().getName()+"�Ѿ����Ｏ�ϵأ���ǰ��"+(cyclicBarrier.getNumberWaiting()+1)+"���߳����ڵȴ���");
						cyclicBarrier.await();
						Thread.sleep((long) (Math.random()*10000));
						System.out.println("�߳�"+Thread.currentThread().getName()+"�Ѿ����Ｏ�ϵأ���ǰ��"+(cyclicBarrier.getNumberWaiting()+1)+"���߳����ڵȴ���");
						cyclicBarrier.await();
						Thread.sleep((long) (Math.random()*10000));
						System.out.println("�߳�"+Thread.currentThread().getName()+"�Ѿ����Ｏ�ϵأ���ǰ��"+(cyclicBarrier.getNumberWaiting()+1)+"���߳����ڵȴ���");
						cyclicBarrier.await();
					} catch (Exception e) {
						e.printStackTrace();
					}
					
				}
			};
			service.execute(runnable);
		}
		service.shutdown();
	}
}

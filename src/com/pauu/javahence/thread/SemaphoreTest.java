package com.pauu.javahence.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class SemaphoreTest {
	public static void main(String[] args) {
		ExecutorService service = Executors.newCachedThreadPool();
		final Semaphore semaphore = new Semaphore(3);
		for(int i=0;i<10;i++){
			Runnable runnable = new Runnable() {
				@Override
				public void run() {
					try {
						semaphore.acquire();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("�߳�"+Thread.currentThread().getName()+"���룬��ǰ����"+(3-semaphore.availablePermits())+"�������߳�");
					try {
						Thread.sleep((long) (Math.random()*10000));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println("�߳�"+Thread.currentThread().getName()+"�����뿪");
					semaphore.release();
					System.out.println("�߳�" + Thread.currentThread().getName() + 
							"���뿪����ǰ����" + (3-semaphore.availablePermits()) + "������");		
				}
			};
			service.execute(runnable);
		}
	}
}
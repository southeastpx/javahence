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
						System.out.println("线程"+Thread.currentThread().getName()+"正准备接受命令！");
						cdOrder.await();
						System.out.println("线程"+Thread.currentThread().getName()+"已接受命令！");
						Thread.sleep((long) (Math.random()*10000));
						System.out.println("线程"+Thread.currentThread().getName()+"回应命令处理结果！");
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
			System.out.println("main线程即将发布命令！");
			cdOrder.countDown();
			System.out.println("main线程已发布命令,正在等待结果！");
			cdAnswer.await();
			System.out.println("main线程已收到所有响应结果！");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		service.shutdown();
	}
}

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
						System.out.println("线程"+Thread.currentThread().getName()+"已经到达集合地，当前有"+(cyclicBarrier.getNumberWaiting()+1)+"个线程正在等待！");
						cyclicBarrier.await();
						Thread.sleep((long) (Math.random()*10000));
						System.out.println("线程"+Thread.currentThread().getName()+"已经到达集合地，当前有"+(cyclicBarrier.getNumberWaiting()+1)+"个线程正在等待！");
						cyclicBarrier.await();
						Thread.sleep((long) (Math.random()*10000));
						System.out.println("线程"+Thread.currentThread().getName()+"已经到达集合地，当前有"+(cyclicBarrier.getNumberWaiting()+1)+"个线程正在等待！");
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

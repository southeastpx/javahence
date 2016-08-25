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
					System.out.println("线程"+Thread.currentThread().getName()+"正在把钱换出去");
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("线程"+Thread.currentThread().getName()+"换回的东西为"+data2);
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
					System.out.println("线程"+Thread.currentThread().getName()+"正在把苹果换出去");
					String data2 = (String) exchanger.exchange(data1);
					System.out.println("线程"+Thread.currentThread().getName()+"换回的东西为"+data2);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
	}
}

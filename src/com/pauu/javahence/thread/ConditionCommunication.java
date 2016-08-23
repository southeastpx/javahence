package com.pauu.javahence.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionCommunication {
	final static Business business = new Business();
	public static void main(String[] args) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				for(int i=1;i<=50;i++){
					business.sub(i);
				}
			}
		}).start();
		
		for(int i=1;i<=50;i++){
			business.main(i);
		}
	}
	
	static class Business{
		private boolean bShouldSub = true;
		private Lock lock = new ReentrantLock();
		private Condition condition = lock.newCondition();
		public void sub(int i){
			lock.lock();
			try {
				while (!bShouldSub) {//使用while而不使用if是防止“虚假唤醒”
					try {
						condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("sub thread squence of " + j + " loop of " + i);
				}
				bShouldSub = false;
				condition.signal();
			} finally {
				lock.unlock();
			}
		}
		
		public void main(int i){
			lock.lock();
			try {
				while (bShouldSub) {
					try {
						condition.await();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				for (int j = 1; j <= 10; j++) {
					System.out.println("main thread squence of " + j + " loop of " + i);
				}
				bShouldSub = true;
				condition.signal();
			} finally {
				lock.unlock();
			}
		}
	}
}


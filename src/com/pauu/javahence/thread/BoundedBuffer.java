package com.pauu.javahence.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * 实现自己的缓冲区（阻塞队列）
 * @author peng.xing
 *
 */
public class BoundedBuffer {
	final Lock lock = new ReentrantLock();
	final Condition notFull = lock.newCondition();
	final Condition notEmpty = lock.newCondition();
	final Object[] items = new Object[100];
	int putptr = 0;
	int takeptr = 0;
	int count = 0;
	/*
	 * 向缓冲区中加数据
	 */
	public void put(Object x) throws InterruptedException{
		lock.lock();
		try {
			//如果缓冲区满了，就等待
			while(count==items.length){
				//不能放了
				notFull.await();
			}
			items[putptr] = x;
			if(++putptr==items.length){
				putptr = 0;
			}
			++count;
			//可以取了
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}
	/*
	 * 向缓冲区中取数据
	 */
	public Object take() throws InterruptedException{
		lock.lock();
		try {
			//如果缓冲区为空，就等待
			while(count==0){
				//不能取了
				notEmpty.await();
			}
			Object value = items[takeptr];
			if(++takeptr==items.length){
				takeptr = 0;
			}
			--count;
			//可以放了
			notFull.signal();
			return value;
		} finally {
			lock.unlock();
		}
	}
	
}

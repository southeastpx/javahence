package com.pauu.javahence.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
/**
 * ʵ���Լ��Ļ��������������У�
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
	 * �򻺳����м�����
	 */
	public void put(Object x) throws InterruptedException{
		lock.lock();
		try {
			//������������ˣ��͵ȴ�
			while(count==items.length){
				//���ܷ���
				notFull.await();
			}
			items[putptr] = x;
			if(++putptr==items.length){
				putptr = 0;
			}
			++count;
			//����ȡ��
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}
	/*
	 * �򻺳�����ȡ����
	 */
	public Object take() throws InterruptedException{
		lock.lock();
		try {
			//���������Ϊ�գ��͵ȴ�
			while(count==0){
				//����ȡ��
				notEmpty.await();
			}
			Object value = items[takeptr];
			if(++takeptr==items.length){
				takeptr = 0;
			}
			--count;
			//���Է���
			notFull.signal();
			return value;
		} finally {
			lock.unlock();
		}
	}
	
}

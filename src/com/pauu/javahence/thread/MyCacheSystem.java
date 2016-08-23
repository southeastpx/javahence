package com.pauu.javahence.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * �Լ���Ƶļ򵥵Ļ���ϵͳ
 * @author peng.xing
 *
 */
public class MyCacheSystem {
	private Map<String, Object> cache = new HashMap<String,Object>();
	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	public Object load(String key){
		readWriteLock.readLock().lock();
		Object value = null;
		try{
			value = cache.get(key);
			if(value==null){//�����в�����
				readWriteLock.readLock().unlock();
				readWriteLock.writeLock().lock();
				try{
					if(value==null){
						value = "aaaaa";//ʵ����Ŀ��Ӧ����queryDb(),ͨ��sql�����ݿ���ȡ��
					}
				}finally{
					readWriteLock.writeLock().unlock();
				}
				readWriteLock.readLock().lock();
			}
		}finally{
			readWriteLock.readLock().unlock();
		}
		
		return value;
	}
}

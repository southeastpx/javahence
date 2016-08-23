package com.pauu.javahence.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 自己设计的简单的缓存系统
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
			if(value==null){//缓存中不存在
				readWriteLock.readLock().unlock();
				readWriteLock.writeLock().lock();
				try{
					if(value==null){
						value = "aaaaa";//实际项目中应该是queryDb(),通过sql从数据库中取得
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

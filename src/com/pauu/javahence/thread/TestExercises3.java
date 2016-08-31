package com.pauu.javahence.thread;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestExercises3 extends Thread {
	private TestHandle testHandle;
	private String key;
	private String value;
	
	public TestExercises3(String key, String key2, String value) {
		this.testHandle = TestHandle.getInstance();
		this.key = key+key2;
		this.value = value;
	}

	@Override
	public void run() {
		testHandle.doSome(key, value);
	}
	
	public static void main(String[] args) {
		//String str1 = "1"+"";===>编译器自动优化成 String str1 = "1";
		//String str2 = "1"+"";===>编译器自动优化成 String str2 = "1";
		//System.out.println(str1==str2);==>true
		TestExercises3 a = new TestExercises3("1","", "1");
		TestExercises3 b = new TestExercises3("1","", "2");
		TestExercises3 c = new TestExercises3("3","", "3");
		TestExercises3 d = new TestExercises3("4","", "4");
		System.out.println("Begin:"+System.currentTimeMillis());
		a.start();
		b.start();
		c.start();
		d.start();
	}
}
class TestHandle{
	private TestHandle(){}
	private static TestHandle _instance = new TestHandle();
	public static TestHandle getInstance(){
		return _instance;
	}
	//private ArrayList keys = new ArrayList();===>会出现并发修改异常ConcurrentModificationException
	private CopyOnWriteArrayList keys = new CopyOnWriteArrayList<>();
	public void doSome(Object key,String value){
		Object lock = key;
		if(!keys.contains(lock)){
			keys.add(lock);
		}else{
			for(Iterator iter = keys.iterator();iter.hasNext();){
				Object oo = iter.next();
				if(oo.equals(lock)){
					lock = oo;
				}
			}
		}
		synchronized (lock) 
		{
			try {
				Thread.sleep(1000);
				System.out.println(key+":"+value+":"+System.currentTimeMillis()/1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

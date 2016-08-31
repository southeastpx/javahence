package com.pauu.javahence.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class TestExercises1 {
	public static void main(String[] args) {
		final BlockingQueue<String> queue = new ArrayBlockingQueue<String>(16);
		System.out.println("Begin:"+System.currentTimeMillis());
		for(int i=0;i<16;i++){
			final String log = ""+(i+1);
			try {
				queue.put(log);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i=0;i<4;i++){
			new	Thread(new Runnable() {
				@Override
				public void run() {
					while(true){
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						try {
							parseLog(queue.take());
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						
					}
				}

			}).start();
		}
	}
	public static void parseLog(String log){
		System.out.println(log+":"+System.currentTimeMillis());
	}
}

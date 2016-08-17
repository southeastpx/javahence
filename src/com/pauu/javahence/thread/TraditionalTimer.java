package com.pauu.javahence.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimer {
	private static int count = 0;
	public static void main(String[] args) {
		/*
		 * 10秒后爆炸，之后每隔3秒爆炸一次
		new Timer(){}.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("bombing！");
			}
		}, 10000,3000);
		*/
		/*
		 * 实现10秒后爆炸，之后2秒、4秒交替爆炸
		 */
		class MyTimerTask extends TimerTask{
			@Override
			public void run() {
				count = (count + 1)%2;
				System.out.println("bombing!");
				new Timer(){}.schedule(new MyTimerTask(), 2000+2000*count);
			}
		}
		new Timer(){}.schedule(new MyTimerTask(),2000);
		new Thread(){
			public void run() {
				while(true){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					System.out.println(new Date(){}.getSeconds());
				}
			}
		}.start();
	}
}

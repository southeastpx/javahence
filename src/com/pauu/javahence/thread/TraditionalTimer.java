package com.pauu.javahence.thread;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class TraditionalTimer {
	private static int count = 0;
	public static void main(String[] args) {
		/*
		 * 10���ը��֮��ÿ��3�뱬ըһ��
		new Timer(){}.schedule(new TimerTask() {
			@Override
			public void run() {
				System.out.println("bombing��");
			}
		}, 10000,3000);
		*/
		/*
		 * ʵ��10���ը��֮��2�롢4�뽻�汬ը
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

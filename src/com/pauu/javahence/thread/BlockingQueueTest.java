package com.pauu.javahence.thread;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueTest {
	public static void main(String[] args) {
		final BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(3);
		for(int i=0;i<2;i++){
			new Thread(){
				@Override
				public void run() {
					while(true){
						try {
							Thread.sleep((long) (Math.random()*10000));
							System.out.println(Thread.currentThread().getName()+"׼�������ݣ�");
							queue.put(1);
							System.out.println(Thread.currentThread().getName()+"�Ѿ���������,Ŀǰ��"+queue.size()+"�����ݣ�");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				}
			}.start();
		}
		new Thread(){
			public void run(){
				while(true){
					try {
						//���˴���˯��ʱ��ֱ��Ϊ100��1000���۲����н��
						Thread.sleep(10000);
						System.out.println(Thread.currentThread().getName() + "׼��ȡ����!");
						queue.take();
						System.out.println(Thread.currentThread().getName() + "�Ѿ�ȡ�����ݣ�" + 							
								"����Ŀǰ��" + queue.size() + "������");					
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
			
		}.start();			
	}
}

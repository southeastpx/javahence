package com.pauu.javahence.thread;

public class TraditionalThreadCommunication {
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
}
//wait()和notify()必须放在synchronized所在的代码块中
class Business{
	private boolean bShouldSub = true;
	public synchronized void sub(int i){
		while(!bShouldSub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int j=1;j<=10;j++){
			System.out.println("sub thread squence of "+j+" loop of "+i);
		}
		bShouldSub = false;
		this.notify();
	}
	
	public synchronized void main(int i){
		while(bShouldSub){
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int j=1;j<=10;j++){
			System.out.println("main thread squence of "+j+" loop of "+i);
		}
		bShouldSub = true;
		this.notify();
	}
}

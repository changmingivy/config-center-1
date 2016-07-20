package com.thread;

import java.util.concurrent.ThreadFactory;
//奴隶逃跑测试
public class HelotEscapeTest {
	private ThreadFactory factory = new HelotFactory();
	private Thread helotThread = null;
	
	public HelotEscapeTest(){
		Runnable task = new Runnable() {
			int stoneNum=1;
			public void run() {
				while(!Thread.interrupted()){
					System.out.println(helotThread.getName()+" 搬第"+stoneNum+"块石头..");
					stoneNum++;
					try{
						Thread.sleep(500);
					} catch(InterruptedException e){e.printStackTrace();}
					if(stoneNum>100){
						throw new RuntimeException("又饿又累没力气搬石头了");
					}
				}
			}
		};
		helotThread = factory.newThread(task);
	}
	//开始干活
	public void startWork(){
		helotThread.start();
	}
	public static void main(String[] args) {
		HelotEscapeTest test = new HelotEscapeTest();
		test.startWork();
	}
}

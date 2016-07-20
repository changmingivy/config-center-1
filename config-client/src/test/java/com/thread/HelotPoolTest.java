package com.thread;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class HelotPoolTest {
	
	public static ThreadPoolExecutor helotPool;
	private int helotNum= 5;  //奴隶数
	private Random random = new Random(100);
	
	private BlockingQueue<Runnable> queue = new LinkedBlockingQueue<Runnable>();
	
	public HelotPoolTest() {
		ThreadFactory factory = new HelotFactory();
		helotPool = new ThreadPoolExecutor( helotNum,helotNum,1000,TimeUnit.SECONDS,queue,factory);
	}
	//分配任务
	public void assignTask(){
		final int MAX=100;
		final int MIN=10;
		Runnable task = null;
		for(int j=0;j<20;j++){
			task = new Runnable() {
				int stoneNum = random.nextInt(MAX - MIN + 1) + MIN;// 生成10~100范围的随机数
				public void run() {
					for (int i = 1; i <= stoneNum; i++) {
						System.out.println(Thread.currentThread().getName() + " 搬完"+ i + "块石头");
						if (i == 10) {
							throw new RuntimeException("搬完第60块石头不干了");
						}
						try{
							Thread.sleep(100);//休息下
						}catch(InterruptedException e){e.printStackTrace();}
					}
				}
			};
			queue.add(task);
		}
	}
	//开始干活
	public void startWork(){
		helotPool.prestartAllCoreThreads();
	}
	public static void main(String[] args) {
		HelotPoolTest pool = new HelotPoolTest();

		MyMonitorThread monitor = new MyMonitorThread(HelotPoolTest.helotPool, 3);
		Thread monitorThread = new Thread(monitor);
		monitorThread.start();


		pool.assignTask();
		pool.startWork();

		pool.assignTask();
		pool.startWork();


	}
}

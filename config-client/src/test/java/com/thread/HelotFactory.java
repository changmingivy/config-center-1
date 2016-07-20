package com.thread;

import java.util.concurrent.ThreadFactory;
//奴隶制造工厂
public class HelotFactory implements ThreadFactory {
	private volatile int helotId=0;//奴隶编号
	//产生一个新奴隶
	public Thread newThread(Runnable r) {
		Thread helotThread = new Thread(r);
		helotThread.setName("helot-Thread-"+gethelotId());//设置奴隶姓名
		helotThread.setUncaughtExceptionHandler(new UEHLogger());//UEHLogger就是报警器
		return helotThread;
	}
	private int gethelotId(){
		return ++helotId;
	}
}

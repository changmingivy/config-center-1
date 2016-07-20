package com.thread;
 
import java.util.concurrent.ThreadPoolExecutor;
 
public class MyMonitorThread implements Runnable
{
    private ThreadPoolExecutor executor;
 
    private int seconds;
 
    private boolean run=true;
 
    public MyMonitorThread(ThreadPoolExecutor executor, int delay)
    {
        this.executor = executor;
        this.seconds=delay;
    }
 
    public void shutdown(){
        this.run=false;
    }
 
    public void run()
    {
        while(run){
                System.out.println(
                    String.format("[monitor] [%d/%d] Active: %d, Completed: %d, Task: %d, isShutdown: %s, isTerminated: %s",
                        this.executor.getPoolSize(),//线程池中保存着的线程数
                        this.executor.getCorePoolSize(),//线程池基本大小
                        this.executor.getActiveCount(),//当前活动的线程大小
                        this.executor.getCompletedTaskCount(),//已经完成的任务数量
                        this.executor.getTaskCount(),//所有任务总量
                        this.executor.isShutdown(),//是否已经关闭（不再接受新的任务）
                        this.executor.isTerminated()));//是否所所有任务完成 只要保存着
                try {
                    Thread.sleep(seconds*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
 
    }
}
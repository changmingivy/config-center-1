package com.thread;

import java.lang.Thread.UncaughtExceptionHandler;

//将泄露的线程信息输出到控制台
public class UEHLogger implements UncaughtExceptionHandler {
    public void uncaughtException(Thread t, Throwable e) {
        System.out.println(String.format("不好了，有奴隶逃跑了!奴隶姓名：%1$s,编号：%2$s,逃跑原因：%3$s", t.getName(), t.getId(), e.getMessage()));
        System.out.println("还剩" + HelotPoolTest.helotPool.getActiveCount() + "个奴隶");
    }
}

package com.marvinsworld.dconfig.spring.utils;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

@Deprecated
@Service
public class SpringContextUtils implements ApplicationContextAware {
  
    // Spring应用上下文环境  
    private static ApplicationContext applicationContext;
  
    /** 
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     */  
    public void setApplicationContext(ApplicationContext applicationContext) {  
        SpringContextUtils.applicationContext = applicationContext;
    }  
  
    /** 
     * @return ApplicationContext 
     */  
    public static ApplicationContext getApplicationContext() {  
        return applicationContext;  
    }  
  
    /** 
     * 根据bean的名称获取相应类型的对象
     */  
    public static <T> T getBean(String beanName) {
        return (T) applicationContext.getBean(beanName);
    }

    /**
     * 根据bean的类型获取相应类型的对象
     */
    public static <T> T getBean(Class<T> clazz){
        return applicationContext.getBean(clazz);
    }
}
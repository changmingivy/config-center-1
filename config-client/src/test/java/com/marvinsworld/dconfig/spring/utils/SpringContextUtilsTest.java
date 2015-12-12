package com.marvinsworld.dconfig.spring.utils;

import com.marvinsworld.dconfig.listener.ZkNodeListener;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Description:
 *
 * @author Marvinsworld
 * @since 2015/12/12 22:21
 */
@ContextConfiguration({"classpath:spring-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class SpringContextUtilsTest {


    @Test
    public void testGetBean(){
        ZkNodeListener zkNodeListener = SpringContextUtils.getBean(ZkNodeListener.class);
        System.out.println(zkNodeListener);
    }

    @Test
    public void testGetBean2(){
        ZkNodeListener zkNodeListener = SpringContextUtils.getBean("zkNodeListener");
        System.out.println(zkNodeListener);
    }
}

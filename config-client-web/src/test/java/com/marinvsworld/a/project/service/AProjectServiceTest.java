package com.marinvsworld.a.project.service;

import com.a.project.service.AProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Description:
 *
 * @author Marvinsworld
 * @since 2015/12/6 11:08
 */
@ContextConfiguration({"classpath:spring-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class AProjectServiceTest {

    @Resource
    private AProjectService aProjectService;

    @Test
    public void testbuildFields() throws InterruptedException {

        System.out.println("db1 = " + aProjectService.getDb1());
        System.out.println("db2 = " + aProjectService.getDb2());

        Thread.sleep(200000);
    }
}

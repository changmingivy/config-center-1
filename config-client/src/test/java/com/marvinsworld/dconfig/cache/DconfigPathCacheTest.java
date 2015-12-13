package com.marvinsworld.dconfig.cache;

import com.google.common.collect.Lists;
import com.marvinsworld.dconfig.spring.utils.SpringContextUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Description:
 *
 * @author Marvinsworld
 * @since 2015/12/13 14:07
 */
@ContextConfiguration({"classpath:spring-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
public class DconfigPathCacheTest {


    @Test
    public void testGetKey() throws ExecutionException {

        DconfigPathCache.dconfigCache.put("a.b.c", Lists.<Class>newArrayList(DconfigPathCacheTest.class, SpringContextUtils.class));


        DconfigPathCache.appendIfAbsent("a.b.c",DconfigPathCache.class);

        List<Class> clazz = DconfigPathCache.get("a.b.c");
        System.out.println(clazz);
    }
}

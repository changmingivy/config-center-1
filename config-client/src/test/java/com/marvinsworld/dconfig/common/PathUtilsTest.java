package com.marvinsworld.dconfig.common;

import junit.framework.Assert;
import org.junit.Test;

/**
 * Description:
 *
 * @author Marvinsworld
 * @since 2015/12/6 13:50
 */
public class PathUtilsTest {

    @Test
    public void testParseKey() {
        String key = "config.center.bproject.db3";
        String path = ZkUtils.parseKey(key);
        Assert.assertEquals("测试正常", "/config/center/bproject/db3", path);
    }
}

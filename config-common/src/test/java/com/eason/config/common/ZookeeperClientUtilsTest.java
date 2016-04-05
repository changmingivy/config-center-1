package com.eason.config.common;

import java.util.List;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2016/4/5 18:26
 */
public class ZookeeperClientUtilsTest {

    public static void main(String[] args) throws Exception {
        List<String> result = ZookeeperClientUtils.getAllNamespaces("10.12.2.124");
        System.out.println(result);
    }
}

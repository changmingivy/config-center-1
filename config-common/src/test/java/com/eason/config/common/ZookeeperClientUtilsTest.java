package com.eason.config.common;

import org.apache.curator.framework.CuratorFramework;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2016/4/5 18:26
 */
public class ZookeeperClientUtilsTest {

    public static void main(String[] args) throws Exception {
//        List<String> result = ZookeeperClientUtils.getAllNamespaces("10.12.2.124");
//        System.out.println(result);

        CuratorFramework client = ZookeeperClientUtils.createClient("10.12.2.124", "");

        ConfigNode node = ZookeeperClientUtils.getAllChildrenNodes(client,"/");

        System.out.println(node);
        //ZookeeperClientUtils.getAllChildrenNodes("10.12.2.124","")
    }
}

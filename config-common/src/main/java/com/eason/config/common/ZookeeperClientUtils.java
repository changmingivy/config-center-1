package com.eason.config.common;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.util.List;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2016/4/5 18:19
 */
public class ZookeeperClientUtils {

    public static CuratorFramework createClient(String zkAddress, String namespace) {
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(zkAddress).sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace(namespace)
                .build();

        client.start();
        return client;
    }

    /**
     * 获取所有的命名空间的名称集合
     *
     * @param zkAddress zookeeper地址
     */
    public static List<String> getAllNamespaces(String zkAddress) throws Exception {
        CuratorFramework client = createClient(zkAddress, "");
        return client.getChildren().forPath("/");
    }




}

package com.marvinsworld.dconfig.center;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Description:
 *
 * @author Marvinsworld
 * @since 2015/12/6 13:03
 */
public class RegisterCenter {

    public static CuratorFramework createClient(String zkAddress,String namespace) {
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(zkAddress).sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace(namespace)
                .build();

        client.start();
        return client;
    }

}

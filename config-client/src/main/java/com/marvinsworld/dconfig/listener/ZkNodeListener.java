package com.marvinsworld.dconfig.listener;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * Description:
 *
 * @author Marvinsworld
 * @since 2015/12/6 10:36
 */
public class ZkNodeListener {

    public static CuratorFramework createClient(String zkAddress, String namespace) {
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(zkAddress).sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                //.namespace(namespace)
                .build();

        client.start();
        return client;
    }

    public static void addListener(CuratorFramework client, String path) {
        final NodeCache cache = new NodeCache(client, path, false);

        cache.getListenable().addListener(new NodeCacheListener() {
            public void nodeChanged() throws Exception {
                System.out.printf("node change=" + new String(cache.getCurrentData().getData()));
            }
        });
    }
}

package com.eason.config.common;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.NodeCache;
import org.apache.curator.framework.recipes.cache.NodeCacheListener;
import org.apache.curator.framework.recipes.cache.TreeCache;
import org.apache.curator.framework.recipes.cache.TreeCacheEvent;
import org.apache.curator.framework.recipes.cache.TreeCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.ZKPaths;

/**
 * Description:
 *
 * @author Marvinsworld
 * @since 2015/12/6 10:36
 */
public class ZookeeperNodeUtils {

    public static CuratorFramework createClient(String zkAddress, String namespace) {
        CuratorFramework client = CuratorFrameworkFactory.builder().connectString(zkAddress).sessionTimeoutMs(5000)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .namespace(namespace)
                .build();

        client.start();
        return client;
    }

    /**
     * 监听某个节点
     */
    public static void addListener(CuratorFramework client, String path) throws Exception {
        final NodeCache cache = new NodeCache(client, path, false);
        cache.start(true);//启动NodeCache

        cache.getListenable().addListener(new NodeCacheListener() {
            public void nodeChanged() throws Exception {
                System.out.printf("node change=" + new String(cache.getCurrentData().getData()));
            }
        });
    }

    /**
     * 监控节点及子节点
     */
    public static void listenTreeCache(CuratorFramework client, String path) throws Exception {
        TreeCache treeCache = new TreeCache(client, path);
        treeCache.start();

        treeCache.getListenable().addListener(new TreeCacheListener() {
            public void childEvent(CuratorFramework curatorFramework, TreeCacheEvent event) throws Exception {
                switch (event.getType()) {
                    case NODE_ADDED: {
                        System.out.println("TreeNode added: " + ZKPaths.getNodeFromPath(event.getData().getPath()) + ", value: "
                                + new String(event.getData().getData()));
                        break;
                    }
                    case NODE_UPDATED: {
                        System.out.println("TreeNode changed: " + ZKPaths.getNodeFromPath(event.getData().getPath()) + ", value: "
                                + new String(event.getData().getData()));
                        break;
                    }
                    case NODE_REMOVED: {
                        System.out.println("TreeNode removed: " + ZKPaths.getNodeFromPath(event.getData().getPath()));
                        break;
                    }
                    default:
                        System.out.println("Other event: " + event.getType().name());
                }
            }
        });
    }

    public static void main(String[] args) throws Exception {
        CuratorFramework client = ZookeeperNodeUtils.createClient("10.12.2.124", "config-center");

        final NodeCache cache = new NodeCache(client, "/param", false);
        cache.start();

        cache.getListenable().addListener(new NodeCacheListener() {
            public void nodeChanged() throws Exception {
                System.out.printf("路径=" + cache.getCurrentData().getPath() + "node change=" + new String(cache.getCurrentData().getData()) + " ");
            }
        });

        Thread.sleep(15000);
    }
}

package com.eason.config.common;

import com.google.common.collect.Lists;
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

    public static String getValue(CuratorFramework client, String path) throws Exception {
        return new String(client.getData().forPath(path));
    }

    /**
     * 转换成节点
     *
     * @param fullPath zookeeper全路径
     */
    public static ConfigNode getAllChildrenNodes(CuratorFramework client, String fullPath) throws Exception {
        List<String> childrenPath = client.getChildren().forPath(fullPath);

        List<ConfigNode> children = Lists.newArrayList();
        for (String childPath : childrenPath) {
            children.add(getAllChildrenNodes(client, ZookeeperPathUtils.buildFullPath(fullPath, childPath)));
        }

        ConfigNode result = new ConfigNode();
        result.setText(ZookeeperPathUtils.splitLastPath(fullPath));
        result.setValue(getValue(client, fullPath));
        result.setNodes(children);

        return result;
    }

    public static ConfigNode getAllChildrenNodesFromRoot(CuratorFramework client) throws Exception {
        return getAllChildrenNodes(client, ZookeeperPathUtils.ROOT);
    }

}

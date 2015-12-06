package com.marvinsworld.dconfig.common;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.util.Assert;

/**
 * Description:
 *
 * @author Marvinsworld
 * @since 2015/12/6 13:44
 */
public class ZkUtils {
    public static final String ROOT = "/";

    /**
     * 将key转成path.
     * ex:"xxx.xxx.xxx.xxx"==>"/xxx/xxx/xxx/xxx"
     */
    public static String parseKey(String key) {
        Assert.hasText(key, "Param[key] is not null");
        return appendRoot(key.replaceAll("\\.", "/"));
    }

    /**
     * 头部增加根路径
     */
    public static String appendRoot(String path) {
        Assert.hasText(path, "Param[path] is not null");
        return path.startsWith(ROOT) ? path : (ROOT + path);
    }

    public static String getValue(CuratorFramework client, String path) throws Exception {
        return new String(client.getData().forPath(path));
    }
}

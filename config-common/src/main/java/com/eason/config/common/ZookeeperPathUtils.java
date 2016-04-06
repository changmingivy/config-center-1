package com.eason.config.common;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 * Description:
 *
 * @author Marvinsworld
 * @since 2015/12/6 13:44
 */
public class ZookeeperPathUtils {
    public static final String ROOT = "/";
    public static final String SEPERATE = "/";

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

    /**
     * 组装完整路径
     * Note:如果是根(/),则不拼接分隔符
     */
    public static String buildFullPath(String parentFullPath, String childPath) {
        Assert.notNull(parentFullPath, "Param[parentFullPath] is not null");
        Assert.isTrue(parentFullPath.startsWith(ROOT), "Param[parentFullPath] must begin with '/'");
        return parentFullPath + (StringUtils.equals(parentFullPath, ROOT) ? "" : SEPERATE) + childPath;
    }
}

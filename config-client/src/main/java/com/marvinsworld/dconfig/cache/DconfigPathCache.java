package com.marvinsworld.dconfig.cache;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import com.google.common.collect.Lists;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * Description:用于缓存zookeeper路径和带有Dconfig注解的类
 *
 * @author Marvinsworld
 * @since 2015/12/13 10:48
 */
@Deprecated
public class DconfigPathCache {

    public static LoadingCache<String, List<Class>> dconfigCache = CacheBuilder.newBuilder().maximumSize(1000).build(
            new CacheLoader<String, List<Class>>() {
                @Override
                public List<Class> load(String key) throws Exception {
                    return Lists.newArrayList();
                }
            }
    );

    /**
     * 如果不存在就追加到最后
     */
    public static void appendIfAbsent(String path, Class clazz) throws ExecutionException {
        List<Class> value = get(path);
        if (value == null) {
            dconfigCache.put(path, Lists.newArrayList(clazz));
        } else {
            value.add(clazz);
        }
    }

    /**
     * 根据key获取
     */
    public static List<Class> get(String path) throws ExecutionException {
        return dconfigCache.get(path);
    }


}

package com.marvinsworld.dconfig.spring.annotation;

import com.eason.config.common.ZookeeperClientUtils;
import com.eason.config.common.ZookeeperNodeUtils;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.marvinsworld.dconfig.cache.DconfigPathCache;
import com.marvinsworld.dconfig.common.ZkUtils;
import com.marvinsworld.dconfig.listener.ZkNodeListener;
import org.apache.curator.framework.CuratorFramework;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.util.ReflectionUtils;

import java.io.InputStream;
import java.lang.reflect.Field;
import java.util.Properties;
import java.util.concurrent.ExecutionException;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2015/8/23 15:57
 */
public class DConfigAnnotationProcessor extends ApplicationObjectSupport implements BeanPostProcessor {
    private static final Logger LOGGER = LoggerFactory.getLogger(DConfigAnnotationProcessor.class);

    private String[] locations;
    private String namespace;
    private String registry;
    private long timeout;
    private boolean ignoreResourceNotFound;
    private Properties properties;

    private CuratorFramework client;

    public DConfigAnnotationProcessor(String[] locations, String namespace, String registry) {
        Preconditions.checkArgument((locations != null) && (locations.length > 0), "DConfig locations property must be not null!");
        this.locations = locations;
        this.namespace = namespace;
        this.registry = registry;

        properties = new Properties();
        for (String file : locations) {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(file);
            LOGGER.debug("DConfig loading file {}...", file);
            try {
                properties.load(inputStream);
            } catch (Exception e) {
                LOGGER.error("DConfig loading file error,please check the path of file {}!", file, e);
            }
        }

        client = ZookeeperClientUtils.createClient(registry, namespace);
        try {
            ZkNodeListener.listenTreeCache(client, "/");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        parseFields(bean, bean.getClass().getDeclaredFields());

//        ZookeeperNodeUtils zkNodeListener = SpringContextUtils.getBean(ZookeeperNodeUtils.class);
//        System.out.println(zkNodeListener);
        return bean;
    }

    /**
     * 解析字段
     */
    private void parseFields(Object bean, Field[] fields) {
        for (Field field : fields) {
            DConfig annotation = AnnotationUtils.getAnnotation(field, DConfig.class);
            if (annotation != null) {
                String key = annotation.value();
                String value = null;

                try {
                    DconfigPathCache.appendIfAbsent(key, bean.getClass());
                } catch (ExecutionException e) {
                    LOGGER.error("Dconfig add the key {} to cache error!", key, e);
                }

                //从远端获取配置项
                try {
                    value = ZookeeperClientUtils.getValue(client, ZkUtils.parseKey(key));
                } catch (Exception e) {
                    LOGGER.error("Dconfig server communicate error,please check the key {} exits!", key, e);
                }

                //设置属性
                ReflectionUtils.makeAccessible(field);
                try {
                    if (!Strings.isNullOrEmpty(value)) {
                        field.set(bean, value);
                    }
                } catch (Exception e) {
                    LOGGER.error("DConfig parse property key error,please check the key {}!", key, e);
                }

                try {
                    ZookeeperNodeUtils.addListener(client, ZkUtils.parseKey(key));
                } catch (Exception e) {
                    LOGGER.error("测试测试监听", key, e);
                }


                //从配置文件读取属性
//                if (Strings.isNullOrEmpty(key)) {
//                    LOGGER.error("DConfig annotation key must have a name!");
//                } else {
//                    ReflectionUtils.makeAccessible(field);
//                    String value = properties.getProperty(key);
//                    try {
//                        if (!Strings.isNullOrEmpty(value)) {
//                            field.set(bean, value);
//                        }
//                    } catch (Exception e) {
//                        LOGGER.error("DConfig parse property key error,please check the key {}!", key, e);
//                    }
//                }
            }
        }
    }

    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    public String getNamespace() {
        return namespace;
    }

    public void setNamespace(String namespace) {
        this.namespace = namespace;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public void setIgnoreResourceNotFound(boolean ignoreResourceNotFound) {
        this.ignoreResourceNotFound = ignoreResourceNotFound;
    }

    public void setLocations(String[] locations) {
        this.locations = locations;
    }

    public long getTimeout() {
        return timeout;
    }

    public boolean isIgnoreResourceNotFound() {
        return ignoreResourceNotFound;
    }

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }
}

package com.eason.config.common;

import java.io.Serializable;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2016/4/14 14:42
 */
public class BaseConfigNode implements Serializable {

    private static final long serialVersionUID = -8630380300966125149L;

    /**
     * 全路径
     */
    private String fullPath;

    /**
     * 存储的内容
     */
    private String value;

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

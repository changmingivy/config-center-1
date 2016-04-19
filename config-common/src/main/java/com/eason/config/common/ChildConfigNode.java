package com.eason.config.common;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2016/4/19 11:15
 */
public class ChildConfigNode extends BaseConfigNode {
    private static final long serialVersionUID = -5136184733669503002L;

    /**
     * 父节点全路径
     */
    private String parentFullPath;

    public String getParentFullPath() {
        return parentFullPath;
    }

    public void setParentFullPath(String parentFullPath) {
        this.parentFullPath = parentFullPath;
    }
}

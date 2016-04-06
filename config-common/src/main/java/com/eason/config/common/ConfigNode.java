package com.eason.config.common;

import java.io.Serializable;
import java.util.List;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2016/4/6 15:30
 */
public class ConfigNode implements Serializable {
    private static final long serialVersionUID = 8417127217489223901L;

    private String id;

    /**
     * 路径,简化
     */
    private String text;

    /**
     * 全路径
     */
    private String fullPath;

    /**
     * 存储的内容
     */
    private String value;

    /**
     * 用于存储直接子节点数量
     */
    private String tags;


    private List<ConfigNode> nodes;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public List<ConfigNode> getNodes() {
        return nodes;
    }

    public void setNodes(List<ConfigNode> nodes) {
        this.nodes = nodes;
    }
}

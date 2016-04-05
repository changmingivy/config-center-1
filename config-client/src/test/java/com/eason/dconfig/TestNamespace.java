package com.eason.dconfig;

import com.marvinsworld.dconfig.listener.ZkNodeListener;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.api.GetChildrenBuilder;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2016/4/5 17:31
 */
public class TestNamespace {

    public static void main(String[] args) {
        CuratorFramework client = ZkNodeListener.createClient("10.12.2.124", "");

        GetChildrenBuilder builder = client.getChildren();
        System.out.println(builder);
    }
}

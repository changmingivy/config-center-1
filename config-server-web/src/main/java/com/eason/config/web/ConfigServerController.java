package com.eason.config.web;

import com.eason.config.common.BaseConfigNode;
import com.eason.config.common.ChildConfigNode;
import com.eason.config.common.ConfigNode;
import com.eason.config.common.ZookeeperClientUtils;
import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.data.Stat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2016/4/5 17:12
 */
@Controller
@RequestMapping(value = "/config-center", method = {RequestMethod.GET, RequestMethod.POST})
public class ConfigServerController {

    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    private String ip = "10.12.4.50";

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String init(HttpServletRequest request, Model model) {
        return "/config-center/list";
    }

    @RequestMapping(value = "/view", method = {RequestMethod.GET, RequestMethod.POST})
    public String view(HttpServletRequest request, Model model) {
        return "/config-center/view";
    }

    @RequestMapping(value = "/data", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<ConfigNode> data(HttpServletRequest request, Model model) {
        CuratorFramework client = ZookeeperClientUtils.createClient(ip, "");

        try {
            ConfigNode result = ZookeeperClientUtils.getAllChildrenNodesFromRoot(client);

            List<ConfigNode> list = Lists.newArrayList();
            list.add(result);
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @RequestMapping(value = "/detail", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public BaseConfigNode detail(HttpServletRequest request, String fullPath) {
        BaseConfigNode result = new BaseConfigNode();

        CuratorFramework client = ZookeeperClientUtils.createClient(ip, "");
        try {
            result.setValue(ZookeeperClientUtils.getValue(client, fullPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.setFullPath(fullPath);
        return result;
    }

    @RequestMapping(value = "/edit", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public BaseConfigNode edit(HttpServletRequest request, ChildConfigNode configNode) {
        ChildConfigNode result = new ChildConfigNode();

//        CuratorFramework client = ZookeeperClientUtils.createClient(ip, "");
//        try {
//            result.setValue(ZookeeperClientUtils.getValue(client, fullPath));
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//
//        result.setFullPath(fullPath);
        return result;
    }

    @RequestMapping(value = "/add", method = {RequestMethod.POST})
    @ResponseBody
    public BaseConfigNode add(HttpServletRequest request, BaseConfigNode configNode) {
        BaseConfigNode result = new BaseConfigNode();

        CuratorFramework client = ZookeeperClientUtils.createClient(ip, "");
        try {
            client.create().forPath(configNode.getFullPath(), configNode.getValue().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.setFullPath("111");

        return result;
    }

    @RequestMapping(value = "/update", method = {RequestMethod.POST})
    @ResponseBody
    public BaseConfigNode update(HttpServletRequest request, BaseConfigNode configNode) {
        BaseConfigNode result = new BaseConfigNode();

        CuratorFramework client = ZookeeperClientUtils.createClient(ip, "");
        try {
            Stat stat = client.setData().forPath(configNode.getFullPath(), configNode.getValue().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.setFullPath("111");

        return result;
    }
}

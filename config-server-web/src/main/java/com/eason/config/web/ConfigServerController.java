package com.eason.config.web;

import com.eason.config.common.BaseConfigNode;
import com.eason.config.common.ConfigNode;
import com.eason.config.common.ZookeeperClientUtils;
import com.google.common.collect.Lists;
import org.apache.curator.framework.CuratorFramework;
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

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String init(HttpServletRequest request, Model model) {
        return "/config-center/list";
    }

    @RequestMapping(value = "/data", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public List<ConfigNode> data(HttpServletRequest request, Model model) {
        CuratorFramework client = ZookeeperClientUtils.createClient("10.12.2.181", "");

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

        CuratorFramework client = ZookeeperClientUtils.createClient("10.12.2.181", "");
        try {
            result.setValue(ZookeeperClientUtils.getValue(client, fullPath));
        } catch (Exception e) {
            e.printStackTrace();
        }

        result.setFullPath(fullPath);

//        try {
//            Stat stat = client.checkExists().forPath(fullPath);
//            System.out.println(stat);
//            return stat;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        return result;
    }
}

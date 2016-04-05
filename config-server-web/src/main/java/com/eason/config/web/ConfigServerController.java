package com.eason.config.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

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

    @RequestMapping(value = "/list", method = {RequestMethod.GET, RequestMethod.POST})
    public String list(HttpServletRequest request, Model model) {
        return "/config-center/list";
    }

    @RequestMapping(value = "/data", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public Object data(HttpServletRequest request, Model model) {


        return "/config-center/list";
    }
}

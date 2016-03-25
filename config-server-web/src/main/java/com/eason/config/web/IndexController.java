package com.eason.config.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2016/3/16 19:35
 */
@Controller
@RequestMapping(method = {RequestMethod.GET,RequestMethod.POST})
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @RequestMapping(value="/", method = {RequestMethod.GET,RequestMethod.POST})
    public String index(HttpServletRequest request, Model model){
        LOGGER.info("1111");
        return "index";
    }
}

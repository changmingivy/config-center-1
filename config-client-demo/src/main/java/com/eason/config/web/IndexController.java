package com.eason.config.web;

import com.a.project.service.AProjectService;
import com.b.project.service.BProjectService;
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
@RequestMapping(value = "/", method = {RequestMethod.GET, RequestMethod.POST})
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    private AProjectService aProjectService;

    private BProjectService bProjectService;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String index(HttpServletRequest request, Model model) {
        LOGGER.info("1111");
        return "index";
    }

    @RequestMapping(value = "data", method = {RequestMethod.GET, RequestMethod.POST})
    public String data(HttpServletRequest request, Model model) {
        String db1 = aProjectService.getDb1();
        String db2 = aProjectService.getDb2();

        String db3 = bProjectService.getDb3();
        String db4 = bProjectService.getDb4();
        return db1 + "--" + db2 + "--" + db3 + "--" + db4;
    }


}

package com.eason.config.web;

import com.a.project.service.AProjectService;
import com.b.project.service.BProjectService;
import com.eason.vo.DbVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2016/3/16 19:35
 */
@Controller
@RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
public class IndexController {
    private static final Logger LOGGER = LoggerFactory.getLogger(IndexController.class);

    @Resource
    private AProjectService aProjectService;

    @Resource
    private BProjectService bProjectService;

    @RequestMapping(method = {RequestMethod.GET, RequestMethod.POST})
    public String index(HttpServletRequest request, Model model) {
        LOGGER.info("1111");
        return "index";
    }

    @RequestMapping(value = "/data", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public DbVO data(HttpServletRequest request, Model model) {
        LOGGER.info("2222");
        String db1 = aProjectService.getDb1();
        String db2 = aProjectService.getDb2();

        String db3 = bProjectService.getDb3();
        String db4 = bProjectService.getDb4();

        DbVO vo = new DbVO();
        vo.setDb1(db1);
        vo.setDb2(db2);
        vo.setDb3(db3);
        vo.setDb4(db4);

        return vo;
    }


}

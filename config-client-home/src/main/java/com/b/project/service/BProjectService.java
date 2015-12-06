package com.b.project.service;

import com.marvinsworld.dconfig.spring.annotation.DConfig;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author Marvinsworld
 * @since 2015/12/6 10:29
 */
@Service
public class BProjectService {

    @DConfig("config.center.bproject.db3")
    private String db3;

    @DConfig("config.center.bproject.db4")
    private String db4;
}

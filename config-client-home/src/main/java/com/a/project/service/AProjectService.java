package com.a.project.service;

import com.marvinsworld.dconfig.spring.annotation.DConfig;
import org.springframework.stereotype.Service;

/**
 * Description:
 *
 * @author Marvinsworld
 * @since 2015/12/6 10:28
 */
@Service
public class AProjectService {

    @DConfig("config.center.aproject.db1")
    private String db1;

    @DConfig("config.center.aproject.db2")
    private String db2;

    
}

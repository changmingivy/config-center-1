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

    public String getDb3() {
        return db3;
    }

    public void setDb3(String db3) {
        this.db3 = db3;
    }

    public String getDb4() {
        return db4;
    }

    public void setDb4(String db4) {
        this.db4 = db4;
    }
}

package com.eason.vo;

import java.io.Serializable;

/**
 * Description:.
 *
 * @author zhiqiang.ge
 * @version V1.0
 * @since 2016/4/18 17:44
 */
public class DbVO implements Serializable {

    private static final long serialVersionUID = -4858291100257046858L;
    private String db1;
    private String db2;
    private String db3;
    private String db4;

    public String getDb1() {
        return db1;
    }

    public void setDb1(String db1) {
        this.db1 = db1;
    }

    public String getDb2() {
        return db2;
    }

    public void setDb2(String db2) {
        this.db2 = db2;
    }

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

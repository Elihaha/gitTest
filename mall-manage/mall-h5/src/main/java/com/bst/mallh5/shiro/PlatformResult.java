package com.bst.mallh5.shiro;

import com.bst.mallh5.user.PlatformUser;

public class PlatformResult {

    private PlatformUser data;

    private Integer err_code;

    private String err_desc;

    public PlatformUser getData() {
        return data;
    }

    public void setData(PlatformUser data) {
        this.data = data;
    }

    public Integer getErr_code() {
        return err_code;
    }

    public void setErr_code(Integer err_code) {
        this.err_code = err_code;
    }

    public String getErr_desc() {
        return err_desc;
    }

    public void setErr_desc(String err_desc) {
        this.err_desc = err_desc;
    }
}
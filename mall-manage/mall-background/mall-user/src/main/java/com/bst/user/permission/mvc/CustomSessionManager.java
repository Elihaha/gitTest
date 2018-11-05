package com.bst.user.permission.mvc;

/**
 * @author gan
 * @desc:  前后端分离,在ajax的请求头中传递sessionId，重写shiro获取sessionId的方式
 */

import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

/**
 * Created by Administrator on 2017/12/11.
 * 自定义sessionId获取
 */
public class CustomSessionManager extends DefaultWebSessionManager {

    private static final String AUTHORIZATION = "Authorization";

    private static final String REFERENCED_SESSION_ID_SOURCE = "Stateless request";

    public CustomSessionManager() {
        super();
    }

    @Override
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //从cookie取sessionId
        return super.getSessionId(request, response);
    }
}

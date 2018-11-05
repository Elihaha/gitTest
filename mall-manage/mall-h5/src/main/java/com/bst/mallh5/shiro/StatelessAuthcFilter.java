package com.bst.mallh5.shiro;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.web.filter.authc.BasicHttpAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class StatelessAuthcFilter extends BasicHttpAuthenticationFilter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        logger.info("拦截到的url:" + httpRequest.getRequestURL().toString());
        // 前段token授权信息放在请求头中传入
        String ticket = httpRequest.getHeader("Authorization");
        if (StringUtils.isEmpty(ticket)) {
            try {
                response401(httpResponse, "请求头不包含票据，ticket");
            } catch (IOException e) {
                e.printStackTrace();
            }
            return false;
        }
        // 获取无状态Token
        StatelessToken accessToken = new StatelessToken(ticket);
        try {
            // 委托给Realm进行登录
            getSubject(request, response).login(accessToken);
        } catch (Exception e) {
            logger.error("auth error:" + e.getMessage(), e);
            try {
                response401(httpResponse, "auth error:" + e.getMessage()); // 6、登录失败
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            return false;
        }
        // 通过isPermitted 才能调用doGetAuthorizationInfo方法获取权限信息
        getSubject(request, response).isPermitted(httpRequest.getRequestURI());
        return true;
    }

    @Override
    protected boolean onAccessDenied(ServletRequest req, ServletResponse resp) throws Exception {
       /* HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        String ticket = request.getHeader("Authorization");
        if (StringUtils.isEmpty(ticket)) {
            response401(response, "验证失败！");
            return false;
        }
        try {
            return tokenManager.isUserExists(ticket);
        } catch (MallAuthorizationException ex) {
            response401(response, ex.getMessage());
            return false;
        } catch (Exception ex) {
            logger.error(">>>>>>>>>>>>>>>>>>>>获取用户信息失败：",ex);
            response401(response, "验证失败！");
            return false;
        }*/
        return false;
    }

    private void response401(HttpServletResponse response, String msg) throws IOException {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(msg);
        response.getWriter().close();
    }
}

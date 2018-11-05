package com.bst.mallh5.shiro;

import com.bst.mallh5.user.PlatformUser;
import com.bst.mallh5.user.UserService;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 认证
 *
 * @author kapok
 * 2017-12-19 11:43
 */
@Component
public class StatelessRealm extends AuthorizingRealm {

    @Autowired
    private UserService userService;

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof StatelessToken;
    }

    /**
     * 授权（验证权限时调用）
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return new SimpleAuthorizationInfo();
    }

    /**
     * 认证 （登录时调用）
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) {
        StatelessToken statelessToken = (StatelessToken)token;

        String ticket = (String)statelessToken.getPrincipal();

        PlatformUser user = userService.getPlatformUser(ticket);
        if (user != null) {
            String credentials = (String)statelessToken.getCredentials();
            return new SimpleAuthenticationInfo(user, credentials, super.getName());
        }else{
            throw new AuthenticationException("token认证失败");
        }
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}

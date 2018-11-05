package com.bst.user.permission.login.controller;

import com.bst.backcommon.exception.MallException;
import com.bst.backcommon.permission.PermissionInfoUtil;
import com.bst.backcommon.permission.entity.Operator;
import com.bst.backcommon.permission.entity.OperatorExample;
import com.bst.common.modle.Result;
import com.bst.user.permission.login.entity.UserAccount;
import com.bst.user.permission.mvc.entity.SysMenu;
import com.bst.user.permission.mvc.mapper.OperatorMapper;
import com.bst.user.permission.mvc.mapper.SysMenuMapper;
import com.bst.user.permission.mvc.mapper.SysRoleMapper;
import com.bst.user.permission.utils.AccountValidatorUtil;
import com.bst.user.permission.utils.JwtUtils;
import com.bst.user.permission.utils.Md5Utils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.bst.user.permission.utils.StatusCodeConstants.USERNAME_OR_PASSWORD_ERROR_CODE;
import static com.bst.user.permission.utils.StatusCodeConstants.USER_NOT_LOGGIN_CODE;

/**
 * @author gan
 * @desc:  单点登陆接口
 */
@RestController
public class LoginController {
    @Autowired
    private OperatorMapper userMapper;

    @Autowired
    private SysRoleMapper roleMapper;

    @Autowired
    private SysMenuMapper permissionMapper;

    private final Logger logger = LoggerFactory.getLogger(LoginController.class);

    /**
     * @desc: 单点登陆
     * @param userAccount
     * @return:
     */
    @RequestMapping(value = "/login", method= RequestMethod.POST)
    public Result login(@RequestBody UserAccount userAccount, HttpServletRequest request) {
        String username = userAccount.getUsername();
        String password = userAccount.getPassword();
        Operator user;
        Subject subject = SecurityUtils.getSubject();
        //根据username获取用户的加密钥匙
        try {
            user = getUserObject(username);
        } catch (MallException e) {
            return  Result.error(USERNAME_OR_PASSWORD_ERROR_CODE, "账号不存在");
        }

        try {
            UsernamePasswordToken usernamePasswordToken =
                    new UsernamePasswordToken(user.getName(), Md5Utils.generate(password,user.getSalt()));
            subject.login(usernamePasswordToken);
        } catch (IncorrectCredentialsException e) {
            return  Result.error(USERNAME_OR_PASSWORD_ERROR_CODE, "用户或密码错误!");
        }
        //使用jwt加密sessionId作为token给前端存储
        String sessionId = subject.getSession().getId().toString();
        subject.getSession().setTimeout(1800000);
        String token = JwtUtils.createToken(sessionId);
        return Result.ok(token);
    }

    /**
     * 未登录，shiro应重定向到登录界面，此处返回未登录状态信息由前端控制跳转页面
     * @return
     */
    @CrossOrigin(maxAge = 3600)
    @RequestMapping(value = "/unauth", method= RequestMethod.GET)
    public Result unauth() {
        return Result.ok(USER_NOT_LOGGIN_CODE,"当前用户未登陆");
    }

    private Operator getUserObject(String username) throws MallException {
        OperatorExample operatorExample = new OperatorExample();
        OperatorExample.Criteria criteria = operatorExample.createCriteria();
        if (AccountValidatorUtil.isMobile(username)) {
            criteria.andPhoneEqualTo(username);
        } else if (AccountValidatorUtil.isEmail(username)) {
            criteria.andEmailEqualTo(username);
        } else {
            criteria.andNameEqualTo(username);
        }
        List<Operator> operators = userMapper.selectByExample(operatorExample);
        if (!operators.isEmpty()) {
            return operators.get(0);
        } else {
            throw new MallException("账号不存在");
        }
    }

    /**
     * @desc: 获取用户信息
     * @return:
     */
    @RequestMapping(value = "/user/info", method= RequestMethod.POST)
    public Result userInfo() {
        Subject subject = SecurityUtils.getSubject();
         Operator user = (Operator) subject.getPrincipal();
        Integer parentId = user.getParentId();
        List<String> roles = new ArrayList<>();
        List<SysMenu> permissions;
        if (parentId == 0) {
            roles = roleMapper.findRolesNameByUserId(user.getId());
            permissions = permissionMapper.findByUserId(user.getId());
        } else {
            permissions = permissionMapper.findByChildUserId(user.getId());
        }
        Map<String, Object> userDescribe = new HashMap<>(3);
        userDescribe.put("name", user.getName());
        userDescribe.put("roles",roles);
        userDescribe.put("dynamicMenu", permissions);
        return Result.ok(userDescribe);
    }

    /**
     * @desc:
     * @param
     * @return:
     */
    @RequestMapping(value = "logout", method = RequestMethod.GET)
    public Result logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout(); // session 会销毁，在SessionListener监听session销毁，清理权限缓存
        }
        return Result.ok();
    }

    /**
     * @desc: 修改密码
     * @param
     * @return:
     */
    @PutMapping(value = "modify_pwd")
//    @ApiOperation(value="修改用户密码")
    public Result modifyPassword(@RequestBody UserAccount  param) {
        Operator operator = PermissionInfoUtil.getCurrentLogginUser();
        String newPassword = param.getPassword();
        //该用户密码加密数
        String salt = operator.getSalt();
        boolean verify = Md5Utils.verify(param.getPassword(), operator.getPassword(), salt);
        if (!verify) {
            return Result.error("旧密码错误, 请重新输入");
        }
        //进行加密处理
        String new_salt = Md5Utils.getSalt();
        String encrypted = Md5Utils.generate(newPassword, new_salt);
        operator.setPassword(encrypted);
        operator.setSalt(new_salt);
        userMapper.updateByPrimaryKey(operator);
        return Result.ok();
    }
}

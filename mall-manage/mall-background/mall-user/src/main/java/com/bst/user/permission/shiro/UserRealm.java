package com.bst.user.permission.shiro;


import com.bst.backcommon.permission.entity.Operator;
import com.bst.backcommon.permission.entity.OperatorExample;
import com.bst.common.entity.shop.ShopInfo;
import com.bst.common.mapper.shop.ShopInfoMapper;
import com.bst.user.permission.mvc.entity.SysMenu;
import com.bst.user.permission.mvc.mapper.OperatorMapper;
import com.bst.user.permission.mvc.mapper.SysMenuMapper;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * 认证
 * 
 */
@Component
public class UserRealm extends AuthorizingRealm {
	@Autowired
	private OperatorMapper operatorMapper;
	@Autowired
	private SysMenuMapper sysMenuMapper;
	@Autowired
	private ShopInfoMapper shopInfoMapper;

	/**
     * 授权(验证权限时调用)
     */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		Operator user = (Operator)principals.getPrimaryPrincipal();
		Integer userId = user.getId();
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		//获取用户拥有的权限
		List<SysMenu> permissions = sysMenuMapper.findByUserId(userId);
		for (SysMenu permission:permissions) {
			String perms = permission.getPerms();
			if (!StringUtils.isEmpty(perms)) {
				info.addStringPermission(permission.getPerms());
			}
		}
		return info;
	}

	/**
	 * 认证(登录时调用)
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordToken token = (UsernamePasswordToken)authcToken;
		String username = token.getUsername();
		Operator user = getUserObject(username);
		if (user.getStatue() != 2) {
			throw new LockedAccountException("删号已失效");
		}
		SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(user,user.getPassword(),this.getName());
        return info;
	}

	/**
	 * @desc: 获取用户对象
	 * @param username
	 * @return:
	 */
	private Operator getUserObject(String username) {
		//查询用户信息
		OperatorExample example = new OperatorExample();
		OperatorExample.Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(username);
		List<Operator> users = operatorMapper.selectByExample(example);
		Operator operator = users.get(0);
		ShopInfo shopInfo = shopInfoMapper.selectShopInfoByPermissionId(operator.getId());
		if (shopInfo == null) {
			shopInfo = new ShopInfo();
			shopInfo.setPermissionId(operator.getId());
			shopInfo.setShopName(operator.getName());
			shopInfo.setAccount(operator.getName());
			shopInfo.setShopStatus((byte) 1);
			shopInfo.setOpenTime(new Date());
			shopInfo.setOperator("系统自动创建");// 系统创建
			shopInfoMapper.insertSelective(shopInfo);
		}
		operator.setShopInfo(shopInfo);
		return users.get(0);
	}
}

package com.his.shiro;

import com.his.pojo.KpUser;
import com.his.service.KpPermissionService;
import com.his.service.KpRoleService;
import com.his.service.KpUserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Description: His自定义的Realm类， 处理shiro的登入和用户的权限注入
 * Date: 20-12-11
 *
 * @author yh
 */
public class HisRealm extends AuthorizingRealm {

    @Autowired
    private KpUserService kpUserService;

    @Autowired
    private KpRoleService kpRoleService;

    @Autowired
    private KpPermissionService kpPermissionService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        KpUser user = (KpUser) principal.getPrimaryPrincipal();
        List<Integer> roleIdList = kpRoleService.getRoleIdByUserId(user.getUserId());
        if (roleIdList != null  && roleIdList.size() > 0) {
            Integer[] roleIdArray = new Integer[roleIdList.size()];
            roleIdList.toArray(roleIdArray);
            List<String> permCodeList = kpPermissionService.searchPermCodeList(roleIdArray);
            info.addStringPermissions(permCodeList);
        }
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String loginName = (String) token.getPrincipal();
        KpUser kpUser = kpUserService.getKpUserByLoginName(loginName);
        if (kpUser == null) {
            return null;
        }
        return new SimpleAuthenticationInfo(kpUser, kpUser.getPassword(), getName());
    }
}
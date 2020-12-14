package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.his.mapper.KpPermissionMapper;
import com.his.pojo.KpPermission;
import com.his.pojo.KpUser;
import com.his.service.KpPermissionService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;

/**
 * Description: TO DO
 * Date: 20-12-11
 *
 * @author yh
 */
@Service
public class KpPermissionServiceImpl implements KpPermissionService {

    @Autowired
    private KpPermissionMapper kpPermissionMapper;

    /**
     * 根据角色Id查询该角色具有的权限
     * @param roleIds 角色Id
     * @return 查询到的角色Code returnType: List<String>
     */
    @Override
    public List<String> searchPermCodeList(Integer[] roleIds) {
        return kpPermissionMapper.searchPermCodeList(roleIds);
    }

    /**
     * 查询可以显示的菜单， 因为是二级目录所以使用Map即可模仿不需要自定义菜单类
     * Key=一级菜单  value=一级菜单对应的二级菜单
     * 内部使用了SecurityUtil.getSubject().getPrincipal()登入的对象。 所以不需要参数也可以
     * @return 菜单Map
     */
    @Override
    public Map<KpPermission, List<KpPermission>> searchMenuMap() {
        Map<KpPermission, List<KpPermission>> menu = new TreeMap<>();
        Subject subject = SecurityUtils.getSubject();
        KpUser user = (KpUser) subject.getPrincipal();
        List<KpPermission> menuLevelOne = searchByParentId(null);
        for (KpPermission permission : menuLevelOne) {
            // 判断有无一级目录的权限
            if (!subject.isPermitted(permission.getPermCode())) continue;
            List<KpPermission> menuLevelTwo = searchByParentId(permission.getPermId());
            menu.put(permission
                    , menuLevelTwo.stream()
                        // 判断有无二级目录的权限
                        .filter(perm -> subject.isPermitted(perm.getPermCode()))
                        .collect(Collectors.toList())
            );
        }
        return menu;
    }

    private List<KpPermission> searchByParentId(Integer parentId) {
        LambdaQueryWrapper<KpPermission> wrapper = new LambdaQueryWrapper<>();
        if (parentId == null) {
            wrapper.isNull(KpPermission::getParentId);
        } else {
            wrapper.eq(KpPermission::getParentId, parentId);
        }
        wrapper.orderByAsc(KpPermission::getSort);
        return kpPermissionMapper.selectList(wrapper);
    }

}
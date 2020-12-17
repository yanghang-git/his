package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.mapper.KpPermissionMapper;
import com.his.pojo.KpPermission;
import com.his.pojo.KpRole;
import com.his.service.KpPermissionService;
import com.his.util.LayuiTreeData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.security.Permission;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Description: TO DO
 * Date: 20-12-11
 *
 * @author yh
 */
@Service
public class KpPermissionServiceImpl extends ServiceImpl<KpPermissionMapper, KpPermission> implements KpPermissionService {

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

    @Override
    public Page<KpPermission> searchPage(Integer current, Integer size, String keyword) {
        Page<KpPermission> page=new Page<>(current,size);
        LambdaQueryWrapper<KpPermission> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)) {
            wrapper.like(KpPermission::getPermCode, keyword)
                    .or()
                    .like(KpPermission::getPermName, keyword)
                    .or()
                    .like(KpPermission::getPermUrl, keyword);
        }
        page(page, wrapper);
        return page;
    }

    @Override
    public List<LayuiTreeData> searchPermTree(Integer roleId) {
        List<Integer> permissionIdList = kpPermissionMapper.getPermissionIdByRoleId(roleId);
        return searchPermTreeBranch(permissionIdList,null);
    }

    @Override
    public List<LayuiTreeData> searchPermTree() {
        final String pattern = ".*menu";
        List<KpPermission> list = list().stream()
                .filter(perm -> Pattern.matches(pattern, perm.getPermCode()))
                .collect(Collectors.toList());
        Map<Integer, LayuiTreeData> treeDataMap = new HashMap<>(list.size());
        list.forEach(perm -> {
            if (perm.getParentId() == null) {
                treeDataMap.put(perm.getPermId(), new LayuiTreeData(perm));
            } else {
                treeDataMap.get(perm.getParentId()).addChildren(new LayuiTreeData(perm));
            }
        });
        return new ArrayList<>(treeDataMap.values());
    }

    @Override
    public List<KpPermission> searchPermMenu() {
        LambdaQueryWrapper<KpPermission> wrapper = new LambdaQueryWrapper<>();
        wrapper.likeLeft(KpPermission::getPermCode, ":menu");
        return list(wrapper);
    }

    private List<LayuiTreeData> searchPermTreeBranch(List<Integer> permissionIdList, Integer permissionId) {
        LambdaQueryWrapper<KpPermission> wrapper = new LambdaQueryWrapper<>();
        if (permissionId == null) {
            wrapper.isNull(KpPermission::getParentId);
        } else {
            wrapper.eq(KpPermission::getParentId, permissionId);
        }
        List<LayuiTreeData> list = kpPermissionMapper.selectList(wrapper).stream()
                .map(LayuiTreeData::new).collect(Collectors.toList());

        for (LayuiTreeData data : list) {
            List<LayuiTreeData> branch = searchPermTreeBranch(permissionIdList, data.getId());
            data.setChildren(branch);
            if ((branch == null || branch.size() <= 0) && permissionIdList != null) {
                data.setChecked(permissionIdList.contains(data.getId()));
            }
        }
        return list;
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
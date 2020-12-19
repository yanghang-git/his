package com.his.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.his.pojo.KpPermission;
import com.his.util.LayuiTreeData;

import java.util.List;
import java.util.Map;

/**
 * Description: TO DO
 * Date: 20-12-11
 *
 * @author yh
 */
public interface KpPermissionService extends IService<KpPermission> {
    /**
     * 根据角色Id查询该角色具有的权限
     * @param roleIds 角色Id
     * @return 查询到的角色Code returnType: List<String>
     */
    List<String> searchPermCodeList(Integer[] roleIds);

    /**
     * 查询可以显示的菜单， 因为是二级目录所以使用Map即可模仿不需要自定义菜单类
     *  Key=一级菜单  value=一级菜单对应的二级菜单
     *  内部使用了SecurityUtil.getSubject().getPrincipal()登入的对象。 所以不需要参数也可以
     * @return 菜单Map
     */
    Map<KpPermission, List<KpPermission>> searchMenuMap();

    Page<KpPermission> searchPage(Integer current, Integer size, String keyword);

    List<LayuiTreeData> searchPermTree(Integer roleId);

    List<LayuiTreeData> searchPermTree();

    List<KpPermission> searchPermMenu();

    Boolean checkPermRoleIsExist(Integer[] ids);
}

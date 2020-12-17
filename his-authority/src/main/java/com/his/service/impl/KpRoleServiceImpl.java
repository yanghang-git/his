package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.mapper.KpPermRoleMapper;
import com.his.mapper.KpRoleMapper;
import com.his.pojo.KpPermRole;
import com.his.pojo.KpRole;
import com.his.service.KpRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-10
 *
 * @author yh
 */
@Service
public class KpRoleServiceImpl extends ServiceImpl<KpRoleMapper, KpRole> implements KpRoleService {

    @Autowired
    private KpRoleMapper kpRoleMapper;

    @Autowired
    private KpPermRoleMapper kpPermRoleMapper;

    @Override
    public List<Integer> getRoleIdByUserId(Integer userId) {
        return kpRoleMapper.getRoleIdByUserId(userId);
    }

    @Override
    public List<String> getRoleCodeByUserId(Integer userId) {
        return kpRoleMapper.getROleCodeByUserId(userId);
    }

    @Override
    public Page<KpRole> searchPage(Integer current, Integer size, String keyword) {
        Page<KpRole> page = new Page<>(current, size);
        LambdaQueryWrapper<KpRole> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)) {
            wrapper.like(KpRole::getRoleCode, keyword)
                    .or()
                    .like(KpRole::getRoleLabel, keyword);
        }
        page(page, wrapper);
        return page;
    }

    @Override
    @Transactional
    public Boolean changePermByRoleId(Integer roleId, Integer[] permIds) {
        return removePermissionByRoleId(roleId)
                &
                saveInPermission(roleId, permIds);
    }

    private Boolean saveInPermission(Integer roleId, Integer[] permIds) {
        if (permIds == null || permIds.length == 0) {
            return true;
        }
        ArrayList<KpPermRole> list = new ArrayList<>(permIds.length);
        for (Integer perId : permIds) {
            list.add(new KpPermRole(perId, roleId));
        }
        return kpPermRoleMapper.insertBatch(list);
    }

    private Boolean removePermissionByRoleId(Integer roleId) {
        LambdaQueryWrapper<KpPermRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(KpPermRole::getRoleId, roleId);
        kpPermRoleMapper.delete(wrapper);
        return true;
    }

}
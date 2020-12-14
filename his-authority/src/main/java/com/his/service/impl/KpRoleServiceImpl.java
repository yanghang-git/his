package com.his.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.mapper.KpRoleMapper;
import com.his.pojo.KpRole;
import com.his.service.KpRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<Integer> getRoleIdByUserId(Integer userId) {
        return kpRoleMapper.getRoleIdByUserId(userId);
    }
}
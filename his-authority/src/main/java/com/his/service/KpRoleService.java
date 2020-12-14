package com.his.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.his.pojo.KpRole;

import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-10
 *
 * @author yh
 */
public interface KpRoleService extends IService<KpRole> {

    List<Integer> getRoleIdByUserId(Integer userId);
}
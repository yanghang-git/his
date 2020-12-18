package com.his.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.his.pojo.KpAdmin;
import org.apache.shiro.authz.annotation.RequiresPermissions;

/**
 * Description: TO DO
 * Date: 20-12-10
 *
 * @author yh
 */
public interface KpAdminService extends IService<KpAdmin> {
    KpAdmin getKpUserByLoginName(String loginName);

    Page<KpAdmin> searchPage(Integer current, Integer size,Integer adminShop, String loginName, String phone);

}
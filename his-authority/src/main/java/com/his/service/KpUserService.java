package com.his.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.his.pojo.KpUser;

/**
 * Description: TO DO
 * Date: 20-12-10
 *
 * @author yh
 */
public interface KpUserService extends IService<KpUser> {
    KpUser getKpUserByLoginName(String loginName);

    Page<KpUser> searchPage(Integer current, Integer size, String loginName, String email, String phone);

}
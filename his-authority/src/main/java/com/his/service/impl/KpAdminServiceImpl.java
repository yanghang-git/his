package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.mapper.KpAdminMapper;
import com.his.pojo.KpAdmin;
import com.his.service.KpAdminService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Description: TO DO
 * Date: 20-12-10
 *
 * @author yh
 */
@Service
public class KpAdminServiceImpl extends ServiceImpl<KpAdminMapper, KpAdmin> implements KpAdminService {

    @Override
    public KpAdmin getKpUserByLoginName(String loginName) {
        LambdaQueryWrapper<KpAdmin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(KpAdmin::getLoginName, loginName);
        return getOne(queryWrapper);
    }


    @Override
    public Page<KpAdmin> searchPage(Integer current, Integer size, String loginName, String email, String phone) {
        Page<KpAdmin> page=new Page<>(current,size);
        LambdaQueryWrapper<KpAdmin> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(!StringUtils.isEmpty(loginName), KpAdmin::getLoginName, loginName);
        wrapper.like(!StringUtils.isEmpty(email), KpAdmin::getEmail, email);
        wrapper.like(!StringUtils.isEmpty(phone), KpAdmin::getMobileNo, phone);
        page(page,wrapper);
        return page;
    }

}
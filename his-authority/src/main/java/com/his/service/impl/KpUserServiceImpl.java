package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.mapper.KpUserMapper;
import com.his.pojo.KpUser;
import com.his.service.KpUserService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Description: TO DO
 * Date: 20-12-10
 *
 * @author yh
 */
@Service
public class KpUserServiceImpl extends ServiceImpl<KpUserMapper, KpUser> implements KpUserService {

    @Override
    public KpUser getKpUserByLoginName(String loginName) {
        LambdaQueryWrapper<KpUser> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(KpUser::getLoginName, loginName);
        return getOne(queryWrapper);
    }

    @Override
    public Page<KpUser> searchPage(Integer current, Integer size, String loginName, String email, String phone) {
        Page<KpUser> page=new Page<>(current,size);
        LambdaQueryWrapper<KpUser> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(!StringUtils.isEmpty(loginName), KpUser::getLoginName, loginName);
        wrapper.like(!StringUtils.isEmpty(email), KpUser::getEmail, email);
        wrapper.like(!StringUtils.isEmpty(phone), KpUser::getMobileNo, phone);
        page(page,wrapper);
        return page;
    }

}
package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.exception.ShopNotExistException;
import com.his.mapper.KpAdminMapper;
import com.his.mapper.KpRoleAdminMapper;
import com.his.mapper.KpRoleMapper;
import com.his.pojo.KpAdmin;
import com.his.pojo.KpPermRole;
import com.his.pojo.KpRoleAdmin;
import com.his.service.KpAdminService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;

/**
 * Description: TO DO
 * Date: 20-12-10
 *
 * @author yh
 */
@Service
public class KpAdminServiceImpl extends ServiceImpl<KpAdminMapper, KpAdmin> implements KpAdminService {

    @Autowired
    private KpRoleMapper kpRoleMapper;

    @Autowired
    private KpRoleAdminMapper kpRoleAdminMapper;

    @Autowired
    private KpAdminMapper kpAdminMapper;

    @Override
    public KpAdmin getKpUserByLoginName(String loginName) {
        LambdaQueryWrapper<KpAdmin> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(KpAdmin::getAdminUsername, loginName);
        return getOne(queryWrapper);
    }


    @Override
    public Page<KpAdmin> searchPage(Integer current, Integer size, Integer adminShop, String loginName, String phone) {
        Page<KpAdmin> page=new Page<>(current,size);
        LambdaQueryWrapper<KpAdmin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(!adminShop.equals(-1), KpAdmin::getAdminShop, adminShop);
        wrapper.like(!StringUtils.isEmpty(loginName), KpAdmin::getAdminUsername, loginName);
        wrapper.like(!StringUtils.isEmpty(phone), KpAdmin::getAdminPhone, phone);
        page(page,wrapper);
        return page;
    }

    @Override
    public boolean editKpAdminNotNullFeildById(KpAdmin admin) {
        if (StringUtils.isEmpty(admin.getAdminId()))
            return false;

        if (!StringUtils.isEmpty(admin.getAdminPass())) {
            admin.setAdminPass(new Md5Hash(admin.getAdminPass(), null , 1).toString());
        }
        LambdaUpdateWrapper<KpAdmin> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(!StringUtils.isEmpty(admin.getAdminUsername()), KpAdmin::getAdminUsername, admin.getAdminUsername())
                .set(!StringUtils.isEmpty(admin.getAdminPass()), KpAdmin::getAdminPass, admin.getAdminPass())
                .set(!StringUtils.isEmpty(admin.getAdminName()), KpAdmin::getAdminName, admin.getAdminName())
                .set(!StringUtils.isEmpty(admin.getAdminShop()), KpAdmin::getAdminShop, admin.getAdminShop())
                .set(admin.getAdminSex() != null, KpAdmin::getAdminSex, admin.getAdminSex())
                .set(admin.getAdminShop() != null, KpAdmin::getAdminShop, admin.getAdminShop())
                .eq(KpAdmin::getAdminId, admin.getAdminId());

        return update(wrapper);
    }

    @Override
    @Transactional
    public Boolean changeRoleByAdminId(Integer adminId, Integer[] roleIds) {
        return removeRoleByAdminId(adminId)
                &
                saveInRole(adminId, roleIds);
    }

    /**
     * 检查登录帐号所在的店铺是否存在  如果不存在则抛出 ShopNotExistException
     * @param loginName 登录帐号
     */
    @Override
    public void checkAdminOfShopIsExist(String loginName) {
        if (kpAdminMapper.checkAdminOfShopIsExist(loginName) == 0) {
            throw new ShopNotExistException("登录的门店不存在");
        }
    }

    private Boolean saveInRole(Integer adminId, Integer[] roleIds) {
        if (roleIds == null || roleIds.length == 0) {
            return true;
        }
        ArrayList<KpRoleAdmin> list = new ArrayList<>(roleIds.length);
        for (Integer roleId : roleIds) {
            list.add(new KpRoleAdmin(roleId, adminId));
        }
        return kpRoleMapper.insertBatch(list);
    }

    private Boolean removeRoleByAdminId(Integer roleId) {
        LambdaQueryWrapper<KpRoleAdmin> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(KpRoleAdmin::getAdminId, roleId);
        kpRoleAdminMapper.delete(wrapper);
        return true;
    }

}
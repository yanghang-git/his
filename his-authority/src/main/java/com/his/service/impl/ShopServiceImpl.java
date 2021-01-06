package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.mapper.ShopMapper;
import com.his.pojo.KpAdmin;
import com.his.pojo.Shop;
import com.his.service.ShopService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Description: TO DO
 * Date: 20-12-18
 *
 * @author yh
 */
@Service
public class ShopServiceImpl extends ServiceImpl<ShopMapper, Shop> implements ShopService {

    @Override
    public Page<Shop> searchPage(Integer current, Integer size, String keyword) {
        Page<Shop> page=new Page<>(current,size);
        LambdaQueryWrapper<Shop> wrapper = new LambdaQueryWrapper<>();
        if (!StringUtils.isEmpty(keyword)) {
            wrapper.like(Shop::getShopAddress, keyword)
                    .or()
                    .like(Shop::getShopName, keyword);
        }
        page(page, wrapper);
        return page;
    }

    @Override
    public String getShopNameById(Integer adminShop) {
        if (adminShop == null)
            return null;
        LambdaQueryWrapper<Shop> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Shop::getShopId, adminShop);
        return getOne(wrapper).getShopName();

    }

    @Override
    public String getCurrentShopName() {
        KpAdmin admin = (KpAdmin) SecurityUtils.getSubject().getPrincipal();
        return getShopNameById(admin.getAdminShop());
    }


}
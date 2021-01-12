package com.his.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.his.pojo.Shop;

/**
 * Description: TO DO
 * Date: 20-12-18
 *
 * @author yh
 */
public interface ShopService extends IService<Shop> {
    Page<Shop> searchPage(Integer current, Integer size, String keyword);

    String getShopNameById(Integer adminShop);

    String getCurrentShopName();
}

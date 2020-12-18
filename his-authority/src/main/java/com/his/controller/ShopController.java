package com.his.controller;

import com.his.pojo.Shop;
import com.his.service.ShopService;
import com.his.util.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-18
 *
 * @author yh
 */
@RestController
@RequestMapping("/shop/")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @RequestMapping("/list")
    public LayuiResult<List<Shop>> getList() {
        return LayuiResult.success(shopService.list());
    }
}
package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.mapper.RentOutLogMapper;
import com.his.mapper.RentOutLogMapper1;
import com.his.pojo.RentOutLog;
import com.his.service.RentOutLogService;
import com.his.service.RentOutLogService1;
import com.his.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class RentOutLogServiceImpl extends ServiceImpl<RentOutLogMapper, RentOutLog> implements RentOutLogService {

    @Autowired
    private ShopService shopService;

    @Autowired
    private RentOutLogMapper rentOutLogMapper;

    @Override
    public Page<RentOutLog> searchPage(Integer current, Integer size, String clientName, String logNumbers, String ofTheTime, String actualReturnTime) {
        Page<RentOutLog> page = new Page<>(current,size);
        LambdaQueryWrapper<RentOutLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RentOutLog::getLogShop, shopService.getCurrentShopName());
        wrapper.like(!StringUtils.isEmpty(clientName), RentOutLog::getClientName,clientName);
        wrapper.eq(!StringUtils.isEmpty(logNumbers), RentOutLog::getLogNumbers, logNumbers);
        wrapper.ge(!StringUtils.isEmpty(ofTheTime), RentOutLog::getOfTheTime,ofTheTime);
        wrapper.le(!StringUtils.isEmpty(actualReturnTime), RentOutLog::getActualReturnTime,actualReturnTime);
        wrapper.orderByDesc(RentOutLog::getActualReturnTime);
        return page(page, wrapper);
    }

    @Override
    public String getLastOddNumbers() {
        return rentOutLogMapper.getLastOddNumbers();
    }
}


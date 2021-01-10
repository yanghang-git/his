package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.mapper.OrderHistoryMapper;
import com.his.pojo.RentOut;
import com.his.pojo.RentOutLog;
import com.his.service.OrderHistoryService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;


@Service
public class OrderHistoryServiceImpl extends ServiceImpl<OrderHistoryMapper,RentOutLog> implements OrderHistoryService {

    @Override
    public Page<RentOutLog> select(Integer current, Integer size, String clientName, String logNumbers, String ofTheTime,String actualReturnTime) {
        Page<RentOutLog> page = new Page<>(current,size);
        LambdaQueryWrapper<RentOutLog> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(!StringUtils.isEmpty(clientName),RentOutLog::getClientName,clientName);
        wrapper.eq(!StringUtils.isEmpty(logNumbers), RentOutLog::getLogNumbers, logNumbers);

        wrapper.ge(!StringUtils.isEmpty(ofTheTime),RentOutLog::getOfTheTime,ofTheTime);
        wrapper.le(!StringUtils.isEmpty(actualReturnTime),RentOutLog::getActualReturnTime,actualReturnTime);
        return page(page, wrapper);
        }
    }


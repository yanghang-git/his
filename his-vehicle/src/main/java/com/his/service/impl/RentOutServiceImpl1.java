package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.mapper.RentOutMapper1;
import com.his.pojo.RentOut;
import com.his.service.RentOutService1;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentOutServiceImpl1 extends ServiceImpl<RentOutMapper1, RentOut> implements RentOutService1 {

    @Override
    public boolean checkClientWhetherBeingLease(List<String> clientIdList) {
        return count(new LambdaQueryWrapper<RentOut>().in(RentOut::getClientId, clientIdList)) != 0;
    }
}

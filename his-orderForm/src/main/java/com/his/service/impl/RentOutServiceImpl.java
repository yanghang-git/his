package com.his.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.mapper.RentOutMapper;
import com.his.pojo.RentOut;
import com.his.service.RentOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Description: TO DO
 * Date: 20-12-29
 *
 * @author yh
 */
@Service
public class RentOutServiceImpl extends ServiceImpl<RentOutMapper, RentOut> implements RentOutService {

    @Autowired
    private RentOutMapper rentOutMapper;


    @Override
    public String getLastOddNumbers() {
        return rentOutMapper.getLastOddNumbers();
    }
}
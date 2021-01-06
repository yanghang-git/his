package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.mapper.RentOutVehicleMapper;
import com.his.pojo.RentOutVehicle;
import com.his.service.RentOutVehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: TO DO
 * Date: 20-12-29
 *
 * @author yh
 */
@Service
public class RentOutVehicleServiceImpl extends ServiceImpl<RentOutVehicleMapper, RentOutVehicle> implements RentOutVehicleService {

    @Override
    public List<RentOutVehicle> searchRentOutVehicleByRentOutOddNumbers(String oddNumbers) {
        if (StringUtils.isEmpty(oddNumbers)) {
            return Collections.emptyList();
        }
        LambdaQueryWrapper<RentOutVehicle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RentOutVehicle::getOddNumbers, oddNumbers);
        return list(wrapper);
    }

    @Override
    public List<String> getVehiclePlateNumberByOddNumbers(String oddNumbers) {
        LambdaQueryWrapper<RentOutVehicle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RentOutVehicle::getOddNumbers, oddNumbers);
        return list(wrapper).stream().map(RentOutVehicle::getVehiclePlateNumber).collect(Collectors.toList());
    }
}
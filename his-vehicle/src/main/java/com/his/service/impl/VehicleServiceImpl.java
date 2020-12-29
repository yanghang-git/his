package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.mapper.VehicleMapper;
import com.his.pojo.Vehicle;
import com.his.service.VehicleService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * Description: TO DO
 * Date: 20-12-26
 *
 * @author yh
 */
@Service
public class VehicleServiceImpl extends ServiceImpl<VehicleMapper, Vehicle>  implements VehicleService {

    /**
     * 查找可以租借的车辆
     * @param shop               门店的Id。 标明查询那个门店下的可租借车辆， 如果指定则查询全部
     * @param licensePlateNumber 车牌号
     * @param vehicleColor       车辆颜色 采用模糊查询
     * @param vehicleType        车辆种类
     * @param rentOutStart       出租价格区间 起始价格
     * @param rentOutEnd         出租价格区间 最高价格
     * @return 查询到的车辆。 封装成一个Page 进行返回
     */
    @Override
    public Page<Vehicle> searchPageByKeyword(Integer current, Integer size, Integer shop, String licensePlateNumber, String vehicleColor, Integer vehicleType, String vehicleDesc, Double rentOutStart, Double rentOutEnd) {
        Page<Vehicle> page = new Page<>(current, size);
        LambdaQueryWrapper<Vehicle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Vehicle::getVehicleState, true);
        wrapper.eq(shop != null, Vehicle::getShop, shop);
        wrapper.eq(!StringUtils.isEmpty(licensePlateNumber), Vehicle::getLicensePlateNumber, licensePlateNumber);
        wrapper.eq(vehicleType != null, Vehicle::getVehicleType, vehicleType);
        wrapper.ge(rentOutStart != null, Vehicle::getRentOut, rentOutStart);
        wrapper.le(rentOutEnd != null, Vehicle::getRentOut, rentOutEnd);
        wrapper.like(!StringUtils.isEmpty(vehicleDesc), Vehicle::getVehicleDescribe, vehicleDesc);
        wrapper.like(!StringUtils.isEmpty(vehicleColor), Vehicle::getVehicleColor, vehicleColor);
        return page(page, wrapper);
    }
}
package com.his.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.his.pojo.Vehicle;
import com.his.pojo.VehicleType;

public interface VehicleService extends IService<Vehicle> {
    /**
     * 查找可以租借的车辆
     * @param shop                  门店的Id。 标明查询那个门店下的可租借车辆， 如果指定则查询全部
     * @param licensePlateNumber    车牌号
     * @param vehicleColor          车辆颜色 采用模糊查询
     * @param vehicleType           车辆种类
     * @param rentOutStart          出租价格区间 起始价格
     * @param rentOutEnd            出租价格区间 最高价格
     * @return 查询到的车辆。 封装成一个Page 进行返回
     */
    Page<Vehicle> searchPageByKeyword(Integer current, Integer size,Integer shop, String licensePlateNumber, String vehicleColor, Integer vehicleType, String vehicleDesc, Double rentOutStart, Double rentOutEnd);
}

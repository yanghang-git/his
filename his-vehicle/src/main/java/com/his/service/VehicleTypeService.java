package com.his.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.his.pojo.VehicleType;

public interface VehicleTypeService extends IService<VehicleType> {
    Page<VehicleType> searchPageByKeyword(Integer current, Integer size, String keyword);
}

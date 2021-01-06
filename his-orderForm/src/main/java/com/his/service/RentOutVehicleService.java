package com.his.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.his.pojo.RentOutVehicle;

import java.util.Collection;
import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-29
 *
 * @author yh
 */
public interface RentOutVehicleService extends IService<RentOutVehicle> {
    List<RentOutVehicle> searchRentOutVehicleByRentOutOddNumbers(String oddNumbers);

    List<String> getVehiclePlateNumberByOddNumbers(String oddNumbers);
}

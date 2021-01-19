package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.exception.OrderFormSaveFailedException;
import com.his.mapper.VehicleMapper;
import com.his.pojo.KpAdmin;
import com.his.pojo.Vehicle;
import com.his.service.VehicleService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

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

    @Override
    public Boolean changeVehicleState(List<String> licensePlateNumberList, boolean state) {
        LambdaUpdateWrapper<Vehicle> wrapper = new LambdaUpdateWrapper<>();
        wrapper.set(Vehicle::getVehicleState, state);
        wrapper.in(licensePlateNumberList != null && licensePlateNumberList.size() > 0, Vehicle::getLicensePlateNumber, licensePlateNumberList);
        return update(wrapper);
    }

    @Override
    public void checkOrderFormOfVehicleWhetherRent(List<String> vehicleList, Integer shopId) {
        LambdaQueryWrapper<Vehicle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Vehicle::getVehicleState, false);
        wrapper.eq(Vehicle::getShop, shopId);
        wrapper.in(Vehicle::getLicensePlateNumber, vehicleList);
        List<Vehicle> list = list(wrapper);
        if (list != null && list.size() > 0) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < list.size(); i++) {
                sb.append(list.get(i).getLicensePlateNumber());
                if (i != list.size() - 1)
                    sb.append(" , ");

            }
            throw new OrderFormSaveFailedException("租借的车辆中[ " + sb + " ]已被出租，租借失败。");
        }
    }

    @Override
    public String searchVehicleDescByPrimaryKey(String primaryKey) {
        return getById(primaryKey).getVehicleDescribe();
    }

    @Override
    public Page<Vehicle> searchPage(Integer current, Integer size, String plateNumber, Integer type, String color, String desc, Boolean outState) {
        KpAdmin admin = (KpAdmin) SecurityUtils.getSubject().getPrincipal();
        Page<Vehicle> page = new Page<>(current, size);
        LambdaQueryWrapper<Vehicle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Vehicle::getShop, admin.getAdminShop());
        wrapper.eq(!StringUtils.isEmpty(plateNumber), Vehicle::getLicensePlateNumber, plateNumber);
        wrapper.eq(type != null, Vehicle::getVehicleType, type);
        wrapper.eq(outState != null, Vehicle::getVehicleState, outState);
        wrapper.like(!StringUtils.isEmpty(color), Vehicle::getVehicleColor, color);
        wrapper.like(!StringUtils.isEmpty(desc), Vehicle::getVehicleDescribe, desc);
        return page(page, wrapper);
    }
}
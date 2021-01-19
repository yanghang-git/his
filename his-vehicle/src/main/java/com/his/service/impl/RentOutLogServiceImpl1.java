package com.his.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.mapper.RentOutLogMapper1;
import com.his.pojo.KpAdmin;
import com.his.pojo.RentOutLog;
import com.his.service.RentOutLogService1;
import com.his.service.ShopService;
import com.his.service.VehicleTypeService;
import com.his.vo.RentOutLogVo;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RentOutLogServiceImpl1 extends ServiceImpl<RentOutLogMapper1, RentOutLog> implements RentOutLogService1 {

    @Autowired
    private ShopService shopService;

    @Autowired
    private VehicleTypeService vehicleTypeService;

    @Override
    public List<RentOutLog> addRentOutLogList(RentOutLogVo vo) {
        Subject subject = SecurityUtils.getSubject();
        KpAdmin kpAdmin =(KpAdmin) subject.getPrincipal();
        System.out.println(vo.getRentOutLog());
        List<RentOutLog> list = new ArrayList<>();
        RentOutLog rentOutLog = new RentOutLog();
        rentOutLog.setLogNumbers(vo.getRentOutLog().getLogNumbers());
        rentOutLog.setOfTheTime(vo.getRentOutLog().getOfTheTime());
        rentOutLog.setPredictReturnTime(vo.getRentOutLog().getPredictReturnTime());
        rentOutLog.setPredictRentOut(vo.getRentOutLog().getPredictRentOut());
        rentOutLog.setActualReturnTime(vo.getRentOutLog().getActualReturnTime());
        rentOutLog.setActualCollectionMoney(vo.getRentOutLog().getActualCollectionMoney());
        rentOutLog.setClientName(vo.getRentOutLog().getClientName());
        rentOutLog.setClientPhone(vo.getRentOutLog().getClientPhone());
        rentOutLog.setClientSex(vo.getRentOutLog().getClientSex());
        rentOutLog.setExistingProblem(vo.getRentOutLog().getExistingProblem());
        rentOutLog.setLogShop(shopService.getCurrentShopName());
        rentOutLog.setCompensatePrice(vo.getRentOutLog().getCompensatePrice());
        rentOutLog.setOperator(kpAdmin.getAdminName());
        for (int i = 0;i<vo.getLicensePlateNumberList().size();i++){
            rentOutLog.setLicensePlateNumber(vo.getLicensePlateNumberList().get(i));
            rentOutLog.setVehicleType(vehicleTypeService.getById(vo.getVehicleTypeList().get(i)).getTypeName());
            rentOutLog.setVehicleDesc(vo.getVehicleDescribeList().get(i));
            list.add(rentOutLog);
        }
        return list;
    }
}

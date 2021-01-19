package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.his.exception.OrderFormCancelFailedException;
import com.his.exception.OrderFormSaveFailedException;
import com.his.pojo.*;
import com.his.service.*;
import com.his.util.Util;
import com.his.vo.RentOutVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Description: TO DO
 * Date: 20-12-29
 *
 * @author yh
 */
@Service
public class OrderFormServiceImpl implements OrderFormService {


    @Autowired
    private ClientService clientService;

    @Autowired
    private RentOutService rentOutService;

    @Autowired
    private RentOutVehicleService rentOutVehicleService;

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private RentOutLogService rentOutLogService;

    @Autowired
    private VehicleTypeService vehicleTypeService;

    @Autowired
    private ShopService shopService;

    @Override
    @Transactional
    public Boolean addRentOutOrderForm(RentOutVo vo) {
        KpAdmin admin = (KpAdmin) SecurityUtils.getSubject().getPrincipal();
        List<String> getRentOutVehicleLicensePlateNumber =
                vo.getRentOutVehicleList().stream().map(RentOutVehicle::getVehiclePlateNumber).collect(Collectors.toList());
        checkOrderFormOfVehicleWhetherRent(getRentOutVehicleLicensePlateNumber, admin.getAdminShop());
        // 设置操作车 为 登录用户的Id
        vo.getRentOut().setOperator(admin.getAdminId());
        // 设置订单的门店为 登录用户的门店
        vo.getRentOut().setRentOutShop(admin.getAdminShop());
        // 设置订单生成的时间为 当前时间
        vo.getRentOut().setGenerateTime(LocalDateTime.now());
        if (vo.getIsAdd()) {
            vo.getClient().setCreateTime(LocalDateTime.now());
            vo.getClient().setClientSex(true);
            clientService.save(vo.getClient());
        } else if (vo.getIsUpdate()) {
            clientService.updateById(vo.getClient());
        }
        String formId = Util.compareFormId(rentOutService.getLastOddNumbers(), rentOutLogService.getLastOddNumbers());
        String oddNumbers = Util.generateOrderFormId(formId);
        vo.getRentOut().setOddNumbers(oddNumbers);
        boolean saveRentOutMark = rentOutService.save(vo.getRentOut());
        vo.setRentOutVehicleList(vo.getRentOutVehicleList().stream().peek(item -> item.setOddNumbers(oddNumbers)).collect(Collectors.toList()));
        System.out.println(vo.getRentOutVehicleList());
        boolean saveBatchRentOutVehicleMark = rentOutVehicleService.saveBatch(vo.getRentOutVehicleList());
        boolean changeVehicleStateMark = vehicleService.changeVehicleState(getRentOutVehicleLicensePlateNumber, false);
        if (!saveBatchRentOutVehicleMark || !saveRentOutMark || !changeVehicleStateMark) {
            throw new OrderFormSaveFailedException("添加订单失败");
        }
        return true;
    }

    @Override
    public Page<RentOut> select(Integer current, Integer size, String clientName, String oddNumbers, String generateTimeStart, String generateTimeEnd) {
        if (StringUtils.isEmpty(clientName)) {
            return rentOutService.searchPageByKeyword(current, size, oddNumbers, null, generateTimeStart, generateTimeEnd);
        } else {
            List<String> clientIdByClientName = clientService.getClientIdByClientName(clientName);
            if (clientIdByClientName == null || clientIdByClientName.size() == 0) {
                return new Page<>(current, size);
            }
            return rentOutService.searchPageByKeyword(current, size, oddNumbers, clientIdByClientName, generateTimeStart, generateTimeEnd);
        }
    }

    @Override
    public Page<RentOut> select1(Integer current, Integer size, String oddNumbers, String clientName,Integer adminShop,Integer isPickUp) {
        if (StringUtils.isEmpty(clientName)) {
            return rentOutService.searchPageByOddNumbersAndClientIdNumbser(current, size, oddNumbers, null,adminShop,isPickUp);
        } else {
            List<String> clientIdByClientName = clientService.getClientIdNumberByClientName(clientName);
            if (clientIdByClientName == null || clientIdByClientName.size() == 0) {
                return new Page<>(current, size);
            }
            return rentOutService.searchPageByOddNumbersAndClientIdNumbser(current, size, oddNumbers, clientIdByClientName,adminShop,isPickUp);
        }
    }
    @Override
    @Transactional
    public boolean cancelOrderForm(RentOut rentOut) {
        KpAdmin admin  = (KpAdmin) SecurityUtils.getSubject().getPrincipal();
        Client byId = clientService.getById(rentOut.getClientId());
        LambdaQueryWrapper<RentOutVehicle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(RentOutVehicle::getOddNumbers, rentOut.getOddNumbers());
        List<RentOutVehicle> list = rentOutVehicleService.list(wrapper);
        List<String> rentOutVehicleOddNumbers = list.stream().map(RentOutVehicle::getVehiclePlateNumber).collect(Collectors.toList());
        ArrayList<RentOutLog> logList = new ArrayList<>();
        RentOutLog rentOutLog = new RentOutLog();
        rentOutLog.setLogNumbers(rentOut.getOddNumbers());
        rentOutLog.setOfTheTime(rentOut.getOfTheTime());
        rentOutLog.setPredictRentOut(rentOut.getRentOutTotalMoney());
        rentOutLog.setPredictReturnTime(rentOut.getPredictReturnTime());
        rentOutLog.setActualReturnTime(LocalDateTime.now());
        rentOutLog.setActualCollectionMoney(500);
        rentOutLog.setClientName(byId.getClientName());
        rentOutLog.setClientPhone(byId.getClientPhone());
        rentOutLog.setClientSex(byId.getClientSex());
        rentOutLog.setExistingProblem("退单");
        rentOutLog.setCompensatePrice(0);
        rentOutLog.setLogShop(shopService.getShopNameById(admin.getAdminShop()));
        rentOutLog.setOperator(admin.getAdminName());
        for (RentOutVehicle rent : list) {
            try {
                RentOutLog emp = (RentOutLog) rentOutLog.clone();
                emp.setLicensePlateNumber(rent.getVehiclePlateNumber());
                Vehicle vehicle = vehicleService.getById(rent.getVehiclePlateNumber());
                emp.setVehicleDesc(vehicle.getVehicleDescribe());
                emp.setVehicleType(vehicleTypeService.getById(vehicle.getVehicleType()).getTypeName());
                logList.add(emp);
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }
        boolean saveFlag = rentOutLogService.saveBatch(logList);
        boolean removeFlag = rentOutService.removeById(rentOut.getOddNumbers());
        boolean changeVehicleStatueFlag = vehicleService.changeVehicleState(rentOutVehicleOddNumbers, true);
        if (!saveFlag) {
            throw new OrderFormCancelFailedException("renOutLogSaveFailed");
        } else if (!removeFlag) {
            throw new OrderFormCancelFailedException("removeRentOutFailed");
        } else if (!changeVehicleStatueFlag) {
            throw new OrderFormCancelFailedException("changeVehicleStatueFailed");
        } else {
            return true;
        }
    }

    @Override
    public boolean outVehicle(String oddNumbers) {
        if (!StringUtils.isEmpty(oddNumbers)) {
            Double totalRentOutMoney = getTotalRentOutMoneyByOddNumbers(oddNumbers);
            LambdaUpdateWrapper<RentOut> wrapper = new LambdaUpdateWrapper<>();
            wrapper.set(RentOut::getIsPickUp, true)
                    .set(RentOut::getRentOutTotalMoney, totalRentOutMoney);
            wrapper.eq(RentOut::getOddNumbers, oddNumbers);
            return rentOutService.update(wrapper);
        }
        return false;
    }

    @Override
    public List<RentOutVehicle> searchRentOutVehicleByRentOutOddNumbers(String oddNumbers) {
        return rentOutVehicleService.searchRentOutVehicleByRentOutOddNumbers(oddNumbers);
    }

    @Override
    public String searchVehicleDescByPrimaryKey(String primaryKey) {
        return vehicleService.searchVehicleDescByPrimaryKey(primaryKey);
    }

    @Override
    public Double getTotalRentOutMoneyByOddNumbers(String oddNumbers) {
        return rentOutVehicleService.searchRentOutVehicleByRentOutOddNumbers(oddNumbers).stream().mapToDouble(RentOutVehicle::getRentOutMoney).sum();
    }

    @Override
    public List<Vehicle> getVehicleListByOddNumbers(String oddNumbers) {
        List<String> vehiclePlateNumber = rentOutVehicleService.getVehiclePlateNumberByOddNumbers(oddNumbers);
        return vehicleService.listByIds(vehiclePlateNumber);
    }


    /**
     * 检查车辆是否有已经被出租   如果有则报错(租借失败，并展示以出租的车辆) 由SpringMVC进行捕捉异常处理。
     * @param vehicleList 车辆 车牌号
     * @param shopId 车辆的的门店
     */
    private void checkOrderFormOfVehicleWhetherRent(List<String> vehicleList, Integer shopId) {
        vehicleService.checkOrderFormOfVehicleWhetherRent(vehicleList, shopId);
    }
}
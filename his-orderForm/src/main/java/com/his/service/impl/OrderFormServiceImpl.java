package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.his.exception.OrderFormSaveFailedException;
import com.his.pojo.KpAdmin;
import com.his.pojo.RentOutVehicle;
import com.his.pojo.Vehicle;
import com.his.service.*;
import com.his.util.Util;
import com.his.vo.RentOutVo;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
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

    @Override
    @Transactional
    public Boolean addRentOutOrderForm(RentOutVo vo) {
        KpAdmin admin = (KpAdmin) SecurityUtils.getSubject().getPrincipal();
        List<String> getRentOutVehicleLicensePlateNumber = vo.getRentOutVehicleList().stream().map(RentOutVehicle::getVehiclePlateNumber).collect(Collectors.toList());
        checkOrderFormOfVehicleWhetherRent(getRentOutVehicleLicensePlateNumber, admin.getAdminShop());
        // 设置操作车 为 登录用户的Id
        vo.getRentOut().setOperator(admin.getAdminId());
        // 设置订单的门店为 登录用户的门店
        vo.getRentOut().setRentOutShop(admin.getAdminShop());
        // 设置订单生成的时间为 当前时间
        vo.getRentOut().setGenerateTime(LocalDateTime.now());
        if (vo.getIsAdd()) {
            vo.getClient().setCreateTime(LocalDateTime.now());
            clientService.save(vo.getClient());
        } else if (vo.getIsUpdate()) {
            clientService.updateById(vo.getClient());
        }
        String oddNumbers = Util.generateOrderFormId(rentOutService.getLastOddNumbers());
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


    /**
     * 检查车辆是否有已经被出租   如果有则报错(租借失败，并展示以出租的车辆) 由SpringMVC进行捕捉异常处理。
     * @param vehicleList 车辆 车牌号
     * @param shopId 车辆的的门店
     */
    private void checkOrderFormOfVehicleWhetherRent(List<String> vehicleList, Integer shopId) {
        LambdaQueryWrapper<Vehicle> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Vehicle::getVehicleState, false);
        wrapper.eq(Vehicle::getShop, shopId);
        wrapper.in(Vehicle::getLicensePlateNumber, vehicleList);
        List<Vehicle> list = vehicleService.list(wrapper);
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
}
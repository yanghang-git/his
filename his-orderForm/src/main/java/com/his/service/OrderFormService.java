package com.his.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.his.pojo.RentOut;
import com.his.pojo.RentOutVehicle;
import com.his.pojo.Vehicle;
import com.his.vo.RentOutVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-29
 *
 * @author yh
 */
public interface OrderFormService {

    Boolean addRentOutOrderForm(RentOutVo vo);

    Page<RentOut> select(Integer page, Integer limit, String clientName, String oddNumbers , String generateTimeStart, String generateTimeEnd);

    boolean cancelOrderForm(RentOut rentOut);

    boolean outVehicle(String oddNumbers);

    List<RentOutVehicle> searchRentOutVehicleByRentOutOddNumbers(String oddNumbers);

    String searchVehicleDescByPrimaryKey(String primaryKey);

    Double getTotalRentOutMoneyByOddNumbers(String oddNumbers);

    List<Vehicle> getVehicleListByOddNumbers(String oddNumbers);
}
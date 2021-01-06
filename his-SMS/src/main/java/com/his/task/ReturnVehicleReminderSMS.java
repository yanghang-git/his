package com.his.task;

import com.his.mapper.ReturnVehicleSmsMapper;
import com.his.pojo.ReturnVehicleSms;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: 还车提示短信类
 * Date: 21-1-4
 *
 * @author yh
 */
@Component
public class ReturnVehicleReminderSMS {

    @Autowired
    private ReturnVehicleSmsMapper returnVehicleSmsMapper;

    public void sendSMS() {
        ReturnVehicleSms sms = returnVehicleSmsMapper.selectRecent();
        System.out.println("我们是" + sms.getShopName() + "门店。 " + sms.getClientName() + "客户你租借的车牌号为：" + sms.getVehiclePlateNumber() + " 一小时后到期。 请到门店归还车辆。 地址为：" + sms.getShopAddress());
        returnVehicleSmsMapper.deleteById(sms.getOddNumbers());
    }
}
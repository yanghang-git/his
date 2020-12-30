package com.his.eunms;

import com.baomidou.mybatisplus.annotation.EnumValue;

/**
 * Description: 订单的状态
 * Date: 20-12-29
 *
 * @author yh
 */
public enum CallVehicleState {
    APPLYING("申请中")
    , BORROWING("借用中")
    , RETURNED("已归还");
    @EnumValue
    private String value;
    private CallVehicleState(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
package com.his.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.his.pojo.CallVehicleRecord;

import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-30
 *
 * @author yh
 */
public interface CallVehicleRecordService extends IService<CallVehicleRecord> {
    List<CallVehicleRecord> getApplyingRecordByReturnShop(Integer resultShop);

    boolean addApplyBorrowRecord(CallVehicleRecord record);
}

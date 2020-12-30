package com.his.controller;

import com.his.exception.ApplyBorrowRecordFailedException;
import com.his.pojo.CallVehicleRecord;
import com.his.service.CallVehicleRecordService;
import com.his.util.LayuiResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-30
 *
 * @author yh
 */
@RestController
@RequestMapping("/vehicle/callRecord/")
public class CallVehicleRecordController {

    @Autowired
    private CallVehicleRecordService callVehicleRecordService;

    @RequestMapping("add")
    public LayuiResult<?> addRecord(CallVehicleRecord record) {
        boolean flag = callVehicleRecordService.addApplyBorrowRecord(record);
        return flag ? LayuiResult.success("申请成功") : LayuiResult.failed("申请失败");
    }

    @RequestMapping("getApplyingRecord")
    public LayuiResult<List<CallVehicleRecord>> getApplyingRecordByReturnShop(Integer shopId) {
        return LayuiResult.success(callVehicleRecordService.getApplyingRecordByReturnShop(shopId));
    }

    @ExceptionHandler(ApplyBorrowRecordFailedException.class)
    public LayuiResult<?> applyBorrowRecordFailedExceptionHandler(ApplyBorrowRecordFailedException ex) {
        return LayuiResult.failed(ex.getMessage());
    }
}
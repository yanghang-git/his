package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.eunms.CallVehicleState;
import com.his.exception.ApplyBorrowRecordFailedException;
import com.his.mapper.CallVehicleRecordMapper;
import com.his.pojo.CallVehicleRecord;
import com.his.pojo.KpAdmin;
import com.his.service.CallVehicleRecordService;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-30
 *
 * @author yh
 */
@Service
public class CallVehicleRecordServiceImpl extends ServiceImpl<CallVehicleRecordMapper, CallVehicleRecord> implements CallVehicleRecordService {

    @Override
    public List<CallVehicleRecord> getApplyingRecordByReturnShop(Integer resultShop) {
        if (resultShop == null) {
            return Collections.emptyList();
        }
        LambdaQueryWrapper<CallVehicleRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CallVehicleRecord::getBeBorrowCarShop, resultShop);
        wrapper.eq(CallVehicleRecord::getState, CallVehicleState.APPLYING.getValue());
        return list(wrapper);
    }

    @Override
    public boolean addApplyBorrowRecord(CallVehicleRecord record) {
        if (StringUtils.isEmpty(record.getLicensePlateNumber())) return false;

        checkVehicleWhetherBeApplied(record.getLicensePlateNumber(), record.getBorrowCarShop());
        // 将状态设置为申请中
        record.setState(CallVehicleState.APPLYING);
        // 将申请时间设置为当前时间
        record.setBorrowCarTime(LocalDateTime.now());
        // 将申请操作人设置为当前登录用户的Id
        record.setApplyOperator(((KpAdmin)SecurityUtils.getSubject().getPrincipal()).getAdminId());
        return save(record);
    }

    /**
     * 检查车辆是否已经被其他门店所申请 或者已被自己本店所申请   如果有则报错(申请失败，此车辆已被其他门店所申请) 由SpringMVC进行捕捉异常处理。
     *
     * @param licensePlateNumber 车辆 车牌号
     * @param borrowShop         当前申请的门店
     */
    private void checkVehicleWhetherBeApplied(String licensePlateNumber, Integer borrowShop) {
        LambdaQueryWrapper<CallVehicleRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(CallVehicleRecord::getState, CallVehicleState.APPLYING);
        wrapper.eq(CallVehicleRecord::getLicensePlateNumber, licensePlateNumber);
        CallVehicleRecord record = getOne(wrapper);
        if (record != null) {
            if (record.getBorrowCarShop().equals(borrowShop)) {
                throw new ApplyBorrowRecordFailedException("此车辆已被当前门店所申请，无法再次申请");
            } else {
                throw new ApplyBorrowRecordFailedException("申请失败， 此车辆已被其他门店所申请。");
            }
        }
    }
}
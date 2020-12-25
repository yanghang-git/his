package com.his.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.his.mapper.VehicleTypeMapper;
import com.his.pojo.VehicleType;
import com.his.service.VehicleTypeService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class VehicleTypeServiceImpl extends ServiceImpl<VehicleTypeMapper, VehicleType> implements VehicleTypeService {

    @Override
    public Page<VehicleType> searchPageByKeyword(Integer current, Integer size, String keyword) {
        // 创建Page  current 当前第几页 size 每页显示多个条数据
        Page<VehicleType> page = new Page<>(current, size);
        // MybatisPlus 的查询条件对象
        LambdaQueryWrapper<VehicleType> wrapper = new LambdaQueryWrapper<>();
        // 使用like 模糊查询   param1: 判断是否需要添加后方的条件   param2: 字段名称   param3: 判断的值
        // 比如    type_name like   '%demo%'      param2: type_name   param3: demo
        wrapper.like(!StringUtils.isEmpty(keyword), VehicleType::getTypeName, keyword);

        return page(page, wrapper);
    }
}

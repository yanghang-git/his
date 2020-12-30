package com.his.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.his.pojo.RentOut;
import org.apache.ibatis.annotations.Select;

/**
 * Description: TO DO
 * Date: 20-12-29
 *
 * @author yh
 */
public interface RentOutMapper extends BaseMapper<RentOut> {

    @Select("SELECT `odd_numbers` FROM `rent_out` WHERE `odd_numbers` LIKE CONCAT((SELECT DATE_FORMAT(NOW(), '%Y%m%d')), '%') ORDER BY SUBSTRING(`odd_numbers`, 9)+0 DESC LIMIT 1")
    String getLastOddNumbers();

}

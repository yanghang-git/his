package com.his.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.his.pojo.KpAdmin;
import org.apache.ibatis.annotations.Select;

/**
 * Description: TO DO
 * Date: 20-12-10
 *
 * @author yh
 */
public interface KpAdminMapper extends BaseMapper<KpAdmin> {

    @Select("select count(*) from shop inner join kp_admin on kp_admin.admin_shop = shop.shop_id where admin_username = #{loginName}")
    Integer checkAdminOfShopIsExist(String loginName);
}
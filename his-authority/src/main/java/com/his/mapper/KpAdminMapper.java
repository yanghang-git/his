package com.his.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.his.pojo.KpAdmin;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-10
 *
 * @author yh
 */
public interface KpAdminMapper extends BaseMapper<KpAdmin> {

    @Select("select count(*) from shop inner join kp_admin on kp_admin.admin_shop = shop.shop_id where admin_username = #{loginName}")
    Integer checkAdminOfShopIsExist(String loginName);

    @Select("\n" +
            "select a.admin_name from kp_admin a inner join kp_role_admin ra on ra.admin_id = a.admin_id  inner join kp_role r on r.role_id = ra.role_id where admin_shop = #{shopId} and r.role_label = #{employeeName}")
    List<String> selectAdminNameByShopIdAndEmployee(@Param("shopId") Integer shopId, @Param("employeeName") String employeeName);
}
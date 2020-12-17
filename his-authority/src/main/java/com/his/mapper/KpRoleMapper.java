package com.his.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.his.pojo.KpRole;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Description: TO DO
 * Date: 20-12-10
 *
 * @author yh
 */
public interface KpRoleMapper extends BaseMapper<KpRole> {
    @Select("select role_id from kp_role_admin where admin_id = #{userId}")
    List<Integer> getRoleIdByUserId(Integer userId);

    @Select("select role_code from kp_role r inner join kp_role_admin a on a.role_id = r.role_id  where a.admin_id = #{userId}")
    List<String> getROleCodeByUserId(Integer userId);
}

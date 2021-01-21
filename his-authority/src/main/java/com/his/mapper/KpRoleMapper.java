package com.his.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.his.pojo.KpPermRole;
import com.his.pojo.KpRole;
import com.his.pojo.KpRoleAdmin;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
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


    @Insert("<script>" +
            "insert into kp_role_admin(role_id, admin_id) values " +
            "<foreach collection='list' item='roleAdmin' separator=','> (#{roleAdmin.roleId}, #{roleAdmin.adminId})</foreach>" +
            "</script>")
    Boolean insertBatch(ArrayList<KpRoleAdmin> list);

    @Select("select r.role_label from kp_role r inner join kp_role_admin ra on r.role_id = ra.role_id where ra.admin_id = #{adminId}")
    List<String> selectRoleNameByKpAdminId(Integer adminId);
}

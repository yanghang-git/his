package com.his.pojo;


import java.io.Serializable;

public class KpRole implements Serializable {

  private static final long serialVersionUID = 7994577692974204517L;
  private Integer roleId;
  private String roleCode;
  private String roleLabel;

  public KpRole() {}

  public KpRole(Integer roleId, String roleCode, String roleLabel) {
    this.roleId = roleId;
    this.roleCode = roleCode;
    this.roleLabel = roleLabel;
  }

  public Integer getRoleId() {
    return roleId;
  }

  public void setRoleId(Integer roleId) {
    this.roleId = roleId;
  }


  public String getRoleCode() {
    return roleCode;
  }

  public void setRoleCode(String roleCode) {
    this.roleCode = roleCode;
  }


  public String getRoleLabel() {
    return roleLabel;
  }

  public void setRoleLabel(String roleLabel) {
    this.roleLabel = roleLabel;
  }


  @Override
  public String toString() {
    return "KpRole{" +
            "roleId=" + roleId +
            ", roleCode='" + roleCode + '\'' +
            ", roleLabel='" + roleLabel + '\'' +
            '}';
  }
}

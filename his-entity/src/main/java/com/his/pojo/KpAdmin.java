package com.his.pojo;

public class KpAdmin {

  private Integer adminId;
  private String adminUsername;
  private String adminPass;
  private String adminName;
  private Boolean adminSex;
  private String adminPhone;
  private Integer adminShop;

  public KpAdmin() {}

  public KpAdmin(Integer adminId, String adminUsername, String adminPass, String adminName, Boolean adminSex, String adminPhone, Integer adminShop) {
    this.adminId = adminId;
    this.adminUsername = adminUsername;
    this.adminPass = adminPass;
    this.adminName = adminName;
    this.adminSex = adminSex;
    this.adminPhone = adminPhone;
    this.adminShop = adminShop;
  }

  public Integer getAdminId() {
    return adminId;
  }

  public void setAdminId(Integer adminId) {
    this.adminId = adminId;
  }


  public String getAdminUsername() {
    return adminUsername;
  }

  public void setAdminUsername(String adminUsername) {
    this.adminUsername = adminUsername;
  }


  public String getAdminPass() {
    return adminPass;
  }

  public void setAdminPass(String adminPass) {
    this.adminPass = adminPass;
  }


  public String getAdminName() {
    return adminName;
  }

  public void setAdminName(String adminName) {
    this.adminName = adminName;
  }


  public Boolean getAdminSex() {
    return adminSex;
  }

  public void setAdminSex(Boolean adminSex) {
    this.adminSex = adminSex;
  }


  public String getAdminPhone() {
    return adminPhone;
  }

  public void setAdminPhone(String adminPhone) {
    this.adminPhone = adminPhone;
  }


  public Integer getAdminShop() {
    return adminShop;
  }

  public void setAdminShop(Integer adminShop) {
    this.adminShop = adminShop;
  }

  @Override
  public String toString() {
    return "KpAdmin{" +
            "adminId=" + adminId +
            ", adminUsername='" + adminUsername + '\'' +
            ", adminPass='" + adminPass + '\'' +
            ", adminName='" + adminName + '\'' +
            ", adminSex='" + adminSex + '\'' +
            ", adminPhone='" + adminPhone + '\'' +
            ", adminShop=" + adminShop +
            '}';
  }
}




package com.his.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

import java.io.Serializable;
import java.time.LocalDateTime;

public class ReturnVehicleSms implements Serializable {

  private static final long serialVersionUID = -6049084103371573453L;
  @TableId(type = IdType.INPUT)
  private String oddNumbers;
  private String clientName;
  private String shopName;
  private String shopAddress;
  private String vehiclePlateNumber;
  private String cron;
  private LocalDateTime smsTime;

  public ReturnVehicleSms() {}

  public ReturnVehicleSms(String oddNumbers, String clientName, String shopName, String shopAddress, String vehiclePlateNumber, String cron, LocalDateTime smsTime) {
    this.oddNumbers = oddNumbers;
    this.clientName = clientName;
    this.shopName = shopName;
    this.shopAddress = shopAddress;
    this.vehiclePlateNumber = vehiclePlateNumber;
    this.cron = cron;
    this.smsTime = smsTime;
  }

  public String getOddNumbers() {
    return oddNumbers;
  }

  public void setOddNumbers(String oddNumbers) {
    this.oddNumbers = oddNumbers;
  }


  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }


  public String getShopName() {
    return shopName;
  }

  public void setShopName(String shopName) {
    this.shopName = shopName;
  }


  public String getShopAddress() {
    return shopAddress;
  }

  public void setShopAddress(String shopAddress) {
    this.shopAddress = shopAddress;
  }


  public String getVehiclePlateNumber() {
    return vehiclePlateNumber;
  }

  public void setVehiclePlateNumber(String vehiclePlateNumber) {
    this.vehiclePlateNumber = vehiclePlateNumber;
  }


  public String getCron() {
    return cron;
  }

  public void setCron(String cron) {
    this.cron = cron;
  }


  public LocalDateTime getSmsTime() {
    return smsTime;
  }

  public void setSmsTime(LocalDateTime smsTime) {
    this.smsTime = smsTime;
  }

  @Override
  public String toString() {
    return "ReturnVehicleSms{" +
            "oddNumbers='" + oddNumbers + '\'' +
            ", clientName='" + clientName + '\'' +
            ", shopName='" + shopName + '\'' +
            ", shopAddress='" + shopAddress + '\'' +
            ", vehiclePlateNumber='" + vehiclePlateNumber + '\'' +
            ", cron='" + cron + '\'' +
            ", smsTime=" + smsTime +
            '}';
  }
}

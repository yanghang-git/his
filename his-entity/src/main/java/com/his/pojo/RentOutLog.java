package com.his.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;


public class RentOutLog implements Serializable, Cloneable {
  private static final long serialVersionUID = 2364877692974204517L;

  @TableId(type = IdType.INPUT)
  //出租单号
  private String logNumbers;
  //起租时间
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime ofTheTime;
  //预计还车时间
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime predictReturnTime;
  //预计出租价格
  private Double predictRentOut;
  //实际还车时间
  @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
  @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
  private LocalDateTime actualReturnTime;
  //实际收款价格
  private double actualCollectionMoney;
  //车牌号
  private String licensePlateNumber;
  //车辆类型
  private String vehicleType;
  //客户姓名
  private String clientName;
  //客户电话
  private String clientPhone;
  //客户性别
  private Boolean clientSex;
  //问题简介
  private String existingProblem;
  //标明那个门店的出租订单
  private String logShop;
  //问题金额
  private Double compensatePrice;
  //操作者
  private String operator;
  //车辆描述
  private String vehicleDesc;

  public RentOutLog() {
  }


  public RentOutLog(String logNumbers, LocalDateTime ofTheTime, LocalDateTime predictReturnTime, Double predictRentOut, LocalDateTime actualReturnTime, double actualCollectionMoney, String licensePlateNumber, String vehicleType, String clientName, String clientPhone, Boolean clientSex, String existingProblem, String logShop, double compensatePrice, String operator, String vehicleDesc) {
    this.logNumbers = logNumbers;
    this.ofTheTime = ofTheTime;
    this.predictReturnTime = predictReturnTime;
    this.predictRentOut = predictRentOut;
    this.actualReturnTime = actualReturnTime;
    this.actualCollectionMoney = actualCollectionMoney;
    this.licensePlateNumber = licensePlateNumber;
    this.vehicleType = vehicleType;
    this.clientName = clientName;
    this.clientPhone = clientPhone;
    this.clientSex = clientSex;
    this.existingProblem = existingProblem;
    this.logShop = logShop;
    this.compensatePrice = compensatePrice;
    this.operator = operator;
    this.vehicleDesc = vehicleDesc;
  }

  public String getOperator() {
    return operator;
  }

  public void setOperator(String operator) {
    this.operator = operator;
  }

  public String getLogNumbers() {
    return logNumbers;
  }

  public void setLogNumbers(String logNumbers) {
    this.logNumbers = logNumbers;
  }


  public LocalDateTime getOfTheTime() {
    return ofTheTime;
  }

  public void setOfTheTime(LocalDateTime ofTheTime) {
    this.ofTheTime = ofTheTime;
  }


  public LocalDateTime getPredictReturnTime() {
    return predictReturnTime;
  }

  public void setPredictReturnTime(LocalDateTime predictReturnTime) {
    this.predictReturnTime = predictReturnTime;
  }


  public Double getPredictRentOut() {
    return predictRentOut;
  }

  public void setPredictRentOut(Double predictRentOut) {
    this.predictRentOut = predictRentOut;
  }


  public LocalDateTime getActualReturnTime() {
    return actualReturnTime;
  }

  public void setActualReturnTime(LocalDateTime actualReturnTime) {
    this.actualReturnTime = actualReturnTime;
  }


  public double getActualCollectionMoney() {
    return actualCollectionMoney;
  }

  public void setActualCollectionMoney(double actualCollectionMoney) {
    this.actualCollectionMoney = actualCollectionMoney;
  }


  public String getLicensePlateNumber() {
    return licensePlateNumber;
  }

  public void setLicensePlateNumber(String licensePlateNumber) {
    this.licensePlateNumber = licensePlateNumber;
  }


  public String getVehicleType() {
    return vehicleType;
  }

  public void setVehicleType(String vehicleType) {
    this.vehicleType = vehicleType;
  }


  public String getClientName() {
    return clientName;
  }

  public void setClientName(String clientName) {
    this.clientName = clientName;
  }


  public String getClientPhone() {
    return clientPhone;
  }

  public void setClientPhone(String clientPhone) {
    this.clientPhone = clientPhone;
  }


  public Boolean getClientSex() { return clientSex; }

  public void setClientSex(Boolean clientSex) { this.clientSex = clientSex; }


  public String getExistingProblem() {
    return existingProblem;
  }

  public void setExistingProblem(String existingProblem) {
    this.existingProblem = existingProblem;
  }

  public String getLogShop() {
    return logShop;
  }

  public void setLogShop(String logShop) {
    this.logShop = logShop;
  }

  public double getCompensatePrice() {
    return compensatePrice;
  }

  public void setCompensatePrice(double compensatePrice) {
    this.compensatePrice = compensatePrice;
  }

  public String getVehicleDesc() {
    return vehicleDesc;
  }

  public void setVehicleDesc(String vehicleDesc) {
    this.vehicleDesc = vehicleDesc;
  }


  @Override
  public Object clone() throws CloneNotSupportedException {
    return super.clone();
  }


  @Override
  public String toString() {
    return "RentOutLog{" +
            "logNumbers='" + logNumbers + '\'' +
            ", ofTheTime=" + ofTheTime +
            ", predictReturnTime=" + predictReturnTime +
            ", predictRentOut='" + predictRentOut + '\'' +
            ", actualReturnTime=" + actualReturnTime +
            ", actualCollectionMoney=" + actualCollectionMoney +
            ", licensePlateNumber='" + licensePlateNumber + '\'' +
            ", vehicleType='" + vehicleType + '\'' +
            ", clientName='" + clientName + '\'' +
            ", clientPhone='" + clientPhone + '\'' +
            ", clientSex=" + clientSex +
            ", existingProblem='" + existingProblem + '\'' +
            ", logShop=" + logShop +
            ", compensatePrice=" + compensatePrice +
            ", operator='" + operator + '\'' +
            ", vehicleDesc='" + vehicleDesc + '\'' +
            '}';
  }
}

package com.his.pojo;

import java.io.Serializable;

public class RentOutVehicle implements Serializable {

  private static final long serialVersionUID = -1440927629759562400L;
  private String oddNumbers;
  private String vehiclePlateNumber;
  private Double rentOutMoney;

  public RentOutVehicle() {}

  public RentOutVehicle(String oddNumbers, String vehiclePlateNumber, Double rentOutMoney) {
    this.oddNumbers = oddNumbers;
    this.vehiclePlateNumber = vehiclePlateNumber;
    this.rentOutMoney = rentOutMoney;
  }

  public String getOddNumbers() {
    return oddNumbers;
  }

  public void setOddNumbers(String oddNumbers) {
    this.oddNumbers = oddNumbers;
  }

  public String getVehiclePlateNumber() {
    return vehiclePlateNumber;
  }

  public void setVehiclePlateNumber(String vehiclePlateNumber) {
    this.vehiclePlateNumber = vehiclePlateNumber;
  }

  public Double getRentOutMoney() {
    return rentOutMoney;
  }

  public void setRentOutMoney(Double rentOutMoney) {
    this.rentOutMoney = rentOutMoney;
  }

  @Override
  public String toString() {
    return "RentOutVehicle{" +
            "oddNumbers='" + oddNumbers + '\'' +
            ", vehiclePlateNumber=" + vehiclePlateNumber +
            ", rentOutMoney=" + rentOutMoney +
            '}';
  }
}

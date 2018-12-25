package cn.com.hfga.dto.travelexpenses;

import java.util.List;
import java.util.Objects;

import cn.com.hfga.dto.leavel.AddLeaverDTO;

public class TravelAddressDTO {
  private String beginAddress;
  private String endAddress;
  private String vehicle;
  

public String getBeginAddress() {
    return beginAddress;
  }

  public void setBeginAddress(String beginAddress) {
    this.beginAddress = beginAddress;
  }

  public String getEndAddress() {
    return endAddress;
  }

  public void setEndAddress(String endAddress) {
    this.endAddress = endAddress;
  }

  public String getVehicle() {
    return vehicle;
  }

  public void setVehicle(String vehicle) {
    this.vehicle = vehicle;
  }

@Override
public String toString() {
	return "TravelAddressDTO [beginAddress=" + beginAddress + ", endAddress=" + endAddress + ", vehicle=" + vehicle
			+ "]";
}
  
}

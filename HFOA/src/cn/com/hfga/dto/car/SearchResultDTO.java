package cn.com.hfga.dto.car;

public class SearchResultDTO {

	private String Department;
	
	private String AccountRealTime;
	
	private String AccountLength;
	
	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public String getAccountRealTime() {
		return AccountRealTime;
	}

	public void setAccountRealTime(String accountRealTime) {
		AccountRealTime = accountRealTime;
	}

	public String getAccountLength() {
		return AccountLength;
	}

	public void setAccountLength(String accountLength) {
		AccountLength = accountLength;
	}

	public String getCarCode() {
		return CarCode;
	}

	public void setCarCode(String carCode) {
		CarCode = carCode;
	}

	private String CarCode;
}

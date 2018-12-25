package cn.com.hfga.entity.entertain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "B_EntertainAnnualBudget")
public class EntertainAnnualBudgetEntity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "ID")
	private int ID;

	@Column(name = "Department")
	private String Department;

	@Column(name = "BudgetSum0")
	private String BudgetSum0;

	@Column(name = "CopileTime0")
	private String CopileTime0;

	@Column(name = "BudgetSum1")
	private String BudgetSum1;

	@Column(name = "CopileTime1")
	private String CopileTime1;
	
	@Column(name = "BudgetSum2")
	private String BudgetSum2;

	@Column(name = "CopileTime2")
	private String CopileTime2;
	
	@Column(name = "BudgetSum3")
	private String BudgetSum3;

	@Column(name = "CopileTime3")
	private String CopileTime3;
	
	@Column(name = "BudgetSum4")
	private String BudgetSum4;

	@Column(name = "CopileTime4")
	private String CopileTime4;
	
	@Column(name = "BudgetSum5")
	private String BudgetSum5;

	@Column(name = "CopileTime5")
	private String CopileTime5;
	
	@Column(name = "Time")
	private String Time;
	
	@Column(name = "Year")
	private String Year;
	
	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getDepartment() {
		return Department;
	}

	public void setDepartment(String department) {
		Department = department;
	}

	public String getBudgetSum0() {
		return BudgetSum0;
	}

	public void setBudgetSum0(String budgetSum0) {
		BudgetSum0 = budgetSum0;
	}

	public String getCopileTime0() {
		return CopileTime0;
	}

	public void setCopileTime0(String copileTime0) {
		CopileTime0 = copileTime0;
	}

	public String getBudgetSum1() {
		return BudgetSum1;
	}

	public void setBudgetSum1(String budgetSum1) {
		BudgetSum1 = budgetSum1;
	}

	public String getCopileTime1() {
		return CopileTime1;
	}

	public void setCopileTime1(String copileTime1) {
		CopileTime1 = copileTime1;
	}

	public String getBudgetSum2() {
		return BudgetSum2;
	}

	public void setBudgetSum2(String budgetSum2) {
		BudgetSum2 = budgetSum2;
	}

	public String getCopileTime2() {
		return CopileTime2;
	}

	public void setCopileTime2(String copileTime2) {
		CopileTime2 = copileTime2;
	}

	public String getBudgetSum3() {
		return BudgetSum3;
	}

	public void setBudgetSum3(String budgetSum3) {
		BudgetSum3 = budgetSum3;
	}

	public String getCopileTime3() {
		return CopileTime3;
	}

	public void setCopileTime3(String copileTime3) {
		CopileTime3 = copileTime3;
	}

	public String getBudgetSum4() {
		return BudgetSum4;
	}

	public void setBudgetSum4(String budgetSum4) {
		BudgetSum4 = budgetSum4;
	}

	public String getCopileTime4() {
		return CopileTime4;
	}

	public void setCopileTime4(String copileTime4) {
		CopileTime4 = copileTime4;
	}

	public String getBudgetSum5() {
		return BudgetSum5;
	}

	public void setBudgetSum5(String budgetSum5) {
		BudgetSum5 = budgetSum5;
	}

	public String getCopileTime5() {
		return CopileTime5;
	}

	public void setCopileTime5(String copileTime5) {
		CopileTime5 = copileTime5;
	}

	public String getTime() {
		return Time;
	}

	public void setTime(String time) {
		Time = time;
	}

	public String getYear() {
		return Year;
	}

	public void setYear(String year) {
		Year = year;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
}

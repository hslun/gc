package cn.com.hfga.dto.entertain;

public class EntertainAnnualInfoDTO {
	
	private int ID;

	private String Department; // 部门
	
	private String BudgetSum; // 预算金额
	
	private String CopileTime; // 编制时间

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

	public String getBudgetSum() {
		return BudgetSum;
	}

	public void setBudgetSum(String budgetSum) {
		BudgetSum = budgetSum;
	}

	public String getCopileTime() {
		return CopileTime;
	}

	public void setCopileTime(String copileTime) {
		CopileTime = copileTime;
	}

	public EntertainAnnualInfoDTO() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "EntertainAnnualInfoDTO [ID=" + ID + ", Department=" + Department + ", BudgetSum=" + BudgetSum
				+ ", CopileTime=" + CopileTime + "]";
	}

	public EntertainAnnualInfoDTO(int iD, String department, String budgetSum, String copileTime) {
		super();
		ID = iD;
		Department = department;
		BudgetSum = budgetSum;
		CopileTime = copileTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((BudgetSum == null) ? 0 : BudgetSum.hashCode());
		result = prime * result + ((CopileTime == null) ? 0 : CopileTime.hashCode());
		result = prime * result + ((Department == null) ? 0 : Department.hashCode());
		result = prime * result + ID;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		EntertainAnnualInfoDTO other = (EntertainAnnualInfoDTO) obj;
		if (BudgetSum == null) {
			if (other.BudgetSum != null)
				return false;
		} else if (!BudgetSum.equals(other.BudgetSum))
			return false;
		if (CopileTime == null) {
			if (other.CopileTime != null)
				return false;
		} else if (!CopileTime.equals(other.CopileTime))
			return false;
		if (Department == null) {
			if (other.Department != null)
				return false;
		} else if (!Department.equals(other.Department))
			return false;
		if (ID != other.ID)
			return false;
		return true;
	}
	
	
	
}

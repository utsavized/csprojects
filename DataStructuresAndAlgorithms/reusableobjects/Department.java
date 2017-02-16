package reusableobjects;

public enum Department {
	SoftwareEngineering (1, "Software Engineering"),
	Finance (2, "Finance"),
	Marketing (3, "Marketing"),
	Sales (4, "Sales"),
	Billing (5, "Billing"),
	CustomerService (6, "Customer Service");
	
	private final int deptNum;
	private final String deptName;
	Department(int deptNum, String deptName) {
		this.deptNum = deptNum;
		this.deptName = deptName;
	}
	
	public int getDeptNumber() {
		return this.deptNum;
	}

	public String getDeptName() {
		return deptName;
	}
}

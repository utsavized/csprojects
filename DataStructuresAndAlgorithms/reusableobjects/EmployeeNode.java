package reusableobjects;

public class EmployeeNode implements Comparable<EmployeeNode>{
	public Employee employee;
	public EmployeeNode left;
	public EmployeeNode right;
	public EmployeeNode(Employee employee) {
		this.employee = employee;
		this.left = null;
		this.right = null;
		
	}

		/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((employee == null) ? 0 : employee.hashCode());
		result = prime * result + ((left == null) ? 0 : left.hashCode());
		result = prime * result + ((right == null) ? 0 : right.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof EmployeeNode))
			return false;
		EmployeeNode other = (EmployeeNode) obj;
		if (employee == null) {
			if (other.employee != null)
				return false;
		} else if (!employee.equals(other.employee))
			return false;
		if (left == null) {
			if (other.left != null)
				return false;
		} else if (!left.equals(other.left))
			return false;
		if (right == null) {
			if (other.right != null)
				return false;
		} else if (!right.equals(other.right))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return employee.toString();
	}

	@Override
	public int compareTo(EmployeeNode o) {
		if(this.employee.getSalary() > o.employee.getSalary())
			return 1;
		else if(this.employee.getSalary() < o.employee.getSalary())
			return -1;
		else
			return 0;
	}

	
	
}

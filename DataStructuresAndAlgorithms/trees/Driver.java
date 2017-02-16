package trees;

import java.util.LinkedList;

import reusableobjects.TreeNode;
import reusableobjects.Department;
import reusableobjects.Employee;
import reusableobjects.EmployeeNode;
import trees.customtrees.EmployeeBST;


public class Driver {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EmployeeBST tree = new EmployeeBST(new EmployeeNode(new Employee(1, "Utsav", null, "Pandey", Department.SoftwareEngineering, 120000))); 
		tree.insert(new EmployeeNode(new Employee(2, "Andrea", null, "Tobon", Department.Finance, 100000)));
		tree.insert(new EmployeeNode(new Employee(3, "John", "Kevin", "Smith", Department.CustomerService, 50000)));
		tree.insert(new EmployeeNode(new Employee(4, "Elon", null, "Musk", Department.SoftwareEngineering, 650000)));
		tree.insert(new EmployeeNode(new Employee(5, "Steve", null, "Jobs", Department.SoftwareEngineering, 5000000)));
		tree.insert(new EmployeeNode(new Employee(6, "Jeff", null, "Bezos", Department.SoftwareEngineering, 1500000)));
		tree.insert(new EmployeeNode(new Employee(7, "David", null, "Holmes", Department.Marketing, 72000)));
		tree.insert(new EmployeeNode(new Employee(8, "Mark", null, "Jones", Department.Sales, 88000)));
		
/*		System.out.println("Tree:");
		tree.listBySalary();
		
		System.out.println("");*/
		
	/*	HashMap<Integer, LinkedList<EmployeeNode>> hM = tree.createLinkedLists();
		LinkedList<EmployeeNode> list = hM.get(2);
		ListIterator<EmployeeNode> i = list.listIterator();
		while(i.hasNext()) {
			System.out.println(i.next().toString());
		}*/
		
		
		/*System.out.println(tree.isBalanced());*/
		
		/*System.out.println("Height - " + tree.height(tree.getRoot()));
		
		EmployeeNode find = tree.search(new Employee(1, "Utsav", null, "Pandey", Department.SoftwareEngineering, 120000));
		
		System.out.println("");
		if(find != null)
			System.out.println("Find: " + find.employee.toString());
		
		
		tree.remove(new Employee(8, "Mark", null, "Jones", Department.Sales, 88000));
		tree.remove(new Employee(7, "David", null, "Holmes", Department.Marketing, 72000));
		tree.remove(new Employee(6, "Jeff", null, "Bezos", Department.SoftwareEngineering, 1500000));		

		System.out.println("Tree:");
		tree.listBySalary();
		
		
		System.out.println("Height - " + tree.height(tree.getRoot()));*/
		/*tree.remove(new Employee(1, "Utsav", null, "Pandey", Department.SoftwareEngineering, 120000));
		
		System.out.println("Tree:");
		tree.listBySalary();
		*/
		
		/*LinkedList<Employee> l = makeLinkedList(tree);
		
		Iterator<Employee> i = l.iterator();
		
		while(i.hasNext()) {
			System.out.println(i.next());
		}
		*/
		

		
		
			
	}
	
	public static LinkedList<Employee> makeLinkedList(EmployeeBST t) {
		LinkedList<Employee> list = new LinkedList<Employee>();
		EmployeeNode root = t.getRoot();
		return inorder(root, list);
	}
	
	public static LinkedList<Employee> inorder(EmployeeNode root, LinkedList<Employee> head) {
		if(root.left == null && root.right == null) {
			head.add(root.employee);
			return head;
		}
		
		if(root.left != null)
			head = inorder(root.left, head);
		
		if(root.right != null)
			head = inorder(root.right, head);
		
		return head;
	}
		
		

}

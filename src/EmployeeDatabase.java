///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  InteractiveDBTes
// File:             EmployeeDatabase.java
// Semester:         
//
// Author:           Xingmin Zhang xzhang66@wisc.edu
// CS Login:         (your login name)
// Lecturer's Name:  (name of your lecturer)
// Lab Section:      (your lab section number)
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   fully acknowledge and credit all sources of help,
//                   other than Instructors and TAs.
//
// Persons:          None
//                   
//
// Online sources:   none
//////////////////////////// 80 columns wide //////////////////////////////////



import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeeDatabase {
	
	private ArrayList<Employee> employeeList; 


	public EmployeeDatabase() {
		this.employeeList = new ArrayList<>();
	}
	
	/**
	 * Add an employee with the given username e to the end of the database. 
	 * If an employee with username e is already in the database, just return.
	 * @param a string of name
	 */
	
	public void addEmployee(String e) {
		if (!this.containsEmployee(e)) {
			employeeList.add(new Employee(e));
		}
	}
	
	/**
	 * Add the given destination d to the wish list for employee e in the database. 
	 * If employee e is not in the database throw a java.lang.IllegalArgumentException. 
	 * If d is already in the wish list for employee e, just return.
	 * @param e: employee name
	 * @param d: a destination to be added to the wishlist of Employee e
	 * @throws IllegalArgumentException
	 */
	void addDestination(String e, String d) throws IllegalArgumentException {
		if (!this.containsEmployee(e)) {
			throw new IllegalArgumentException();
		} else {
			if (!this.hasDestination(e, d)) {
				getEmployee(e).getWishlist().add(d);
			}
		}
	}
	
	
	/**
	 * A method to check whether Employee e is already in the database 
	 * @param e: a name to check
	 * @return true if and only if employee e is in the database.
	 */
	public boolean containsEmployee(String e) {
		boolean found = false;
		Iterator<Employee> itr = employeeList.iterator();
		while (itr.hasNext()) {
				Employee next = itr.next();
				if(next.getUsername().equals(e)) {
					found = true;
					break;
			}
		}
		return found;
	}
	
	/**
	 * A method to check whether a destination is contained in the database.
	 * @param d: a string destination
	 * @return Return true if and only if destination d appears in at least one employee's 
	 * wish list in the database.
	 */
	boolean containsDestination(String d) {
		boolean found = false;
		Iterator<Employee> itr = this.iterator();
		while (itr.hasNext()) {
			Employee employee = itr.next();
			if (hasDestination(employee, d)) {
				found = true;
				break;
			}
		}
		return found;
	}
	
	
	/**
	 * A method to determine whether an employee has a certain destination in his/her
	 * wishlist
	 * @param e: Employee name
	 * @param d: a destination to check
	 * @return true if and only if destination d is in the wish list for employee e. 
	 * If employee e is not in the database, return false.
	 */
	public boolean hasDestination(String e, String d) {
		boolean found = false;
		if(this.containsEmployee(e)) {
			Iterator<String> itr = this.getEmployee(e).getWishlist().iterator();
			while (itr.hasNext()) {
				if(itr.next().equals(d)) {
					found = true;
					break;
				}
			}
		}
		return found;
	}
	
	/**
	 * A polymorphic method of the above. Instead of using employee name, this method
	 * takes in an employee object and checks whether it has a destination in his/her
	 * wish lists.  
	 * @param employee: an employee
	 * @param d: a destination
	 * @return true if the employee has d in his/her wish lists. 
	 */
	private boolean hasDestination(Employee employee, String d) {
		Iterator<String> itr = employee.getWishlist().iterator();
		boolean found = false;
		while (itr.hasNext()) {
			String wish = itr.next();
			if (wish.equals(d)) {
				found = true;
				break;
			}
		}
		return found;
	}
	
	/**
	 * A method to get a list of employee names who have a certain destination in the
	 * wish lists. 
	 * @param d: destination 
	 * @return the list of employees who have destination d in their wish list. 
	 * If destination d is not in the database, return a null list.
	 */
	List<String> getEmployees(String d) {
		ArrayList<String> newList = new ArrayList<>();
		Iterator<Employee> itr = this.iterator();
		while (itr.hasNext()) {
			Employee employee = itr.next();
			if (hasDestination(employee, d)) {
				newList.add(employee.getUsername());
			}
		}
		if(newList.isEmpty()) {
			newList = null;
		}
		return newList;
		
	}
	
	/**
	 * A method to get a list of destinations in an employee's wish list. 
	 * @param e
	 * @return the wish list for the employee e. 
	 * If an employee e is not in the database, return null.
	 */
	List<String> getDestinations(String e) {
		if(this.containsEmployee(e)) {
			return getEmployee(e).getWishlist();
		} else {
			return null;
		}
	}
	
	/**
	 * An iterator of employees
	 * @return an iterator of employees
	 */
	public Iterator<Employee> iterator() {
		return employeeList.iterator();
	}
	//NOT SURE
	
	
	/**
	 * A method to remove an employee
	 * @param e: name of employee to remove
	 * @return true if the employee is removed; if the employee is not found, 
	 * return false
	 */
	boolean removeEmployee(String e) {
		if(containsEmployee(e)) {
			employeeList.remove(getEmployee(e));
			return true;
		} else {
			return false;
		}
	}
	
	
	/**
	 * A method to remove a destination from the everyone's wish list. 
	 * @param d: a destination to remove
	 * @return ture is the destination is removed; if the destination is not found
	 * in the database, return false.
	 */
	boolean removeDestination(String d) {
		if (!containsDestination(d)) {
			return false;
		} else {
			Iterator<Employee> itr = this.iterator();
			while (itr.hasNext()) {
				Employee employee = itr.next();
				if (this.hasDestination(employee, d)) {
					employee.getWishlist().remove(d);
				}
			}
			return true;
		}
	}
	
	/**
	 * A method to get the number of employees in the database. 
	 * @return the number of employees in this database.
	 */
	int size() {
		return employeeList.size();
	}
	
	
	/**
	 * A helper method to return employee with a name e
	 * @param e
	 * @return
	 */
	private Employee getEmployee(String e) {
		Iterator<Employee> itr = employeeList.iterator();
		Employee employee = null;
		while (itr.hasNext()) {
				Employee next = itr.next();
				if(next.getUsername().equals(e)) {
					employee = next;
					break;
			}
		}
		return employee;
	}


}

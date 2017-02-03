///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            CS367 Assignment 1
// Files:            Employee.java, EmployeeDatabase.java
// Semester:         
//
// Author:           Xingmin Zhang 
// Email:            xzhang66@wisc.edu
// CS Login:         
// Lecturer's Name:  
// Lab Section:      
//
//////////////////// STUDENTS WHO GET HELP FROM OTHER THAN THEIR PARTNER //////
//                   must fully acknowledge and credit those sources of help.
//                   Instructors and TAs do not have to be credited here,
//                   but tutors, roommates, relatives, strangers, etc do.
//
// Persons:          None
//
// Online sources:   None
//////////////////////////// 80 columns wide //////////////////////////////////


import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class InteractiveDBTester {
    public static void main(String[] args) {
   	
   	 //if argument is not exactly 1 line, print out a message and quit
   	 if(args.length != 1) {
   		 System.out.println("Please provide input file as command-line argument");
   	 
   	 //otherwise, use the argument to open a file and construct an 
       //employee database.
   	 } else {
   		 //use the argument as the name of a file, and check whether it exits
   		 File file = new File(args[0]);
   		 //if the file exits, open the file and parse the texts
   		 if (file.exists()) {
   			 try {
					Scanner inFile = new Scanner(file);
					//Create a database called employeeDatabase
					EmployeeDatabase employeeDatabase = new EmployeeDatabase();
					while (inFile.hasNext()) {
						String newLine = inFile.nextLine().toLowerCase();
						String[] strs = newLine.split(",");
						//The first word of a line is the name of an employee
						employeeDatabase.addEmployee(strs[0]);
						//The rest words are destinations
						for (int i = 1; i < strs.length; i++) {
							employeeDatabase.addDestination(strs[0], strs[i]);
						}
					}

					//After constructing a database, prompt users to operate on 
					//the database
				  Scanner stdin = new Scanner(System.in);  // for reading console input
		        printOptions();
		        boolean done = false;
		        while (!done) {
		            System.out.print("Enter option ( dfhisqr ): ");
		            String input = stdin.nextLine();
		            input = input.toLowerCase();  // convert input to lower case

		            // only do something if the user enters at least one character
		            if (input.length() > 0) {
		                char choice = input.charAt(0);  // strip off option character
		                String remainder = "";  // used to hold the remainder of input
		                if (input.length() > 1) {
		                    // trim off any leading or trailing spaces
		                    remainder = input.substring(1).trim();
		                }

		                switch (choice) {
		                
		                case 'd':
		                   //discontinue a destination from everyone's wish list
		               	 discontinueDestination(employeeDatabase, remainder);
		                    break;


		                case 'f':
		                   //find an employee with the name stored in remainder 
		               	 findEmployee(employeeDatabase, remainder);
		                    break;

		                case 'h': 
		                    printOptions();
		                    break;

		                case 'i':
		                   //print a summery of the database
		               	 printInfor(employeeDatabase);
		                    break;
		                    
		                case 's':
		                   //show all the employees who have a destination in
		               	 //their wish lists
		               	 showDestination(employeeDatabase, remainder);
		                    break;

		                case 'q':
		                    done = true;
		                    System.out.println("quit");
		                    break;

		                case 'r':
		                   //remove an employee with the specified name 
		               	 removeEmployee(employeeDatabase, remainder);
		                    break;

		                default:  // ignore any unknown commands
		                    break;
		                }
			            }
			        }
			        
			        stdin.close();
			      
			        
				} catch (FileNotFoundException e) {
					//Should I print this or other messages?
					//It won't happen because I have checked file
					System.out.println("Error: Cannot access input file");
					e.printStackTrace();
				}
   		//if the argument is not 1, print out an error message and quit	 
   		 } else {
   			 System.out.println("Error: Cannot access input file");
   		 }
   	 }

        
    }

    /**
     * Prints the list of command options along with a short description of
     * one.  This method should not be modified.
     */
    private static void printOptions() {
         System.out.println("d <destination> - discontinue the given <destination>");
         System.out.println("f <Employee> - find the given <Employee>");
         System.out.println("h - display this help menu");
         System.out.println("i - display information about this Employee database");
         System.out.println("s <destination> - search for the given <destination>");
         System.out.println("q - quit");
         System.out.println("r <Employee> - remove the given <Employee>");

    }
    
    /**
     * A method to print out summary information of a database
     * @param employeeDatabase: the database to present summary information of
     */
    private static void printInfor(EmployeeDatabase employeeDatabase) {
   	 int maxDestinationsPerEmployee = 0; 
   	 int minDestinationsPerEmployee = 0;
   	 int totalDestinations = 0; //add everyone's count of destinations
   	 float aveDestinationPerEmployee = 0;
   	
   	 int maxEmployeesPerDestination = 0; //for each destination, what 
   	                     //is the max number of employees choosing it
   	 int minEmployeesPerDestination = 0;
   	 int totalEmployees = 0; //add each destination's count of 
   	                      //employees that list it in their wish lists
   	 float aveEmployeePerDestination = 0;
   	 
   	 //to store unique wishes in the database
   	 ArrayList<String> uniqueWishes = new ArrayList<>();
   	 //to store the msot popular wishes in the database
   	 ArrayList<String> mostPopularWishes = new ArrayList<>();
   	 //convert the most popular wishes into one string for printing
   	 String mostPopularDestinations = "";
   	 
   	 //if the database is not empty, loop through the database and 
   	 //calculate all the summary information
   	 if (employeeDatabase.size() != 0) {
      	 Iterator<Employee> itr = employeeDatabase.iterator();
      	 Employee firstEmployee = itr.next();
      	 //initialize the max and min number of destinations per 
      	 //employee to the first employee
      	 maxDestinationsPerEmployee = firstEmployee.getWishlist().size();
      	 minDestinationsPerEmployee = firstEmployee.getWishlist().size();
      	 totalDestinations = firstEmployee.getWishlist().size();
      	 
      	 //loop through the database, if one employee has more wishes,
      	 //put the number of wishes to maxDestinationsPerEmployee
      	 //if one employee has fewer wishes, put the number of wishes to
      	 //minDestinationPerEmployee
      	 //keep counting total number of wishes of all employees
      	 while(itr.hasNext()) {
      		 Employee employee = itr.next();
      		 Iterator<String> itrDestination = employee.getWishlist().iterator();
      		 while(itrDestination.hasNext()) {
      			 String uniq = itrDestination.next();
      			 if(!uniqueWishes.contains(uniq)) {
      				 uniqueWishes.add(uniq);
      			 }
      		 }
      		 if (employee.getWishlist().size() > maxDestinationsPerEmployee) {
      			 maxDestinationsPerEmployee = employee.getWishlist().size();
      		 }
      		 if (employee.getWishlist().size() < minDestinationsPerEmployee) {
      			 minDestinationsPerEmployee = employee.getWishlist().size();
      		 }
      		 totalDestinations += employee.getWishlist().size(); 
      	 }
      	//calculate average number of destinations per employee 
      	aveDestinationPerEmployee = 
      			totalDestinations / (float)employeeDatabase.size();
      	 
      	//If there are more than one unique destinations, initialize 
      	//the max and min employees per destination, total count of 
      	//employees choosing all destinations to the value for the first
      	//destination; put the first destination to the most popular
      	//wishes list
      	 Iterator<String> itrUniqueDestination = uniqueWishes.iterator();
      	 if (itrUniqueDestination.hasNext()) {
      		 String firstDestination = itrUniqueDestination.next();
      		 int numEmployeesOfFirstDestination = 
      				 employeeDatabase.getEmployees(firstDestination).size();
      		 maxEmployeesPerDestination = numEmployeesOfFirstDestination; 
         	 minEmployeesPerDestination = numEmployeesOfFirstDestination;
         	 totalEmployees = numEmployeesOfFirstDestination;
         	 mostPopularWishes.add(firstDestination);
      	 }
      	 
       	//loop through the unique destinations, if one destination
       	//has more employees choosing it, put the number of employees 
       	//to maxEmployeesPerDestination and put the destination to the 
       	//list of mostPopularWishes; if one destination has fewer 
       	//employees choosing it, put the number of employees to 
       	//minEmployeesPerDestination
      	 while (itrUniqueDestination.hasNext()) {
      		 String destination = itrUniqueDestination.next();
      		 int numEmployee = employeeDatabase.getEmployees(destination).size();
      		 if (numEmployee > maxEmployeesPerDestination) {
      			 maxEmployeesPerDestination = numEmployee;
      			 mostPopularWishes = new ArrayList<>();
      			 mostPopularWishes.add(destination);
      		 }
      		 if (numEmployee < minEmployeesPerDestination) {
      			 minEmployeesPerDestination = numEmployee;
      		 }
      		 totalEmployees += numEmployee;
      	 }
      	 //calculate the average number of employees for each destination
      	 if(uniqueWishes.size() != 0) {
      		 aveEmployeePerDestination = 
      				 totalEmployees / (float)uniqueWishes.size();
      	 }
      		 
      	 //put the destinations in the most popular list into one string
      	Iterator<String> itrMostPopularDestinations = 
      			mostPopularWishes.iterator();
      	while(itrMostPopularDestinations.hasNext()) {
      		mostPopularDestinations += (itrMostPopularDestinations.next() + " ");
      	} 	
   	 }
   	 
   	 //print out a summary information of the database
   	 System.out.println("Employees: " + employeeDatabase.size() +
   			 " Destinations: " + uniqueWishes.size());
   	 System.out.printf("# of destinations/employee: most %d, "
   	 		+ "least %d, average %.1f\n", 
   	 		maxDestinationsPerEmployee, minDestinationsPerEmployee, 
   	 		aveDestinationPerEmployee);
   	 System.out.printf("# of employees/destination: most %d, "
   	 		+ "least %d, average %.1f\n", 
   			 maxEmployeesPerDestination, minEmployeesPerDestination, 
   			 aveEmployeePerDestination);
   	 System.out.printf("Most popular destination: %s [%d]\n", 
   			 mostPopularDestinations.trim(), maxEmployeesPerDestination);;
    }
    
    /**
     * A method to discontinue a destination from everyone's list
     * @param employeeDatabase
     * @param destination
     */
    private static void discontinueDestination(EmployeeDatabase employeeDatabase, String destination) {
   	 if(!employeeDatabase.containsDestination(destination)) {
   		 System.out.println("destination not found");
   	 } else {
   		 employeeDatabase.removeDestination(destination);
   	 }
    }
    
    /**
     * A method to show an employee in a database
     * @param employeeDatabase: the database to find employee from
     * @param name: name of employee to look for
     */
    private static void findEmployee(EmployeeDatabase employeeDatabase, String name) {
   	 if(!employeeDatabase.containsEmployee(name)) {
   		 System.out.println("employee not found");
   		 //if employee is found, print out the name of the employee 
   		 //and his/her wishes
   	 } else {
   		 System.out.print(name + ":");
   		 if (employeeDatabase.getDestinations(name) instanceof ArrayList) {
   			 ArrayList<String> destinations = 
   					 (ArrayList<String>) employeeDatabase.getDestinations(name);
   			 Iterator<String> itr = destinations.iterator();
   			 if (itr.hasNext()) {
   				 System.out.print(itr.next());
   			 }
   			 while (itr.hasNext()) {
   				 System.out.print("," + itr.next());
   			 }
   			 System.out.println("");
   		 }
   	 }
    }
    
    /**
     * A method to show employees who have a particular destination
     * in his/her wish list
     * @param employeeDatabase: the database to look destinations from
     * @param destination: the destination to look up
     */
    private static void showDestination(EmployeeDatabase employeeDatabase, String destination) {
   	 if(!employeeDatabase.containsDestination(destination)) {
   		 System.out.println("destination not found");
   		 //if the destination is found, print out all the employees
   		 //that have the destination in his/her wish list
   	 } else {
   		 ArrayList<String> employees = 
   				 (ArrayList<String>) employeeDatabase.getEmployees(destination);
   		 System.out.print(destination + ":" + employees.get(0));
   		 for (int i = 1; i < employees.size(); i++) {
   			 System.out.print("," + employees.get(i));
   		 }
   		 System.out.println("");
   	 }
    }
    
    /**
     * A method to remove an employee from the database
     * @param employeeDatabase: the database to remove employee from
     * @param name: name of employee to remove
     */
    private static void removeEmployee(EmployeeDatabase employeeDatabase, String name) {
   	 if (employeeDatabase.containsEmployee(name)) {
   		 employeeDatabase.removeEmployee(name);
   	 } else {
   		 System.out.println("employee not found");
   	 }
    }
}

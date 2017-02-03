///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  InteractiveDBTes
// File:             Employee.java
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



import java.util.*;
/**
 * The Employee class is used to represent an employee. It keeps track of a 
 * username and a wish list of destinations.
 * 
 * @author Beck Hasti/Charles Fischer, CS 367 instructor, copyright 2014-2016
 */
public class Employee {
    private String username;          // the employee's username      
    private List<String> wishlist;    // the wish list of destinations
    
    /**
     * Constructs an employee with the given username and an empty wish list.
     * 
     * @param name the username of this employee
     */
    public Employee(String name)     {
        username = name;
        wishlist = new ArrayList<String>();
    }
    
    /**
     * Return the username of this employee.
     * 
     * @return the username of the employee
     */
    public String getUsername() { 
        return username;
    }
    
    /**
     * Return the wish list of destinations for this employee.
     * 
     * @return the wish list of destinations
     */
    public List<String> getWishlist() {
        return wishlist;
    }
}

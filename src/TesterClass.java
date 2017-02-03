import java.util.ArrayList;

public class TesterClass {
	public static void main (String[] args) {
		EmployeeDatabase testDatabase = new EmployeeDatabase();
		testDatabase.addEmployee("AaronZ");
		testDatabase.addEmployee("SharonJ");
		System.out.println("testDatabse size is " + testDatabase.size());
		
		testDatabase.addDestination("AaronZ", "MSN");
		testDatabase.addDestination("AaronZ", "ORD");
		testDatabase.addDestination("SharonJ", "MSN");
		try {
			testDatabase.addDestination("Sharon", "ORD");
		} catch (Exception e) {
			System.out.println("not able to add destination");
		}
		
		System.out.println("Contains AaronZ? " + testDatabase.containsEmployee("AaronZ"));
		System.out.println("Contains Aaron? " + testDatabase.containsEmployee("Aaron"));
		System.out.println("Contains MSN? " + testDatabase.containsDestination("MSN"));
		System.out.println("Contains MS? " + testDatabase.containsDestination("MS"));
		
		ArrayList<String> newlist = new ArrayList<>();
	
		newlist = (ArrayList<String>) testDatabase.getEmployees("MSN");
		System.out.println("The follow employees listed MSN in the wishes: ");
		System.out.println(newlist.get(0) + " " + newlist.get(1));
		
		newlist = (ArrayList<String>) testDatabase.getDestinations("AaronZ");
		System.out.println("The follow destinations for AaronZ: ");
		System.out.println(newlist.get(0) + " " + newlist.get(1));
		
		
		System.out.println("Remove employee Aaron. Success?" + testDatabase.removeEmployee("Aaron"));
		System.out.println("testDatabse size is " + testDatabase.size());
		
		//System.out.println("Remove employee AaronZ. Success?" + testDatabase.removeEmployee("AaronZ"));
		//System.out.println("testDatabse size is " + testDatabase.size());
		newlist = (ArrayList<String>) testDatabase.getDestinations("AaronZ");
		System.out.println("AaronZ's wishes: " + newlist.size());
		System.out.println("Remove Destination MSN. Success?" + testDatabase.removeDestination("MSN"));
		newlist = (ArrayList<String>) testDatabase.getDestinations("AaronZ");
		System.out.println("AaronZ's wishes: " + newlist.size());
		
		}
}

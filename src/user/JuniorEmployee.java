package user;

import java.util.Calendar;

public class JuniorEmployee extends Employee {
	
	public JuniorEmployee(int eid, String username, String name, int age, Calendar dateJoined, int annualLeavesApplied) {
		super(eid, username, name, age, dateJoined, annualLeavesApplied);
	}
	
	public void cancelList(int leaveID) {
		//Connect to database
		//Find the record in the database using the eid, pw
		//Using the leaveID to find out the leave object and delete it from the Department's pending list
	}
	
	public void viewLeaveRecord() {
		//connect to database
		//find out all the record with the same eid and pw
		//List out all the records
	}
	
	public boolean isSenior() {
		return false;
	}

	@Override
	public final int getEntitledAnnualLeaves() {
		return 17;
	}
}

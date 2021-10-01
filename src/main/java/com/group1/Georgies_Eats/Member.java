/*	EKT
 * 	This is the Member class or my MODEL
 */

package com.group1.Georgies_Eats;

public class Member {
	
	private String MemberName;
	private int MemberID;
	private int NumberReviews;
	
	/* constructor w params */
	public Member(String name, int mid) {
		this.MemberName = name;
		this.MemberID = mid;
		// always initialize NumberReviews to 0
		this.NumberReviews = 0;
		Logger.log("Created new Member");
		
		//Once created Member, need to add them to the database
		MembersList.AddMember(this);
	}

	/* Getters -- immutable so no setters
	 */	
	
	public String GetMemberName() { 
		return MemberName; 
	}
	public int GetMemberID() { 
		return MemberID; 
	}
	public int GetNumberReviews() { 
		this.UpdateMember();
		return NumberReviews; 
	}

	/*
	 * update the number of reviews - check if there's a change
	 */
	public void UpdateMember() {
		//calling Isaac's Review microservice to get this information
		int temp = ReviewMicroservice.getNumReviewsByMember(this.MemberID);
		//only update if a change occurs
		if (temp != NumberReviews) {
			Logger.log("Member's review count changed - need to update");
			NumberReviews = temp;
			//also need to update the database
			MembersList.UpdateMember(this);
		}
		Logger.log("no update needed for Member");
	}
	
	@Override
	public String toString() {
		String temp = "Name: ";
			temp += MemberName;
			temp += "\nmemberID: ";
			temp += MemberID;
			temp += "\nNumber of Reviews: ";
			temp += NumberReviews;
		return temp;
	}
}


// FIXME - I think the issue is that thymeleaf needs strings so
// I am just adding a quick wrapper class to this to make it 
// easier to get and set the string equivalents of the Member attributes - IS

/* EKT:
 * Because I want to keep everything immutable, setting the wrapper to Current Member
 * instead of a new member class - this will keep things from being updated by unauth users.
 */

class MemberWrapper {
	
	public Member mem;
	
	//is current member
	public MemberWrapper() { this.mem = MemberMicroservice.getCurrentMember(); }
	public MemberWrapper(Member m) { mem = m; }
	
	
	// String constructors
	public String getMemberName() { return mem.GetMemberName(); }
	public String getMemberID() { return String.valueOf(mem.GetMemberID()); }
	public String getNumberReviews() { return String.valueOf(mem.GetNumberReviews()); }
	//public void setMemberName(String MemberName) { mem.setMemberName(MemberName); }
	//public void setMemberID(String MemberID) { 
		//try {mem.setMemberID(Integer.parseInt(MemberID));}
		//catch(Exception e) { return; }
	//}
	//public void setNumberReviews(String NumberReviews) { 
		//try {mem.setNumberReviews(Integer.parseInt(NumberReviews));}
		//catch(Exception e) { return; }
	//}
	
	@Override 
	public String toString() { return mem.toString(); }
}


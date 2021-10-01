/*
 * EKT
 * This is the Member's mock database
 * 		stores all the created Members
 * 		can pull up a saved Member and their Personal Information
 */

package com.group1.Georgies_Eats;

import java.util.ArrayList;

public class MembersList {

	private static ArrayList<Member> members = new ArrayList<Member>();
	
	// Current member
	private static Member curr;
	
	public MembersList() {}
	
	/* 
	 * Get all of the Members
	 */
	public static ArrayList<Member> GetAllMembers() {
		return members;
	}
	
	/*
	 * Set current user
	 */
	protected static void setCurr(Member m) {
		curr = m;
		Logger.log("updated current user");
	}
	/*
	 * Get current user
	 */
	protected static Member getCurr() {
		return curr;
	}
	
	
	/*
	 * Get just one Member based on ID\
	 * 
	 * @params:	member id to search
	 * @return:	Member if found
	 * 			else null
	 */
	public static Member GetMember(int mid) {
		for (Member m : members) {
			if (m.GetMemberID() == mid) {
				Logger.log("Found member from ID");
				return m;
			}
		}	
		//if we got here, then didn't find the member
		Logger.log("Didn't find a match for member from ID");
		return null;
	}
	
	/* 
	 * Get just one Member based on Name
	 * @params:	member name to search
	 * @return:	Member if found
	 * 			else null
	 */
	public static Member GetMember(String name) {
		for (Member m : members) {
			if (m.GetMemberName() == name) {
				Logger.log("Found member from Name");
				return m;
			}
		}	
		//if we got here, then didn't find the member
		Logger.log("Didn't find a match for member from Name");
		return null;
	}
	
	/*
	 * Add a new Member upon creation - append to arraylist
	 * @params: new Member object to add
	 */
	public static void AddMember(Member m) {
		members.add(m);
		Logger.log("added new member to list");
		System.out.println("added member: " + m.GetMemberName());
	}
	
	/*
	 * Remove a Member form the ArrayList
	 */
	public static void RemoveMember(Member m) {
		members.remove(m);
		Logger.log("removed member from list");
	}
	
	/*
	 * Update the Member if a change occurred
	 */
	public static void UpdateMember(Member m) {
		for (Member ms : members) {
			if (ms.equals(m)) {
				RemoveMember(ms);
				AddMember(m);
				Logger.log("updated member");
				return;
			}
		}
		//if reached here then didn't find member - need to add them
		Logger.log("no Member found - need to add them to list instead");
		AddMember(m);
	}
	
}

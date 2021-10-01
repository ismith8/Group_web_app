/* EKT
 * This is the Member's Microservice
 * 		it handles getting the information to and from the database & Model
 */

package com.group1.Georgies_Eats;

import java.util.ArrayList;

import org.springframework.stereotype.Controller;

@Controller
public class MemberMicroservice {

	// Beth microservices: 
		// get a member
		// get multiple members	

	public MemberMicroservice() {}
	
	/* 
	 * Call member from database based on ID
	 */
	protected static Member getMember(int mid) {
		Logger.log("requested single member based on ID");
		return MembersList.GetMember(mid);
	}
	
	/*
	 * Call member from database based on ID
	 */
	protected static Member getMember(String name) {
		Logger.log("requested single member based on name");
		return MembersList.GetMember(name);
	}
	
	protected static ArrayList<Member> getAllMembers() {
		Logger.log("requested all the current members");
		return MembersList.GetAllMembers();		
	}
	
	protected static Member getCurrentMember( ) {
		Logger.log("requested current member");
		return MembersList.getCurr();
	}
}



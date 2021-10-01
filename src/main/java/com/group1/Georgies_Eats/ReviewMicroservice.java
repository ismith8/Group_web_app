package com.group1.Georgies_Eats;

import java.util.List;

import org.springframework.stereotype.Controller;

@Controller
public class ReviewMicroservice {

	// Beth microservices: 
		// get a member
		// get multiple members
	
	// Isaac microservices:
		// get number of reviews
		// get member reviews
	
	/* Returns the total number of reviews */
	protected static int getNumReviews() {
		Logger.log("ReviewMicroservice: requested total number of reviews");
		return DataStorage.getAllReviews().size();
	}

	/* Returns the number of reviews for a member */
	protected static int getNumReviewsByMember(int MID) {
		String uID = String.valueOf(MID);
		List<Review> temp = DataStorage.getReviewsByUserID(uID);
		Logger.log("ReviewMicroservice: requested total number of reviews by member ID");
		return temp.size();
	}
	
	/* Returns the reviews by a member */
	protected static List<Review> getMemberReviews(int MID) {
		String uID = String.valueOf(MID);
		Logger.log("ReviewMicroservice: requested all reviews associcated to a member");
		return DataStorage.getReviewsByUserID(uID);
	}
}



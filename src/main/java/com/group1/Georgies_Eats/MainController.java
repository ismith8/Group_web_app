package com.group1.Georgies_Eats;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {
	
	// Used to identify if the user has been logged in yet
	private static boolean loggedIn = false;
	
	/* LANDING/INDEX PAGE */
	
	@GetMapping("/")
	public String indexPage() {
		if (!loggedIn) { return "login"; }
		Logger.log("Navigated to home"); 
		return "index"; 
	}
	
	@GetMapping("index")
	public String homePage() { 
		if (!loggedIn) { return "login"; }
		Logger.log("Navigated to home"); 
		return "index"; 
	}
	
	/* LOG OUT */
	@GetMapping("logout")
	public String logout() {
		loggedIn = false;
		MembersList.setCurr(null);
		Logger.log("user logged out");
		return "login";
	}
	
	
	/* LOGIN PAGE */
	@GetMapping("login")
	public String postLogin(Model m) {
		String memID = "";
		m.addAttribute("memID", memID);
		return "login";
	}
	
	/* 
	 * receive login form
	 * change bool if successfully logged in
	 */
	@PostMapping("login")
	public String login(@ModelAttribute("memID") String memID) {
		
		System.out.println(memID);
		int ID = 0;
		
		// Catches non int input
		try { ID = Integer.parseInt(memID); }
		catch (Exception e) {
			Logger.log("Use did not enter a number"); 
			return "bad-login"; 
		}
		
		// Gets member
		Member newLogin = MemberMicroservice.getMember(ID);
		if (newLogin != null) {
			loggedIn = true;
			MembersList.setCurr(newLogin);
			Logger.log("successfully added a member");
			return "index";
		}
		else {
			Logger.log("Failed to log in");
			return "bad-login";
		}
	}
	
	
	
	/* 
	 * CREATE REVIEW
	 * Displays the page, sets up model for
	 * information to be stored
	 */
	@GetMapping("create_review")
	public String createReviewPage(Model m) {
		if (!loggedIn) { return "login"; }
		Logger.log("Navigated to create_review"); 
		
		// review for user
		Review review = new Review();
		System.out.println("new review");
		review.setUserID(MemberMicroservice.getCurrentMember().GetMemberID());
		review.setName(MemberMicroservice.getCurrentMember().GetMemberName());
		m.addAttribute("review", review);

		// list of restaurants available, associates id with order
		ArrayList<String> listRestaurants = DataStorage.getRestaurantsNames();
		m.addAttribute("listRestaurants", listRestaurants);
		
		// list of ratings for user, associates value with choice
		ArrayList<String> listRatings = new ArrayList<String>();
		listRatings.add("It was amazing!");
		listRatings.add("It was great"); 
		listRatings.add("It was alright"); 
		listRatings.add("Ehhh..."); 
		listRatings.add("I would never eat here again"); 
		m.addAttribute("listRatings", listRatings);
		
		return "create_review";
	}
	
	/*
	 * Handles the information being sent back 
	 * upon submission from the user
	 */
	@PostMapping("create_review")
	public String submitForm(@ModelAttribute("review") Review review) {
		if (!loggedIn) { return "login"; }
		review.setUserID(MembersList.getCurr().GetMemberID());
		review.setName(MembersList.getCurr().GetMemberName());
				
	    boolean o = DataStorage.addReview(review);
	    if (o) {
	    	Logger.log("Created review: " + review.toString()); 
	    	return "display_review";
	    }
	    else {
	    	Logger.log("Failed to add review"); 
	    	return "bad_review";
	    }
	}
	
	
	/*
	 * LIST RESTAURANTS PAGE
	 * Gets all of the available restaurants and
	 * dynamically outputs them to the page
	 */
	@GetMapping("/restaurants")
	public String RestaurantPage(Model m) {
		if (!loggedIn) { return "login"; }

		
		Logger.log("Navigated to all-restaurants"); 
		
		// Used for loading a restaurant page
		RestaurantWrapper restaurant = new RestaurantWrapper();
		m.addAttribute("restaurant", restaurant);
		
		// Get a list of all Restaurants
		List<Restaurant> restaurantList = DataStorage.getAllRestaurants();
		m.addAttribute("restaurantList", restaurantList);
		
		return "all-restaurants";
	}
	
	/*
	 * Posts information for restaurant/review page
	 * Includes RestaurantWrapper for easier data
	 * access
	 */
	@PostMapping("/register")
	public String submitForm(@ModelAttribute("restaurant") RestaurantWrapper restaurant, Model m) {
		if (!loggedIn) { return "login"; }

		Logger.log("Navigated to " + restaurant.getName()); 
		String id = restaurant.getRestaurantID();
				
		// adds restaurant to model
		Restaurant r = DataStorage.getRestaurantByID(id);
		m.addAttribute("r", r);
		
		
		List<Review> reviews = DataStorage.getReviewsByRestaurantID(id);
		m.addAttribute("reviews", reviews);
		System.out.println(reviews.toString());
		
		
		// Average rating
		float temp = DataStorage.getAvgRating(id);
		String avgRating;
		if (temp == -1) { avgRating = "No ratings yet"; }
		else { avgRating = String.valueOf(temp); }
		m.addAttribute("avgRating", avgRating);
		System.out.println(avgRating);
	
		return "review";
	}	
	

	/*	EKT
	 * 	PROFILE PAGE
	 * 
	 * 	This is my Member Controller - it leads to my View
	 * 	
	 */
	@GetMapping("MemberView")
	public String profilePage(Model m) {
		if (!loggedIn) { return "login"; }

		
		System.out.println("In member view");
		
		//need to set the current user
		Member c = MembersList.getCurr();
		MemberWrapper mwc = new MemberWrapper(c); // wrapper 
		m.addAttribute("mwc", mwc);
		
		// Add reviews of current user
		List<Review> c_reviews = ReviewMicroservice.getMemberReviews(c.GetMemberID());
		System.out.println(c_reviews.size());
		m.addAttribute("c_reviews", c_reviews);
		Logger.log("Navigated to profile page - MemberView");
		return "MemberView";
	}	
}
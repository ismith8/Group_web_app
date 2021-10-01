package com.group1.Georgies_Eats;

/* 
 * REVIEW MODEL
 * 
 * This is the model for all Reviews
 * Contains 3 int identifiers, and an optional 
 * comment. NOTE: only rating and comment can
 * be updated, userID and RestaurantID are not
 * able to be changed
 */
public class Review {
	
	private int restaurantID;
	private String restaurantName;
	private int userID;
	private int rating;
	private String name;
	private String comment;
	
	/* blank constructor */
	public Review() {
		this.restaurantID = -1;
		this.restaurantName = this.getRestaurantName();
		this.userID = -1;
		this.rating = -1;
		this.name = "";
		this.comment = "";
		Logger.log("Created a new Review");
	}

	/* Getters and setters */
	public String getRestaurantID() { return String.valueOf(restaurantID); }	
	public String getRestaurantName() {
		switch(this.restaurantID) {
			case 1:
				return "Panera Bread";
			case 2:
				return "Starbucks";
			case 3:
				return "Chipotle";
			case 4:
				return "Dunkin' Donuts";
			case 5:
				return "Steak and Shake";
			default:
				return "Error - restaurant name not found";		
		}
	}
	public int getIntRestaurantID() { return restaurantID; }
	public void setRestaurantID(String restaurantID){this.restaurantID=(Integer.parseInt(restaurantID)+1);}
	public void setRestaurantID(int restaurantID) {this.restaurantID = restaurantID + 1; }
	public String getUserID() { return String.valueOf(userID); }
	public int getIntUserID() { return userID; }
	public void setUserID(String userID) { this.userID = Integer.parseInt(userID); }
	public void setUserID(int userID) { this.userID = userID; }
	public String getRating() { return String.valueOf(rating); }
	public int getIntRating() { return rating; }
	public void setRating(String rating) {
		if (rating.equals("0")) this.rating = 5;
		if (rating.equals("1")) this.rating = 4;
		if (rating.equals("2")) this.rating = 3;
		if (rating.equals("3")) this.rating = 2;
		if (rating.equals("4")) this.rating = 1;
	}
	public void setName(String name) { this.name = name; }
	public String getName() { return name; }
	public String getComment() { return comment;}
	public void setComment(String comment) {this.comment = comment;}

	
	/* String to String */
	@Override
	public String toString() {
		String temp = "User: ";
			temp += userID;
			temp += "\nName: ";
			temp += name;
			temp += "\nRestaurantID: ";
			temp += restaurantID;
			temp += "\nRating: ";
			temp += rating;
			temp += "\n\n";
			temp += comment;
		return temp;
	}
}

package com.group1.Georgies_Eats;

import java.util.ArrayList;
import java.util.List;

/*
 * DATABASE MODEL
 * 
 *  Acts as a faux database by storing
 *  2 ArrayLists of information and provides
 *  many access/modification methods.
 */
public class DataStorage {
	
	private static ArrayList<Restaurant> restaurants = new ArrayList<Restaurant>();
	private static ArrayList<Review> reviews = new ArrayList<Review>();
	
	
	/* Get all reviews */
	protected static ArrayList<Review> getAllReviews() {
		System.out.print("Returning ");
		System.out.print(reviews.size());
		System.out.println(" items");
		Logger.log("Requested all Reviews");
		return new ArrayList<Review>(reviews);
	}
	
	/* Get all Restaurants */
	protected static ArrayList<Restaurant> getAllRestaurants() {
		System.out.print("Returning ");
		System.out.print(restaurants.size());
		System.out.println(" items");
		Logger.log("Requested all Restaurants");
		return new ArrayList<Restaurant>(restaurants);
	}
	
			// Restaurant Methods //
	
	/* 
	 * Add a restaurant to the list
	 * Prevents multiple Restaurants to have the
	 * same RestaurantID 
	 */
	protected static void addRestaurant(Restaurant r) {
		for (int i = 0; i < restaurants.size(); i++) {
			if (restaurants.get(i).getRestaurantID().equals(r.getRestaurantID())) {
				Logger.log("Failed to add new restaurant " + r.getRestaurantID());
				return;
			}
		}
		restaurants.add(r);
		Logger.log("Added " + r.getName() + " to Restaurants");
		return;
	}
	
	/*
	 * Returns an ArrayList<String> of all
	 * the names of all the restaurants
	 */
	protected static ArrayList<String> getRestaurantsNames() {
		ArrayList<String> temp = new ArrayList<String>();
		for (int i = 0; i < restaurants.size(); i++) {
			temp.add(restaurants.get(i).getName());
		}
		Logger.log("Returning " + restaurants.size() + " items");
		return temp;
	}
	
	/* Returns the restaurant with matching id */
	protected static Restaurant getRestaurantByID(String id) {
		for (int i = 0; i < restaurants.size(); i++) {
			if (restaurants.get(i).getRestaurantID().equals(id)) return restaurants.get(i);
		}
		return null;
	}
	
	/* Returns the restaurant with matching name */
	protected static Restaurant getRestaurantByName(String name) {
		for (int i = 0; i < restaurants.size(); i++) {
			if (restaurants.get(i).getName().equals(name)) return restaurants.get(i);
		}
		return null;
	}
	
			// Review Methods //
	
	/*
	 * Handles adding a review and rejecting the
	 * add if the user has already reviewed that restaurant
	 */
	protected static boolean addReview(Review r) {
		for (int i = 0; i < reviews.size(); i++) {
			if (reviews.get(i).getUserID().equals(r.getUserID())) {
				if (reviews.get(i).getRestaurantID().equals(r.getRestaurantID())) {
					Logger.log("Failed to add review, already exists");
					return false;
				}
			}
		}
		reviews.add(r);
		String temp = "Added a review for ";
			temp += r.getRestaurantID();
			temp += " written by ";
			temp += r.getUserID();
			temp += "Contains ";
			temp += reviews.size();
			temp += " reviews";
		Logger.log(temp);
		return true;
	}
	
	/* Calculates the average rating for a restaurant */
	protected static float getAvgRating(String RID) {
		float temp = 0;
		int count = 0;
		for (int i = 0; i < reviews.size(); i++) {
			if (reviews.get(i).getRestaurantID().equals(RID)) {
				temp += reviews.get(i).getIntRating();
				System.out.println(reviews.get(i).getIntRating());
				count++;
			}
 		}
		if (temp == 0 || count == 0) { return -1; }
		temp /= count;
		return temp;
	}
	
	/* Gets reviews by restaurant ID */
	protected static List<Review> getReviewsByRestaurantID(String RID) {
		List<Review> temp = new ArrayList<Review>();
		for (int i = 0; i < reviews.size(); i++) {
			if (reviews.get(i).getRestaurantID().equals(RID)) { temp.add(reviews.get(i)); }
 		}
		return temp;
	}
	
	/* Gets reviews by Member ID */
	protected static List<Review> getReviewsByUserID(String UID) {
		List<Review> temp = new ArrayList<Review>();
		for (int i = 0; i < reviews.size(); i++) {
			if (reviews.get(i).getUserID().equals(UID)) { temp.add(reviews.get(i)); }
 		}
		return temp;
	}
	
	
	
}

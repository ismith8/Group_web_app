package com.group1.Georgies_Eats;

import java.util.ArrayList;
import java.util.List;

/*
 * RESTAURANT MODEL
 * 
 * Uses methods to create and store
 * information about restaurants in the
 * data storage file
 */
public class Restaurant {

	private String path; 
	private String name;
	private String restaurantID;
	private List<String> menu;
	private List<String> tags;
	private List<Review> reviews;
	
	
	/* Constructor, no parameters */
	public Restaurant() {
		this.path = "./";
		this.name = "";
		this.restaurantID = "";
		this.menu = new ArrayList<String>();
		this.tags = new ArrayList<String>();
		Logger.log("Created a new restaurant");
	}

	/*  Getters and setters */
	public String getPath() { return path; }
	public void setPath(String path) { this.path = path; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public String getRestaurantID() { return restaurantID; }
	public void setRestaurantID(String restaurantID) { this.restaurantID = restaurantID; }
	public List<String> getMenu() { return menu; }
	public void setMenu(List<String> menu) { this.menu = menu; }
	public List<String> getTags() { return tags; }
	public void setTags(List<String> tags) { this.tags = tags; }
	
	/* To String */
	@Override
	public String toString() {
		String temp = name;
			temp += "\nContains ";
			temp += reviews.size();
			temp += " reviews\nLogopath:";
			temp += path;
		return temp;
	}
}


/*
 * Subclass for wrapping restaurant info and getting 
 * Name and RID used in Controller
 */
class RestaurantWrapper {
	
	private String name;
	private String restaurantID;
	
	public RestaurantWrapper() {
		this.name = "";
		this.restaurantID = "";
		Logger.log("Created a RestaurantWrapper");
	}
	
	public RestaurantWrapper(String name, String restaurantID) {
		this.name = name;
		this.restaurantID = restaurantID;
		Logger.log("Created a RestaurantWrapper");
	}
	/* Getters and setters */
	public void setName(String name) { this.name = name;}
	public String getName() { return name; }
	public void setRestaurantID(String restaurantID) { this.restaurantID = restaurantID; }
	public String getRestaurantID() { return restaurantID; }
}
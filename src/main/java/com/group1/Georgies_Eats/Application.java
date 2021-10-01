package com.group1.Georgies_Eats;

import java.util.Arrays;

import javax.annotation.PreDestroy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.jms.core.JmsTemplate;


@SpringBootApplication
public class Application {
	
	public static void main(String[] args) {
		fillMemberList();
		fillRestaurants();
		SpringApplication.run(Application.class, args);
	}
	
	@PreDestroy
	// On close, write Logger
	public void onExit() { Logger.close(); }
	
	/*
	 * Set up information for memberList
	 */
	private static void fillMemberList() {
		Logger.log("creating hardcoded member data");
				
		Member m1= new Member("Korra", 1);
		Member m2= new Member("Asami", 2);
		Member m3= new Member("Pabu", 3);
		Member m4= new Member("Bolin", 4);
		
		MembersList.AddMember(m1);
		MembersList.AddMember(m2);
		MembersList.AddMember(m3);
		MembersList.AddMember(m4);

		Logger.log("now have data to view in the system");
	}
	
	
	/*
	 * Automatically generates restaurant information
	 * and fills into DataStorage for retrieval.
	 * Add more restaurants here.
	 */
	private static void fillRestaurants() {
		
		// FIXME - if time allows, go back and actually implement
		// the menus. That would be a cool extra feature. Or read
		// from a CSV file.
		
		// Panera Bread
		Restaurant r = new Restaurant();
		r.setPath("resources/panera-logo.png");		
		r.setName("Panera Bread");
		r.setRestaurantID("1");
		String[] PnrTags = {"Fast", "Healthy", "American", "Sandwich", "Cafe"};
		r.setTags(Arrays.asList(PnrTags));
		DataStorage.addRestaurant(r);
		
		// Starbucks
		r = new Restaurant();
		r.setPath("/resources/starbucks-logo.png");
		r.setName("Starbucks");
		r.setRestaurantID("2");
		String[] SbuxTags = {"Fast", "Cafe", "Coffee", "Popular"};
		r.setTags(Arrays.asList(SbuxTags));
		DataStorage.addRestaurant(r);

		// Chipolte
		r = new Restaurant();
		r.setPath("/resources/chipolte-logo.png");
		r.setName("Chiplote");
		r.setRestaurantID("3");
		String[] ChpTags = {"Mexian", "Burrito", "Popular"};
		r.setTags(Arrays.asList(ChpTags));
		DataStorage.addRestaurant(r);
		
		// Dunkin Donuts
		r = new Restaurant();
		r.setPath("./resources/dd-logo.png");
		r.setName("Dunkin' Donuts");
		r.setRestaurantID("4");
		String[] Ddtags = {"Coffee", "Cafe", "Cheap"};
		r.setTags(Arrays.asList(Ddtags));
		DataStorage.addRestaurant(r);
		
		// Steak and Shake
		r = new Restaurant();
		r.setPath("./resources/sns-logo.png");
		r.setName("Steak and Shake");
		r.setRestaurantID("5");
		String[] SnsTags = {"Fast", "Burgers", "Milkshake", "Cheap"};
		r.setTags(Arrays.asList(SnsTags));
		DataStorage.addRestaurant(r);
	}

}

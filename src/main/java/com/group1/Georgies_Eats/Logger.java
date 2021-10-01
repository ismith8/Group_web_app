package com.group1.Georgies_Eats;

import java.io.FileWriter;

/*
 * Cross Cutting Concerns
 * 
 * A simple logger to tack what the user is doing.
 * Call Logger.log(String log) to log an action.
 * On close Application.java will call Logger.close() to
 * write all the information to a file. 
 */
public class Logger {
	
	private static String writeToLog = "";
	
	/* 
	 * Build up string to be written to file
	 * of user actions
	 */
	public static void log(String log) {
		writeToLog += log;
		writeToLog += "\n";
	}
	
	/*
	 * On close write information to the file
	 */
	public static void close() {
		try {
			FileWriter fw = new FileWriter("log.txt");
			fw.write(writeToLog);
			fw.close();
		}
		catch(Exception e) { e.printStackTrace(); }
	}
}

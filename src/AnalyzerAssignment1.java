/**
 * Seng 300 Group 11 Assignment 1
 * 
 * @author Sidney Shane Dizon, Sunah Kim, Kristopher Tang
 * 
 * This is a java application analysis tool that will find the number of
 * occurrences of the declarations of and references to a Java type (as a 
 * fully qualified name, like java.lang.String), within a specified 
 * directory.
 *
 *	Input:
 * This takes in pathname to indicate a directory of interest.
 * Takes a string to indicate a fully qualified name of a Java type.
 *	
 *	Process:
 * The java application counts the number of declarations of that type
 * within that directory and counts that number of references to each 
 * occurrence of that type within that directory.
 * 
 *	Output:
 * The output is a string written to the console stating the name of the 
 * type, the count of declarations, and the count of references, such as:
 * 
 * 		<type>. Declarations found: <count>; references found: <count>.
 * 
 * where the <type> and two <count>s are to be replaced with the relevant 
 * values.
 */

import java.io.*;


public class AnalyzerAssignment1 {

	public static void main(String[] args) {
		//print Welcome message to console to get the input from the user
		
		//Get the pathname from the user
		
		//Get the Java type from the user
		
		//Open the directory specified by the user
		
		

	}
	
	public static void AccessContentsOfADirectory(String pathname) {
		File directory = new File(pathname);
		File[] contentsOfDirectory = directory.listFiles();
		//print the directory contents to the console for checking
		for (File object : contentsOfDirectory) {
			if (object.isFile()) {
				System.out.format("File name: %s%n", object.getName());
			}
			else if (object.isDirectory()) {
				System.out.format("Directory name: %s%n", object.getName());
			}
			
		}
	}

}

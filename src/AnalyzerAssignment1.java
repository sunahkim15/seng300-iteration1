/**
 * Seng 300 Group 11 Assignment 1
 * 
 * @author Sidney Shane Dizon, Sunah Kim, Kristopher Tang
 * 
 * This is a java application analysis tool that will find the number of occurrences of the declarations of and references to a Java type (as a 
 * fully qualified name, like java.lang.String), within a specified directory.
 *
 *	Input:
 * This takes in pathname to indicate a directory of interest.
 * Takes a string to indicate a fully qualified name of a Java type.
 *	
 *	Process:
 * The java application counts the number of declarations of that type within that directory and counts that number of references to each occurrence of that 
 * type within that directory.
 * 
 *	Output:
 * The output is a string written to the console stating the name of the type, the count of declarations, and the count of references, such as:
 * 
 * 		<type>. Declarations found: <count>; references found: <count>.
 * 
 * where the <type> and two <count>s are to be replaced with the relevant values.
 */

import java.io.*;
import java.util.Scanner;

public class AnalyzerAssignment1 {

	public static void main(String[] args) {
		Scanner userInput = new Scanner(System.in);
		//print Welcome message to console to get the input from the user
		System.out.print("Welcome! The Analyzer is now running, please type in the pathname your are interested in.\nPathname = ");
		//Get the pathname from the user
		String pathname = userInput.nextLine();
		System.out.println("The pathname is: " + pathname);
		System.out.print("Now type in the fully qualified name of a Java Type.\nType = ");
		//Get the Java type from the user
		String javaType = userInput.nextLine();
		System.out.println("The Java type is " + javaType);
		
		//Open the directory specified by the user
		accessContentsOfADirectory(pathname);
		

	}
	
	public static void accessContentsOfADirectory(String pathname) {
		File directory = new File(pathname);
		File[] contentsOfDirectory = directory.listFiles();
		//print the directory contents to the console for checking
		for (int i = 0; i < contentsOfDirectory.length ; i++) {
			if (contentsOfDirectory[i] != null) {
				File object = contentsOfDirectory[i];
				if (object.isFile()) {
					System.out.format("File name: %s%n", object.getName());
				}
				else if (object.isDirectory()) {
					System.out.format("Directory name: %s%n", object.getName());
				}
			}
		}
	}

}

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
 * 
 * Link Sources:
 * 	https://stackoverflow.com/questions/5603966/how-to-make-filefilter-in-java
 * 	https://www.tutorialspoint.com/java/java_file_class.htm
 * 	https://www.webucator.com/how-to/how-display-the-contents-of-directory-java.cfm
 * 
 */

import java.io.*;

public class AnalyzerAssignment1 {

	public static void main(String[] args) {
		
		//Check if the command line arguments include a pathname and java type
		if (args.length > 1) {
			//Get the pathname from the user
			String pathname = args[0];
			//Get the Java type from the user
			String javaType = args[1];
		
			//print for checking
			System.out.println("The pathname is: " + pathname);
			System.out.println("The Java type is " + javaType);
			
			//Open the directory specified by the user and get contents
			File[] directoryContents = accessContentsOfADirectory(pathname);
			//System.out.println("Content:\n" + directoryContents);
			
			//Check if we have a valid directory; if we have null then terminate the program
			if (directoryContents == null) {
				System.out.println("Program terminated.");
				System.exit(0);
			}
			
		
		
		}
		else {
			System.out.println("There are no pathname or java type input on the command line arguments. Retry again.");
		}
	}
	
	/**
	 * This opens and gets the contents of the directory specified
	 * @param pathname - the pathname of the directory
	 * @return - the array of contents of the directory
	 */
	public static File[] accessContentsOfADirectory(String pathname) {
		final String extension = ".java";
		File directory = new File(pathname);
		//Only get the files that have a .java extension
		File[] contentsOfDirectory = directory.listFiles((File path) -> path.getName().endsWith(extension));

		//check if the Directory exists
		if (directory.canRead()) {
			//print the directory contents to the console for checking
			for (File object : contentsOfDirectory) {
				if (object.isFile()) {
					System.out.format("File name: %s%n", object.getName());
				}
				else if (object.isDirectory()) {
					System.out.format("Directory name: %s%n", object.getName());
				}
			}
			
		} else {
			//Otherwise terminate the program and tell the user.
			System.out.println("The pathname specified is not valid. Try running the code again and enter a valid one.");
		}
		return contentsOfDirectory;
	}

}

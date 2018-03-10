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
 * 	https://www.programcreek.com/2011/11/use-jdt-astparser-to-parse-java-file/
 */

import java.io.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
 
import org.eclipse.jdt.core.dom.AST;
import org.eclipse.jdt.core.dom.ASTParser;
import org.eclipse.jdt.core.dom.ASTVisitor;
import org.eclipse.jdt.core.dom.AbstractTypeDeclaration;
import org.eclipse.jdt.core.dom.CompilationUnit;
import org.eclipse.jdt.core.dom.SimpleName;
import org.eclipse.jdt.core.dom.Type;
import org.eclipse.jdt.core.dom.VariableDeclaration;
import org.eclipse.jdt.core.dom.VariableDeclarationFragment;
import org.eclipse.jdt.core.dom.VariableDeclarationStatement;


public class AnalyzerAssignment1 {

	public static void main(String[] args) throws IOException, FileNotFoundException {
		
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
	 * This opens and gets the contents of the directory that has .java extension
	 * @param pathname - the pathname of the directory
	 * @return - the array of .java file extension located inside the directory
	 * @throws IOException - throws IOException if the filePath doesn't exist
	 */
	public static File[] accessContentsOfADirectory(String pathname) throws IOException {
		final String extension = ".java";
		File directory = new File(pathname);
		//Only get the files that have a .java extension
		File[] contentsOfDirectory = directory.listFiles((File path) -> path.getName().endsWith(extension));

		//check if the Directory exists
		if (directory.canRead()) {
			String filePath = null;
			for (File object : contentsOfDirectory) {
				filePath = object.getAbsolutePath();
				if (object.isFile()) {
					//print the directory contents to the console for checking
					System.out.format("File name: %s%n", object.getName());
					//Parse for file
					//System.out.println("The file content:\n" + readFileToString(filePath));
					parse(readFileToString(filePath));
					//readFileToString(filePath);
				}
			}
			
		} else {
			//Otherwise terminate the program and tell the user.
			System.out.println("The pathname specified is not valid. Try running the code again and enter a valid one.");
		}
		return contentsOfDirectory;
	}
	
	/**
	 * This method read file content into a string
	 * @param filePath - the path to the file to be read
	 * @return - the contents of the file read as String
	 * @throws IOException - if the file doesn't exist then this will throw an exception
	 */
	public static String readFileToString(String filePath) throws IOException, FileNotFoundException {
		StringBuilder fileData = new StringBuilder(1000);
		BufferedReader reader = new BufferedReader(new FileReader(filePath));
		
		char[] buffer = new char[10];
		int numRead = 0;
		while ((numRead = reader.read(buffer)) != -1) {
			System.out.println("numRead is: "+ numRead);
			String readData = String.valueOf(buffer, 0, numRead);
			fileData.append(readData);
			buffer = new char[1024];
		}
		
		reader.close();
		
		return fileData.toString();
	}
	
	/**
	 * This method parses the contents of the .java file for declarations
	 * @param fileContent - content of the .java file as String
	 */
	public static void parse(String fileContent) {
		ASTParser parser = ASTParser.newParser(AST.JLS8);
		parser.setSource(fileContent.toCharArray());
		parser.setKind(ASTParser.K_COMPILATION_UNIT);
		
		final CompilationUnit compUnit = (CompilationUnit) parser.createAST(null);
		
		compUnit.accept(new ASTVisitor() {
			
			// finding type of variable declared (use for finding references to java type) 
			public boolean visit(VariableDeclarationStatement node) {
				Type name = node.getType();
				int lineNumber = compUnit.getLineNumber(name.getStartPosition());
				
				System.out.println("Declaration of '" + name.toString() + "' at Line " + lineNumber);
				System.out.println("----------------------------------------------");
				return false; // do not continue
			}
			
			// working on finding when class, enum, interface, and annot type declared
			public boolean visit(AbstractTypeDeclaration node) {
				SimpleName name = node.getName();
				int lineNumber = compUnit.getLineNumber(name.getStartPosition());
				
				System.out.println("Declaration of '" + name.toString() + "' at Line " + lineNumber);
				System.out.println("----------------------------------------------");
				return false; // do not continue
			}
			
			
		});
		
	}

}

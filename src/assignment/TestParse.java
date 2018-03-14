package assignment;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

public class TestParse {
	private static String BASEDIR = "C:\\Users\\esthe\\Desktop\\test300assign"; 
	String[] sourcePath = {BASEDIR};
	
	File file = new File(BASEDIR + "\\dummy.java");
	
	
	@Before
	public void setup() throws IOException {
		FileWriter writer = new FileWriter(file);
		writer.write("public class Dummy {\n public int a;\n public void method(){}\n}");
		writer.close();
	}

	@Test
	public void test() throws FileNotFoundException, IOException {
		String filecontent = AnalyzerAssignment1.readFileToString(BASEDIR + "\\dummy.java");
		AnalyzerAssignment1.parse(filecontent, sourcePath, "dummy.java", "int");
	}

}

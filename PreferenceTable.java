import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;


public class PreferenceTable 
{

	private static Vector<Vector<String>> loadContentFromFile(String filePath) throws IOException
	{
		Vector<Vector<String>> vector = new Vector<Vector<String>>();			// create vector of vectors containing strings
		Vector<String> vector2 = new Vector<String>();							// create vector of strings
		File file = new File(filePath);											// set file path from inputted String
			
		try (BufferedReader br = new BufferedReader(new FileReader(file))) 		// BufferedReader to read each line by line
		{
		    String line;
		    int i = 0;
		    while ((line = br.readLine()) != null) 								// until EOF
			{
		    	String[] tokens = new String[12];								// Set up string array
		    	tokens = line.split("\\t");										// split line into tokens for string array
		    	
		    	int l = tokenTest(tokens);										// count how many tokens there are
		    	
		    	for(i = 0; i < l; i++)
		    	{
		    		vector2.add(tokens[i]);										// add tokens to vector
		    	}
		    	
		    	vector2.add("\n");
		    	
		    	vector.add(vector2);											// add vector to vector of vectors
		    }
		}
		
		return vector;															// return vector
	}
		
	public static int tokenTest(String[] tokens)
	{
		int amount = tokens.length;
		return amount;															// return amount of tokens
	}	
	
	
	public static void main(String[] args) throws IOException 
	{
		Vector<Vector<String>> preferences = loadContentFromFile("src/data.tsv");
		System.out.println(preferences);
	}

}

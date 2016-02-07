import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class PreferenceTable 
{
	public static ArrayList<String> choices = new ArrayList<String>();
		
	static Person[] group = new Person[52];										// for 51 students and the top line 

	private static void loadContentFromFile(String filePath) throws IOException
	{
		File file = new File(filePath);											// set file path from inputted String
		int g = 0;
			
		try (BufferedReader br = new BufferedReader(new FileReader(file))) 		// BufferedReader to read each line by line
		{
		    String line;
		    int i = 0;
		    int a = 0;
		    while ((line = br.readLine()) != null) 								// until EOF
			{
		    	if (a == 0)
    			{
		    		// do nothing
    			}
		    	
		    	else
		    	{
			    	String[] tokens = new String[12];								// Set up string array
			    	tokens = line.split("\\t");										// split line into tokens for string array
			    	
			    	int l = tokenTest(tokens);										// count how many tokens there are
			    	
			    	Person add = new Person();										// create person object
			    	add.setName(tokens[0]);											// add name to person
			    	add.setPreArranged(tokens[1]);									// add pre-arranged value to person
			    	
			    	for(i = 0; i < l; i++)											// for all of the tokens 
			    	{
			    		if(i > 1)													// add preferences to preference vector, when i is not 0 or 1
			    		{
			    			add.addPref(tokens[i]);
			    			addToChoiceArray(tokens[i]);
			    		}
			    	}
			    	
			    	group[g] = add;
			    	g++;
		    	}
		    	a = 1;
		    }
		}
	}
		
	public static int tokenTest(String[] tokens)
	{
		int amount = tokens.length;
		return amount;															// return amount of tokens
	}	
	
	public static void addToChoiceArray(String choice)
	{
		
		if(choices.contains(choice))
		{
			// do nothing
		}
		
		else
		{
			choices.add(choice);
		}
	}
	
	public static void main(String[] args) throws IOException 
	{
		loadContentFromFile("src/data.tsv");
		
		for(int i = 0; i < group.length; i++)
		{
			System.out.println(group[i]);
		}
				
		java.util.Collections.sort(choices);			// Alphabetize choices 
		
//		for(int i = 0; i < choices.size(); i++)
//		{
//			System.out.println(choices.get(i));
//		}
	}

}

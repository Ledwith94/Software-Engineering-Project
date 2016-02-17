import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

public class PreferenceTable
{
	public static ArrayList<String> choices = new ArrayList<String>();
		
	static StudentEntry[] group = new StudentEntry[52];										// for 51 students and the top line 
	static Hashtable<String, StudentEntry> table = new Hashtable<String, StudentEntry>();	// hashtable for names and student entries
	

	private static void loadContentFromFile(String filePath) throws IOException
	{
		File file = new File(filePath);											// set file path from inputted String
		int g = 0;
			
		try (BufferedReader br = new BufferedReader(new FileReader(file))) 		// BufferedReader to read each line by line
		{
		    String line;
		    int i = 0;
		    int a = 0;
		    while ((line = br.readLine()) != null) 									// until EOF
			{
		    	if (a == 0)
    			{
		    																		// do nothing for the top line 
    			}
		    	
		    	else
		    	{
			    	String[] tokens = new String[12];								// Set up string array
			    	tokens = line.split("\\t");										// split line into tokens for string array
			    	
			    	int l = tokenTest(tokens);										// count how many tokens there are
			    	
			    	StudentEntry add = new StudentEntry();							// create StudentEntry object
			    	add.setName(tokens[0]);											// add name to StudentEntry
			    	add.setPreArranged(tokens[1]);									// add pre-arranged value to StudentEntry
			    	
			    	for(i = 0; i < l; i++)											// for all of the tokens 
			    	{
			    		if(i > 1)													// add preferences to preference vector, when i is not 0 or 1
			    		{
			    			add.addPref(tokens[i]);
			    			addToChoiceArray(tokens[i]);
			    		}
			    	}
			    	
			    	add.setStatedPrefs();											// store stated preferences
			    	
			    	group[g] = add;													// add student to group 
			    	table.put(add.getName(), add);									// add student to hashtable
			    	g++;
		    	}
		    	a = 1;
		    }
		}
	}
		
	public static int tokenTest(String[] tokens)
	{
		int amount = tokens.length;
		return amount;																// return amount of tokens
	}	
	
	public static void addToChoiceArray(String choice)
	{
		
		if(choices.contains(choice))												// if choice is already there, don't add
		{
			// do nothing
		}
		
		else
		{
			choices.add(choice);
		}
	}
	
	public static ArrayList<StudentEntry> getAllStudentEntries()					// create list of all student entries
	{
		ArrayList<StudentEntry> list = new ArrayList<StudentEntry>();					
		
		for(int i = 0; i != group.length - 1; i++)
		{
			list.add(group[i]);														// add to list 
		}
		
		return list;
	}
	
	public static StudentEntry getEntryFor(String name)								// lookup hashtable and return student
	{
		StudentEntry student = new StudentEntry();
		
		if(table.containsKey(name))													// if name in hashtable
		{
			student = table.get(name);												// get student 
		}
		else
		{
			student = null;
		}
		
		return student;
	}
	
	public static void main(String[] args) throws IOException 
	{
		loadContentFromFile("src/data.tsv");
		
		StudentEntry test = group[0];
		
		System.out.println("Name: " + test.getName());
		System.out.println("Pre-arranged: " + test.getpreArranged());
		System.out.println("Preferences: \n" + test.getPrefs());
		System.out.println("Stated Preferences: " + test.getStatedPrefs()+"\n");
		
		ArrayList<StudentEntry> list = getAllStudentEntries();
		System.out.println(list.toString());
		
		StudentEntry student = getEntryFor("Richard B. Riddick");					// getStudentEntry
		
		if(student == null)
		{
			System.out.println("Error, student entry null");
		}
		else
		{
			System.out.println("\ngetEntryFor success for "+student);
		}
		
		//System.out.println("\n\n Hashtable: "+table.toString());
				
		java.util.Collections.sort(choices);										// Alphabetize list of choices 
		
	}

}

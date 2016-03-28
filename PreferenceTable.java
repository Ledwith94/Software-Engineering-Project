import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Random;

public class PreferenceTable
{
	private  Random RND = new Random();
	
	public  ArrayList<String> choices = new ArrayList<String>();
	public  ArrayList<String> preArrangedChoices = new ArrayList<String>();
		
	 StudentEntry[] students = new StudentEntry[52];									// for 51 students and the top line 
	 Hashtable<String, StudentEntry> table = new Hashtable<String, StudentEntry>();		// hashtable for names and student entries

	public  PreferenceTable(String filePath) throws IOException
	{
		File file = new File(filePath);											// set file path from inputted String
		int g = 0;																// students iterator 
			
		try (BufferedReader br = new BufferedReader(new FileReader(file))) 		// BufferedReader to read each line by line
		{
		    String line;
		    int i = 0;
		    int a = 0;
		    while ((line = br.readLine()) != null) 									// until EOF
			{	if (a == 0){}														// do nothing for the top line
		    	
		    	else
		    	{
			    	String[] tokens = new String[12];								// Set up string array
			    	tokens = line.split("\\t");										// split line into tokens for string array
			    	
			    	int l = tokenTest(tokens);										// count how many tokens there are
			    	
			    	StudentEntry add = new StudentEntry();							// create StudentEntry object
			    	add.setName(tokens[0]);											// add name to StudentEntry
			    	add.setPreArranged(tokens[1]);									// add pre-arranged value to StudentEntry
			    	
			    	if(add.getPreArranged())										// if someone has a prearranged choice
			    		{preArrangedChoices.add(tokens[2]);							// store choice in prearranges arraylist
			    		 add.addPref(tokens[2]);}
			    	
			    	else															// else store in other choices arraylist
			    	{
				    	for(i = 2; i < l; i++)										// for all of the other tokens 
				    	{
			    			add.addPref(tokens[i]);									// add preferences to StudentEntry object
			    			addToChoiceArray(tokens[i]);							// add choice to choice arraylist
				    	}
			    	}
			    	
			    	add.setStatedPrefs();											// store stated preferences
			    	
			    	students[g] = add;													// add student to students 
			    	table.put(add.getName(), add);									// add student to hashtable
			    	g++;															// increment students counter
		    	}
		    	a = 1;																// change a to take in all lines except first
		    }
		}
	}
		
	public  int tokenTest(String[] tokens)
		{return tokens.length;}																// return amount of tokens	
	
	public  void addToChoiceArray(String choice)
	{
		if(choices.contains(choice.intern()))												// if choice is already there, don't add
		{}		// do nothing
		else
			{choices.add(choice.intern());}
	}
	
	public  ArrayList<StudentEntry> getAllStudentEntries()					// create list of all student entries
	{
		ArrayList<StudentEntry> list = new ArrayList<StudentEntry>();					
		
		for(int i = 0; i != students.length - 1; i++)
			{list.add(students[i]);}													// add to list 
		
		return list;
	}
	
	public  StudentEntry getEntryFor(String name)								// lookup hashtable and return student
	{
		StudentEntry student = new StudentEntry();
		
		if(table.containsKey(name.intern()))										// if name in hashtable
			{student = table.get(name.intern());}									// get student 

		else
			{student = null;}
		
		return student;
	}
	
	// getRandomStudent
	public  StudentEntry getRandomStudent()
		{return students[RND.nextInt(students.length)];}									// return a random StudentEntry
	
	// getRandomPreference
	public  String getRandomPreference()										// return a random preference
		{return choices.get(RND.nextInt(choices.size() ) );}
	
	// fillPreferenceOfAll(int max)
	public  void fillPreferencesOfAll(int maxInt)
	{
		for(int i = 0; i < students.length-1; i++)										// loop through student students
		{
			StudentEntry student = students[i];
			if(student.getPreArranged() == true)									// if preArranged, do nothing
			{} // do nothing
			else
			{
				while(student.getTotalPrefs() < maxInt)									// add to preferences until 10
				{
					String choice = getRandomPreference();							// get random pref
					student.addPref(choice);										// add (if not a pref already, else don't add)
				}
			}
		}
	}
 }

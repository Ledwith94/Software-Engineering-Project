import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class PreferenceTable
{
	private Random RND = new Random();
	
	private ArrayList<String> choices = new ArrayList<String>();
	private ArrayList<String> preArrangedChoices = new ArrayList<String>();
	private ArrayList<StudentEntry> students = new ArrayList<StudentEntry>();
	private ArrayList<StudentEntry> preArrangedStudents = new ArrayList<StudentEntry>();
	private HashMap<String, StudentEntry> table = new HashMap<String, StudentEntry>();		// hashtable for names and student entries

	public ArrayList<StudentEntry> getStudents()
		{return this.students;}
	
	public ArrayList<String> getChoices()
		{return this.choices;}
	
	public ArrayList<String> getPreArrChoices()
		{return this.preArrangedChoices;}
	
	public PreferenceTable(String filePath) throws IOException
	{
		File file = new File(filePath);											// set file path from inputted String
			
		try (BufferedReader br = new BufferedReader(new FileReader(file))) 		// BufferedReader to read each line by line
		{
		    String line;
		    int i = 0;
		    int a = 0;
		    
		    while ((line = br.readLine()) != null) 									// until EOF
			{	boolean preArrStud = false;
		    	if (a == 0){}														// do nothing for the top line
		    	
		    	else
		    	{
			    	String[] tokens = new String[12];								// Set up string array
			    	tokens = line.split("\\t");										// split line into tokens for string array
			    	
			    	int l = tokenTest(tokens);										// count how many tokens there are
			    	
			    	StudentEntry stu = new StudentEntry();							// create StudentEntry object
			    	stu.setName(tokens[0]);											// add name to StudentEntry
			    	stu.setPreArranged(tokens[1]);									// add pre-arranged value to StudentEntry
			    	
			    	if(stu.getPreArranged())										// if someone has a prearranged choice
			    		{this.preArrangedChoices.add(tokens[2]);					// store choice in prearranged arraylist
			    		 preArrStud = true;
			    		 stu.addPref(tokens[2]);}
			    	
			    	else															// else store in other choices arraylist
			    	{
				    	for(i = 2; i < l; i++)										// for all of the other tokens 
				    	{
				    		stu.addPref(tokens[i]);									// add preferences to StudentEntry object
			    			addToChoiceArray(tokens[i]);							// add choice to choice arraylist
				    	}
			    	}
			    	
			    	stu.setStatedPrefs();											// store stated preferences
			    	
			    	this.students.add(stu);												// add student to students
			    	if(preArrStud)
			    		{this.preArrangedStudents.add(stu);preArrStud = false;}			// add to preArranged list, reset boolean
			    	this.table.put(stu.getName(), stu);									// add student to hashtable
		    	}
		    	a = 1;																// change a to take in all lines except first
		    }
		}
	}
		
	public int tokenTest(String[] tokens)
		{return tokens.length;}																// return amount of tokens	
	
	public void addToChoiceArray(String choice)
	{
		if(this.choices.contains(choice.intern()))												// if choice is already there, don't add
			{}		// do nothing
		else
			{this.choices.add(choice.intern());}
	}
	
	public ArrayList<StudentEntry> getAllStudentEntries()					// create list of all student entries
	{
		ArrayList<StudentEntry> list = new ArrayList<StudentEntry>();					
		
		for(int i = 0; i != this.students.size() - 1; i++)
			{list.add(this.students.get(i));}									// add to list 
		
		return list;
	}
	
	public StudentEntry getEntryFor(String name)								// lookup hashtable and return student
	{
		StudentEntry student = new StudentEntry();
		
		if(this.table.containsKey(name.intern()))								// if name in hashtable
			{student = this.table.get(name.intern());}							// get student 

		else
			{student = null;}
		
		return student;
	}
	
	// getRandomStudent
	public StudentEntry getRandomStudent()
		{return this.students.get(RND.nextInt(this.students.size()));}			// return a random StudentEntry
	
	// getRandomPreference
	public String getRandomPreference()											// return a random preference
		{return this.choices.get(RND.nextInt(this.choices.size() ) );}
	
	// fillPreferenceOfAll(int max)
	public void fillPreferencesOfAll(int maxInt)
	{
		for(int i = 0; i < this.students.size()-1; i++)							// loop through student students
		{
			StudentEntry student = this.students.get(i);
			if(student.getPreArranged() == true)								// if preArranged, do nothing
				{} // do nothing
			else
			{
				while(student.getTotalPrefs() < maxInt)							// add to preferences until max
				{
					String choice = getRandomPreference();						// get random pref
					student.addPref(choice);									// add (if not a pref already, else don't add)
				}
			}
		}
	}	
}

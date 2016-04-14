import java.util.ArrayList;
import java.util.Random;

public class StudentEntry 
{

	private static Random RND = new Random();
	private String name;
	private boolean preArranged;
	private ArrayList<String> preferences = new ArrayList<String>();
	private int statedPrefs;
	
	public void setName(String input)							// set name
		{this.name = input;}
	
	public String getName()										// return name
		{return this.name;}

	public void setPreArranged(String input)					// set prearranged status
		{if(input.equalsIgnoreCase("yes"))
			{this.preArranged = true;}
		else
			{this.preArranged = false;}}
	
	public boolean getPreArranged()								// return prearranged status
		{return this.preArranged;}
	
	public void addPref(String input)							// check if input is in preference list already 
		{if(!hasPreference(input)) 								// do not add to preferences if there already
			{this.preferences.add(input);}						// else add
		}
	
	public String getPrefs()									// return preferences in a string
		{String s = "";
			for(int i = 0; i < this.preferences.size(); i++)
				{s += (i+1) + ": " + this.preferences.get(i) + "\n";}
			return s;}
	
	public void setStatedPrefs()								// set stated number of preferences
		{this.statedPrefs = this.preferences.size();}
	
	public int getStatedPrefs()									// get stated number of preferences
		{return this.statedPrefs;}
	
	public int getTotalPrefs()									// get total number of prefs
		{return this.preferences.size();}
	
	public String getRandomPreference()							// return random preference
		{if(this.getPreArranged()) {return this.preferences.get(0);} // return first pref if prearranged				
		 return this.preferences.get(RND.nextInt(this.preferences.size()));}
	
	public int getRanking(String s)								// return choiceIndex
		{return this.preferences.indexOf(s);}
	
	public boolean hasPreference(String input)					// check if has preference
		{if(this.preferences.contains(input.intern()))
			{return true;}
		return false;}
	
	public String toString()									// print StudentEntry object
		{String s = "\nName: "+getName()+"\nPreArranged: "+getPreArranged()+"\nPreferences: "+getPrefs();
		return s;}
}

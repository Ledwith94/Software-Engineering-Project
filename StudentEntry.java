import java.util.ArrayList;


public class StudentEntry 
{

	private String name;
	private boolean preArranged;
	private ArrayList<String> preferences = new ArrayList<String>();
	private int statedPrefs;
	
	public void setName(String input)							// set name
	{
		this.name = input;
	}
	
	public String getName()										// return name
	{
		return this.name;
	}

	public void setPreArranged(String input)					// set prearranged status
	{
		if(input.equalsIgnoreCase("yes"))
		{
			this.preArranged = true;
		}
		
		else
		{
			this.preArranged = false;
		}
	}
	
	public boolean getpreArranged()								// return prearranged status
	{
		return this.preArranged;
	}
	
	public void addPref(String input)							// maybe int value for preference number
	{
		this.preferences.add(input);
	}
	
	public String getPrefs()									// return preferences in a string
	{
		String s = "";
		for(int i = 0; i < preferences.size(); i++)
		{
			s += (i+1) + ": " + preferences.get(i) + "\n";
		}
		return s;
	}
	
	public void setStatedPrefs()								// set stated number of preferences
	{
		statedPrefs = preferences.size();
	}
	
	public int getStatedPrefs()									// get stated number of preferences
	{
		return statedPrefs;
	}
	
	public String toString()									// print StudentEntry object
	{
		String s = "\nName: "+getName()+"\nPreArranged: "+getpreArranged()+"\nPreferences: "+getPrefs();
		return s;
	}

}

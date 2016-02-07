import java.util.ArrayList;


public class Person 
{

	private String name;
	private String preArranged;
	private ArrayList<String> preferences = new ArrayList<String>();
	
	public void setName(String input)
	{
		this.name = input;
	}
	
	public String getName()
	{
		return this.name;
	}

	public void setPreArranged(String input)
	{
		this.preArranged = input;
	}
	
	public String getpreArranged()
	{
		return this.preArranged;
	}
	
	public void addPref(String input)			// maybe int value for preference number
	{
		this.preferences.add(input);
	}
	
	public String getPrefs()
	{
		String s = preferences.get(0);
		for(int i = 1; i < preferences.size(); i++)
		{
			s += " " + preferences.get(i) + ",";
		}
		return s;
	}
	
	public String toString()
	{
		String s = "\nName: "+getName()+"\nPreArranged: "+getpreArranged()+"\nPreferences: "+getPrefs();
		return s;
	}
	
	public static void main(String[] args)
	{
		Person test = new Person();
		test.setName("Conor");
		test.setPreArranged("No");
		test.addPref("Project 1");
		
		System.out.println(test);
		
	}

}

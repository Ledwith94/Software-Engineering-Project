
public class Person 
{

	private String name;
	private String preArranged;
	private String[] preferences = new String[10];
	
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
	
	public void addPref(String input, int index)			// maybe int value for preference number
	{
		this.preferences[index] = input;
	}
	
	public String getPrefs()
	{
		String s = preferences[0];
		for(int i = 1; i < preferences.length; i++)
		{
			s += " " + preferences[i] + ",";
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
		test.addPref("Project 1", 1);
		
		System.out.println(test);
		
	}

}

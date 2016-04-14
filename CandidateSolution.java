import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

public class CandidateSolution 
{
	private PreferenceTable prefTable;
	private static Random RND = new Random();
	private int energy;
	private int projAllocationEnergy;

	private HashMap<String, CandidateAssignment> map = new HashMap<String, CandidateAssignment>();
	
	public HashMap<String, CandidateAssignment> getMap()
		{return this.map;}
	
	public void updateMap(HashMap<String, CandidateAssignment> newMap)
		{this.map = newMap;}
	
	public PreferenceTable getTable()
		{return this.prefTable;}
	
	public void updateTable(PreferenceTable p)
		{this.prefTable = p;}
	
	public CandidateSolution copySol()
		{CandidateSolution copyTo = new CandidateSolution(null);
		 copyTo.updateMap(this.getMap());
		 copyTo.updateTable(this.prefTable);
		 copyTo.calcTotalEnergy();
		 return copyTo;}

	public CandidateSolution(PreferenceTable table)									// initialize method
		{this.prefTable = table;
		 if(!(table == null))
		 	{for(int i = 0; i < table.getStudents().size() - 1; i++)
		 		{CandidateAssignment cand = new CandidateAssignment(table.getStudents().get(i));
		 		 this.addAssignment(cand);}
		 	}
		}
	
	public void addAssignment(CandidateAssignment cand)
		{this.map.put(cand.getStudentEntry().getName(), cand);}						// add cand assignment
	
	public CandidateAssignment getSolutionFor(String name)							// get candidate assignment 
		{if(this.map.containsKey(name)){return this.map.get(name);}							// return cand assignment if found
		 return null;}																// else return null 
	
	public CandidateAssignment getRandomAssignment()
		{List<String> keys = new ArrayList<String>(this.map.keySet());					// store string keys in a list
		 return map.get(keys.get(RND.nextInt(keys.size())));}						// choose a key at random
	
	public int calcTotalEnergy()
	{
		HashMap<String, Integer> projects = new HashMap<String, Integer>();
		int energy = 0;
		int projEnergy = 0;

		for (Entry<String, CandidateAssignment> entry : this.map.entrySet())
		{
			CandidateAssignment cand = entry.getValue();
		
			if(projects.containsKey(cand.getAssignedProject()))								// if project has been used before
		  		{int a = projects.get(cand.getAssignedProject());							// get the amount of times
		  		 projects.put(cand.getAssignedProject(), (a+1));}							// add 1
			else																			// if project not used yet
		  		{projects.put(cand.getAssignedProject(), 1);}								// add project with int 1
			
			energy += cand.getEnergy();
		}
		
		for(int value : projects.values()) 
			{if(value > 1){projEnergy += ((value-1)*100);}}			// if a project appears more than once, energy += 100*amount of times used more than once
		
		this.projAllocationEnergy = projEnergy;
		this.energy = energy + projEnergy;
		
		return this.energy;
	}
		
	public int getEnergy()
		{return this.energy;}
	
	public int getAllocEnergy()
		{return this.projAllocationEnergy;}
	
	public int getFitness()
		{return this.getEnergy() * -1;}
	
}

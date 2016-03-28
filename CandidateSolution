import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class CandidateSolution 
{
	@SuppressWarnings("unused")
	private PreferenceTable prefTable;
	private static Random RND = new Random();
	private int energy;
	private int projAllocationEnergy;

	HashMap<String, CandidateAssignment> map = new HashMap<String, CandidateAssignment>();
	HashMap<String, Integer> projects = new HashMap<String, Integer>();

	public void initialize(PreferenceTable table)									// initialize method
		{this.prefTable = table;}
	
	public void addAssignment(CandidateAssignment cand)
	 {this.map.put(cand.getStudentEntry().getName(), cand);							// add cand assignment
	  if(projects.containsKey(cand.getAssignedProject()))							// if project has been used before
	  	{int a = projects.get(cand.getAssignedProject());							// get the amount of times
	  	 projects.put(cand.getAssignedProject(), (a+1));}							// add 1
	  else																			// if project not used yet
	  	{projects.put(cand.getAssignedProject(), 1);}								// add project with int 1
	  this.addEnergy(cand.getEnergy());
	 }
	
	public CandidateAssignment getSolutionFor(String name)							// get candidate assignment 
		{if(map.containsKey(name)){return map.get(name);}							// return cand assignment if found
		 return null;}																// else return null 
	
	public CandidateAssignment getRandomAssignment()
		{List<String> keys = new ArrayList<String>(map.keySet());					// store string keys in a list
		 return map.get(keys.get(RND.nextInt(keys.size())));}						// choose a key at random
	
	public void addEnergy(int e)
		{this.energy += e;}
	
	public void addProjAllocationEnergy()
		{for(int value : projects.values()) 
			{if(value > 1){this.projAllocationEnergy += ((value-1)*100);}}			// if a project appears more than once, energy += 100*amount of times used more than once
		 this.addEnergy(this.getAllocEnergy());}
		
	public int getEnergy()
		{return this.energy;}
	
	public int getAllocEnergy()
		{return this.projAllocationEnergy;}
	
	public int getFitness()
		{return this.getEnergy() * -1;}
}

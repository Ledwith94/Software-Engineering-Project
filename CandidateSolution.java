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
	private HashMap<String, Integer> projectsCount = new HashMap<String, Integer>();

	public CandidateSolution(PreferenceTable table)													// initialize method
		{this.prefTable = table;
		 if(!(table == null))
		 	{for(int i = 0; i < table.getStudents().size() - 1; i++)
		 		{CandidateAssignment cand = new CandidateAssignment(table.getStudents().get(i));
		 		 this.addAssignment(cand);
		 		 
		 		 if(this.projectsCount.containsKey(cand.getAssignedProject()))							// if project has been used before
		  			{int a = this.projectsCount.get(cand.getAssignedProject());							// get the amount of times
		  			 this.projectsCount.put(cand.getAssignedProject(), (a+1));}							// add 1
		 		 else																				// if project not used yet
		  			{this.projectsCount.put(cand.getAssignedProject(), 1);}								// add project with int 1
		 		}
		 	}
		}
	
	public void addAssignment(CandidateAssignment cand)
		{this.map.put(cand.getStudentEntry().getName(), cand);}						// add cand assignment
	
	public CandidateAssignment getSolutionFor(String name)							// get candidate assignment 
		{if(this.map.containsKey(name)){return this.map.get(name);}					// return cand assignment if found
		 return null;}																// else return null 
	
	public CandidateAssignment getRandomAssignment()
		{List<String> keys = new ArrayList<String>(this.map.keySet());				// store string keys in a list
		 return map.get(keys.get(RND.nextInt(keys.size())));}						// choose a key at random
		
	public int getEnergy()
		{return this.energy;}
	
	public int getProjAllocEnergy()
		{return this.projAllocationEnergy;}
	
	public int getFitness()
		{return this.getEnergy() * -1;}
	
	public void recalcProjectCount()
		{	
			HashMap<String, Integer> projects = new HashMap<String, Integer>();
	
			for (Entry<String, CandidateAssignment> entry : this.map.entrySet())
			{
				CandidateAssignment cand = entry.getValue();
			
				if(projects.containsKey(cand.getAssignedProject()))								// if project has been used before
			  		{int a = projects.get(cand.getAssignedProject());							// get the amount of times
			  		 projects.put(cand.getAssignedProject(), (a+1));}							// add 1
				else																			// if project not used yet
			  		{projects.put(cand.getAssignedProject(), 1);}								// add project with int 1
			}
			this.projectsCount = projects;
		}

	public int calcTotalEnergy()
		{
			int energy = 0;
			int projEnergy = 0;
				
			for(CandidateAssignment cand : map.values()) 
				{energy += cand.getEnergy();}
			
			for(int value : projectsCount.values()) 
				{if(value > 1){projEnergy += ((value-1)*100);/*System.out.println("\n\t\tAdding "+ ((value-1)*100) +" proj energy, now = "+ projEnergy);*/}}			// if a project appears more than once, energy += 100*amount of times used more than once
			
			this.projAllocationEnergy = projEnergy;
			this.energy = energy + projEnergy;
			
			return this.energy;
		}
	
	public void makeRandomChange()
		{
			int oldEnergy = this.getEnergy();
			
			CandidateAssignment cand = this.getRandomAssignment();
			cand.randomizeAssignment();
			
			map.put(cand.getStudentEntry().getName(), cand);// update map
			
			this.recalcProjectCount();						// recalc project collisions
			this.calcTotalEnergy();							// recalc total energy
			
			if(this.getEnergy() > oldEnergy)
				{cand.undoChange();									// undo change 
				 map.put(cand.getStudentEntry().getName(), cand); 	// update map
				 this.recalcProjectCount();							// update project collisions
				 this.calcTotalEnergy();}							// update total energy
		}
	
	public void SimulatedAnnealing()
		{		
			System.out.print("\nBeginning Simulated Annealing\n\tRandom Moves Made:\n0 ");

			for(int i = 0; i < 1000000; i++)
			{
				this.makeRandomChange();
			 
			 if(i != 0 && i%12500 == 0) {System.out.print(i+" ");}		// for printing progress
			 if(i != 0 && i%250000 == 0) {System.out.print("\n");}
			}
		
			System.out.println("1000000\n\nSimulated Annealing Complete\n");
		
			System.out.println(this.toString());		
		}

	public void removeCollisions()		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	{
		calcTotalEnergy();
		
		ArrayList<String> collidingProjects = new ArrayList<String>();
		ArrayList<String> unassignedProjects = new ArrayList<String>();
		ArrayList<String> allChoiceArray = this.prefTable.getChoices();
		ArrayList<CandidateAssignment> collidingStudents = new ArrayList<CandidateAssignment>();

		
		for (Entry<String, Integer> entry : projectsCount.entrySet())		// sort through project count
		{
			String proj = entry.getKey();
			int amount = entry.getValue();
						
			if(amount > 1) {collidingProjects.add(proj);}		// record which projects are colliding
		}
		
		for(int i = 0; i < allChoiceArray.size(); i++)			// sort through all choices
		{
			String proj = allChoiceArray.get(i);
			if(!projectsCount.containsKey(proj)) {unassignedProjects.add(proj);}	// record unassigned project choices
		}
		
		for (Entry<String, CandidateAssignment> entry : map.entrySet())				// sort through CandidateAssignment map
		{
			CandidateAssignment cand = entry.getValue();
			if(collidingProjects.contains(cand.getAssignedProject()))
				{collidingStudents.add(cand);System.out.println("\n\t\tAdding "+cand.getStudentEntry().getName()+" for "+ cand.getAssignedProject());}					// record the students with the collisions
		}
		
		System.out.println("\n"+ collidingStudents.size() + " colliding students\n\n");
		
		for(int i = 0; i < collidingStudents.size(); i++)				// get rid of colliding projects with the students
		{
			CandidateAssignment cand = collidingStudents.get(i);

			int temp = RND.nextInt(unassignedProjects.size());
			String P = unassignedProjects.get(temp);
			cand.setProject(P);
			unassignedProjects.remove(temp);
				
			//System.out.println("\n\t\tUpdating "+cand.getStudentEntry().getName()+" to "+cand.getAssignedProject() +"\n");
			map.put(cand.getStudentEntry().getName(), cand);			// update cand in the CandidateAssignments map
		}
		
		this.recalcProjectCount();
		this.calcTotalEnergy();
		System.out.println(this.toString());

	}
	
	public String toString()
	{
		String s = "";
		for (Entry<String, CandidateAssignment> entry : map.entrySet())				// sort through CandidateAssignment map
			{s += entry.getValue().toString();}
		return s;
	}
	
}

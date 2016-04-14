import java.util.HashMap;


public class SimulatedAnnealing 
{
	public CandidateSolution makeRandomChange(CandidateSolution solution)
	{
		CandidateSolution temp = solution;
		HashMap<String, CandidateAssignment> tempMap = temp.getMap();

		CandidateAssignment cand = temp.getRandomAssignment();
		cand.randomizeAssignment();
		String name = cand.getStudentEntry().getName();
		tempMap.put(name, cand);
		temp.updateMap(tempMap);
		temp.calcTotalEnergy();
		
		return temp;		
	}

	public CandidateSolution anneal(CandidateSolution sol) throws InterruptedException
	{		
		CandidateSolution temp = null;
		CandidateSolution temp2 = null;
		CandidateSolution best = null;
		temp2 = sol.copySol();
		best = sol.copySol();
		int oldEnergy = sol.getEnergy();
		
		System.out.print("\nBeginning Simulated Annealing\n\tRandom Moves Made:\n0 ");

		for(int i = 0; i < 1000000; i++)
			{
			temp = makeRandomChange(temp2);
			 
			 if(temp.getEnergy() < oldEnergy)
			 	{
					 oldEnergy = temp.getEnergy();
				 	 temp2 = temp.copySol();		// update temp2
				 	 best = temp.copySol();			// update best
				}
			 
			 else
			 	{temp2 = best.copySol();}			// reset temp2 to best
			 
			 if(i != 0 && i%12500 == 0) {System.out.print(i+" ");}		// for printing progress
			 if(i != 0 && i%250000 == 0) {System.out.print("\n");}
			}
		
		System.out.println("1000000\n\nSimulated Annealing Complete\n");
		
		if(best.getEnergy() < sol.getEnergy())
			{return best;}
		return sol;
	}
	
	public CandidateSolution removeCollisions(CandidateSolution sol)
	{
		
		
		
		return null;
	}
	
}


/*

https://docs.oracle.com/javase/7/docs/api/javax/swing/JOptionPane.html

	Show a dialog asking the user to type in a String:
String inputValue = JOptionPane.showInputDialog("Please input a value");

	Show a dialog asking the user to select a String:
Object[] possibleValues = { "First", "Second", "Third" };
Object selectedValue = JOptionPane.showInputDialog(null,
"Choose one", "Input",
JOptionPane.INFORMATION_MESSAGE, null,
possibleValues, possibleValues[0]);


*/

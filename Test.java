import java.io.IOException;

import javax.swing.JOptionPane;


public class Test {

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		PreferenceTable prefs = new PreferenceTable("src/data.tsv");
		prefs.fillPreferencesOfAll(10);
		
		CandidateSolution best = new CandidateSolution(prefs);
		int bestScore = 1000000;
		
		for(int j = 0; j < 100000; j++)
		{
			CandidateSolution sol = new CandidateSolution(prefs);
			
			int solutionEnergy = sol.calcTotalEnergy();
			
			if(solutionEnergy < bestScore)
				{best = sol; bestScore = solutionEnergy;}
		}
		
		System.out.println("\nBest solution Energy = "+best.getEnergy()+"\nThis solution has "+best.getAllocEnergy()+" allocation energy and "+(best.getEnergy()-best.getAllocEnergy())+" choice energy\n");		
		
		SimulatedAnnealing SA = new SimulatedAnnealing();
		
		while(true)
		{
			CandidateSolution newBest = SA.anneal(best);
		
			System.out.println("\nBest Solution Energy generated = "+newBest.getEnergy()+"\nThis solution has "+newBest.getAllocEnergy()+" allocation energy and "+(newBest.getEnergy()-newBest.getAllocEnergy())+" choice energy\n");
			if(newBest.getEnergy() < best.getEnergy())	{best = newBest;}
			
			int choice = JOptionPane.showConfirmDialog(null,
					"Run Simulated Annealing again ?", "Do you want to run again ?", JOptionPane.YES_NO_OPTION);

			if(choice == 1){break;}

		}
		
		System.out.println("\n\n\tThe Overall Best solution was Energy = "+best.getEnergy()+"\nThis solution has "+best.getAllocEnergy()+" allocation energy and "+(best.getEnergy()-best.getAllocEnergy())+" choice energy\n");
		System.exit(0);
	}
}

//  Previous main tests
//	
//	StudentEntry test = prefs.students[0];
//	
//	System.out.println("Name: " + test.getName());
//	System.out.println("Pre-arranged: " + test.getPreArranged());
//	System.out.println("Preferences: \n" + test.getPrefs());
//	System.out.println("Stated Preferences: " + test.getStatedPrefs()+"\n");

//ArrayList<StudentEntry> list = getAllStudentEntries();
// System.out.println(list.toString());

//StudentEntry student = prefs.getEntryFor("Simone de Beauvoir");					// getStudentEntry
//
//if(student == null)
//	{System.out.println("Error, student entry null");}
//else
//	{System.out.println("\ngetEntryFor success for "+student);}
//
//student = prefs.getEntryFor("Red Sonja");
//
//if(student == null)
//	{System.out.println("Error, student entry null");}
//else
//	{System.out.println("\ngetEntryFor success for "+student);}



//student = prefs.getEntryFor("Simone de Beauvoir");					// getStudentEntry
//
//if(student == null)
//	{System.out.println("Error, student entry null");}
//else
//	{System.out.println("\ngetEntryFor success for "+student);}
//
//student = prefs.getEntryFor("Red Sonja");
//
//if(student == null)
//	{System.out.println("Error, student entry null");}
//else
//	{System.out.println("\ngetEntryFor success for "+student);}
//
////System.out.println("\n\n Hashtable: "+table.toString());
//		
//java.util.Collections.sort(prefs.choices);										// Alphabetize list of choices

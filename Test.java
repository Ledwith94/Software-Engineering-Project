import java.io.IOException;

import javax.swing.JOptionPane;


public class Test {

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		PreferenceTable prefs = new PreferenceTable("src/data.tsv");
		prefs.fillPreferencesOfAll(10);
		
		CandidateSolution best = new CandidateSolution(prefs);		// initialize solution
		best.calcTotalEnergy();
		
		System.out.println("\nBest solution Energy = "+best.getEnergy()+"\nThis solution has "+best.getProjAllocEnergy()+" allocation energy and "+(best.getEnergy()-best.getProjAllocEnergy())+" choice energy\n");		
		
		System.out.println("\n"+best.toString()+"\n");
		
		//////////////////////////////////////////////////
		
		GeneticAlgorithm GA = new GeneticAlgorithm(prefs);
		best = GA.mate();
		
		//////////////////////////////////////////////////
		
		int choice = 0;
		
		while(choice != 1)
		{
			best.SimulatedAnnealing();
		
			System.out.println("\nBest Solution Energy generated = "+best.getEnergy()+"\nThis solution has "+best.getProjAllocEnergy()+" allocation energy and "+(best.getEnergy()-best.getProjAllocEnergy())+" choice energy\n");
			
			choice = JOptionPane.showConfirmDialog(null,
					"Run Simulated Annealing again ?", "Do you want to run again ?", JOptionPane.YES_NO_OPTION);

			if(choice == 1){break;}
		}
		
		System.out.println("\n\n\tThe resulting solution was Energy = "+best.getEnergy()+"\nThis solution has "+best.getProjAllocEnergy()+" allocation energy and "+(best.getEnergy()-best.getProjAllocEnergy())+" choice energy\n");
		
		if(best.getProjAllocEnergy() != 0)
		{
			choice = JOptionPane.showConfirmDialog(null,
					"There are collisions in this solution ?", "Do you want to remove the collisions ?", JOptionPane.YES_NO_OPTION);
	
			if(choice == 0){System.out.println("\n\t\tRemoving Collisions\n"); best.removeCollisions();}
		}
		
		System.out.println("\n\n\tThe Overall Best solution was Energy = "+best.getEnergy()+"\nThis solution has "+best.getProjAllocEnergy()+" allocation energy and "+(best.getEnergy()-best.getProjAllocEnergy())+" choice energy\n");
		
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

import java.io.IOException;


public class Test {

	public static void main(String[] args) throws IOException 
	{
		PreferenceTable prefs = new PreferenceTable("src/data.tsv");
		prefs.fillPreferencesOfAll(10);
		
		CandidateSolution sol = new CandidateSolution();
		
		for(int i = 0; i < prefs.students.length - 1; i++)
			{CandidateAssignment cand = new CandidateAssignment(prefs.students[i]);
			 sol.addAssignment(cand); System.out.println(cand);}
		
		sol.addProjAllocationEnergy();
		System.out.println("\n\nSolution Energy = " + sol.getEnergy() + "\t\tPenalty for project collisions: " + sol.getAllocEnergy());
		System.out.println("Fitness = " + sol.getFitness());
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

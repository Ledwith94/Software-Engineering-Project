import java.io.IOException;
import javax.swing.JOptionPane;


public class Test {

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		PreferenceTable prefs = new PreferenceTable("src/data.tsv");
		prefs.fillPreferencesOfAll(10);
		
		CandidateSolution best = new CandidateSolution(prefs);				// initialize a random solution
		best.calcTotalEnergy();
		
		System.out.println("\nInitial solution Energy = "+best.getEnergy()+"\nThis solution has "+best.getProjAllocEnergy()+" allocation energy and "+(best.getEnergy()-best.getProjAllocEnergy())+" choice energy\n");		
				
		int cont = 0;
		int SAchoice = 0;
		int choice = 0;
		
		while(cont != 1)
		{		
			String[] options = new String[] {"Genetic Algorithm", "Simulated Annealing"};
		    int picked = JOptionPane.showOptionDialog(null, "Select Genetic Algorithm or Simulated Annealing", "Pick what method you wish to use",
		        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE,
		        null, options, options[0]);
			
			if(picked == 0)
			{
				GeneticAlgorithm GA = new GeneticAlgorithm(prefs);
				best = GA.mate();
				System.out.println("\n\n\tThe resulting solution was Energy = "+best.getEnergy()+"\nThis solution has "+best.getProjAllocEnergy()+" allocation energy and "+(best.getEnergy()-best.getProjAllocEnergy())+" choice energy\n");
			}
			
			else if(picked == 1)
			{
				while(SAchoice != 1)
				{
					best.SimulatedAnnealing();
				
					System.out.println("\nBest Solution Energy generated = "+best.getEnergy()+"\nThis solution has "+best.getProjAllocEnergy()+" allocation energy and "+(best.getEnergy()-best.getProjAllocEnergy())+" choice energy\n");
					
					SAchoice = JOptionPane.showConfirmDialog(null,
							"Run Simulated Annealing again ?", "Do you want to run again ?", JOptionPane.YES_NO_OPTION);
		
					if(SAchoice == 1){break;}
				}
			}
			
			else
				{System.out.println("Error, try again");}
			
			cont = JOptionPane.showConfirmDialog(null,
					"Run Simulated Annealing or Genetic Algorithm again ?", "Do you want to run Simulated Annealing or Genetic Algorithm again ?", JOptionPane.YES_NO_OPTION);
		}
		
		if(best.getProjAllocEnergy() != 0)
		{
			choice = JOptionPane.showConfirmDialog(null,
					"There are collisions in this solution ?", "Do you want to remove the collisions ?", JOptionPane.YES_NO_OPTION);
	
			if(choice == 0){System.out.println("\n\t\tRemoving Collisions\n"); best.removeCollisions();}
		}
		
		System.out.println("\n\n\tThe Overall Best solution was Energy = "+best.getEnergy()+"\nThis solution has "+best.getProjAllocEnergy()+" allocation energy and "+(best.getEnergy()-best.getProjAllocEnergy())+" choice energy\n");
		
		System.out.println("Generating results.tsv in the same folder as the program ...");
		
		best.toFile();
		
		System.exit(0);
	}
}

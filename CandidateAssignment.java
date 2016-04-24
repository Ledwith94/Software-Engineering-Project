import java.util.Random;
import java.math.*;

@SuppressWarnings("unused")
public class CandidateAssignment
{
	private StudentEntry student;
	private String oldProject;
	private String project;
	private int energy;
	private int index;
	private static Random RND = new Random();
	
	public CandidateAssignment(StudentEntry s)				// initialize method 
		{this.student = s;
		 randomizeAssignment();}							// assign random assignment
	
	public void randomizeAssignment()
		{if(this.oldProject != null)							// if a previous project mapping
		 	{this.oldProject = this.project;}					// save old mapping
		 this.project = this.student.getRandomPreference();		// make new mapping
		 this.index = this.student.getRanking(this.project);	// record choice index
		 this.energy = this.index;}								// energy = index
	
	public StudentEntry getStudentEntry()					// get student entry from candidate assignment
		{return this.student;}
	
	public String getAssignedProject()						// get assigned project for this assignment 
		{return this.project;}
	
	public void setProject(String s)						// forcably assign a project
		{if(this.student.hasPreference(s))
			{
				this.project = s;
				int temp = this.student.getIndexOfProject(s);
				if(temp > 0) {this.index = temp;}			// if found, get index
				else {this.index = 10;}						// else set index to 10
			}
			
			else											// student doesn't have project
			{
				this.project = s;
				this.index = 10;
				this.energy = this.index;
			}
		}
	
	public int getEnergy()
		{return this.energy;}
	
	public void undoChange()
		{String temp = this.project;
			if(this.oldProject != null)
				{this.project = this.oldProject;}			// revert to previous project mapping
		 this.oldProject = temp;}							// store old mapping
	
	public String toString()								// to print out nicely 
		{String s = "";
		 s += this.getStudentEntry().getName();
		 if(this.getStudentEntry().getName().length() < 8) {s += "\t\t\t";}
		 else if(this.getStudentEntry().getName().length() < 16) {s += "\t\t";}
		 else {s += "\t";}
		 s += this.getAssignedProject();
		 s += "\n";
		 return s;}
}

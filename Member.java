import java.util.ArrayList;

public class Member
{
	// fields
	private String name;
	private int age;
	private String activity;
	private String ambition;
	private String disciplines;
	
	// ****************************
	// CONSTRUCTORS
	// ****************************
	public Member (String name, int age, String activity, String ambition)
	{
		this.name = name;
		this.age = age;
		this.activity = activity;
		this.ambition = ambition;
	}
	
	public Member (String name, int age, String activity, String ambition, String disciplines)
	{
		this.name = name;
		this.age = age;
		this.activity = activity;
		this.ambition = ambition;
		this.disciplines = disciplines;
	}
	
	// ********************************
	// ACCESSORS - GET METHODS
	//*********************************
	public String getName()
	{
		return name;
	}
	
	public int getAge()
	{
		return age;
	}
	
	public String getActivity()
	{
		return activity;
	}
	
	public String getAmbition()
	{
		return ambition;
	}
	
	public String getDisciplines()
	{
		return disciplines;
	}
	
	public String toString()
	{
		if (disciplines == null)
		{
			return getName() + ";" + getAge() + ";" + getActivity() + ";" + getAmbition(); 
		}
		else
		{
			return getName() + ";" + getAge() + ";" + getActivity() + 
					";" + getAmbition() + ";" + getDisciplines();
		}
	}
	
	// ********************************
	// MUTATORS - SET METHODS
	// ********************************
	public void setDisciplines(String disciplines)
	{
		this.disciplines = disciplines;
	}
	
}

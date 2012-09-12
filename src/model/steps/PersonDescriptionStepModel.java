package model.steps;

import gui.steps.PersonDescriptionStep;

import java.util.ArrayList;


public class PersonDescriptionStepModel extends InformationGatherStepModel 
{
	private static PersonDescriptionStepModel instance;
	
	private final static String[] VALID_GROUPS = {
		"single",
		"family"
	};
	
	private String age;
	private String group;
	
	private PersonDescriptionStepModel() 
	{
		super("Persönlich", new PersonDescriptionStep());
	}
	
	public static PersonDescriptionStepModel getInstance() 
	{
		if(instance != null)
			return instance;
		
		instance = new PersonDescriptionStepModel();
		return instance;
	}
	
	public String getError() {
		
		if(!hasValidAge())
			return "Ungültiges Alter";
		
		if(!hasValidGroup())
			return "Ungültige Gruppe";
		
		return null;
	}
	
	public boolean hasValidAge()
	{	
		return age != null && (
				age.equals("Child") || 
				age.equals("Teenager") || 
				age.equals("YoungAdults") ||
				age.equals("Adults") ||
				age.equals("OldAdults"));
	}
	
	public boolean hasValidGroup()
	{
		for(int i=0; i<VALID_GROUPS.length; i++) {
			if(VALID_GROUPS[i].equals(group)) return true;
		}
		return false;
	}

	public String getAge() 
	{
		return age;
	}

	public void setAge(String age) 
	{
		this.age = age;
		updateAlredayFilled();
	}

	public String getGroup() 
	{
		return group;
	}

	public void setGroup(String group) 
	{
		this.group = group;
		updateAlredayFilled();
	}
	
	public boolean isAdult()
	{
		return (age.equals("YoungAdults") ||
				age.equals("Adults") ||
				age.equals("OldAdults"));
	}
}

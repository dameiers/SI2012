package model.steps;

import java.util.ArrayList;


public class PersonDescriptionStep extends InformationGatherStep 
{
	private static PersonDescriptionStep instance;
	
	private final static String[] VALID_GROUPS = {
		"single",
		"family"
	};
	
	private int age;
	private String group;
	
	private PersonDescriptionStep() 
	{
		
	}
	
	public static PersonDescriptionStep getInstance() 
	{
		return instance != null ? instance : (instance=new PersonDescriptionStep());
	}
	
	public String getError() {
		
		if(!hasValidAge())
			return "UngŸltiges Alter";
		
		if(!hasValidGroup())
			return "UngŸltige Gruppe";
		
		return null;
	}
	
	public boolean hasValidAge()
	{	
		return age > 0 && age < 99;
	}
	
	public boolean hasValidGroup()
	{
		for(int i=0; i<VALID_GROUPS.length; i++) {
			if(VALID_GROUPS[i].equals(group)) return true;
		}
		return false;
	}

	public int getAge() 
	{
		return age;
	}

	public void setAge(int age) 
	{
		this.age = age;
	}
	
	public void setAge(String age)
	{
		setAge(Integer.parseInt(age));
	}

	public String getGroup() 
	{
		return group;
	}

	public void setGroup(String group) 
	{
		this.group = group;
	}
}

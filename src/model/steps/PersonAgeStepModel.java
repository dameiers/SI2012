package model.steps;

import gui.steps.PersonAgeStep;

public class PersonAgeStepModel extends InformationGatherStepModel 
{
	private static PersonAgeStepModel instance;
	
	private int[] ages;
	
	private PersonAgeStepModel() 
	{
		super("Personen", new PersonAgeStep());
	}
	
	public static PersonAgeStepModel getInstance() 
	{
		if(instance != null)
			return instance;
		
		instance = new PersonAgeStepModel();
		return instance;
	}

	public String getError() 
	{
		if(ages.length == 0)
			return "Ungültige Anzahl von Altersangaben";
		
		return null;
	}
	
	public boolean hasAdultPerson()
	{
		if(ages == null) 
			return false;
		
		for(int i=0; i<ages.length; i++)
		{
			if(ages[i] >= 18) {
				return true;
			}
		}
		return false;
	}

	public int[] getAges() 
	{
		return ages;
	}

	public void setAges(String[] ages) 
	{
		this.ages = new int[ages.length];
		
		for(int i=1; i<ages.length; i++) {
			this.ages[i] = Integer.parseInt(ages[i]);
		}
		updateAlredayFilled();
	}
	
	public void setAges(int[] ages)
	{
		this.ages = ages;
		updateAlredayFilled();
	}
}

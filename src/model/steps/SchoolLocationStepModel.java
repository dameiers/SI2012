package model.steps;

import gui.steps.SchoolLocationStep;

public class SchoolLocationStepModel extends InformationGatherStepModel 
{
	private static SchoolLocationStepModel instance;
	
	private String[] locations;
	
	private SchoolLocationStepModel() 
	{
		super("Schulort", new SchoolLocationStep());
	}
	
	public static SchoolLocationStepModel getInstance() 
	{
		return instance != null ? instance : (instance=new SchoolLocationStepModel());
	}
	
	public String getError() 
	{
		if(locations.length == 0)
			return "Ung�ltige Anzahl von Orten";
		
		return null;
	}

	public String[] getLocations() 
	{
		return locations;
	}

	public void setLocations(String[] locations) 
	{
		this.locations = locations;
		updateAlredayFilled();
	}

}

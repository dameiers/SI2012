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
	
	public void printToConsole()
	{
		System.out.println(locations);
	}
	
	public static SchoolLocationStepModel getInstance() 
	{
		if(instance != null)
			return instance;
		
		instance = new SchoolLocationStepModel();
		return instance;
	}
	
	public String getError() 
	{
		if(locations == null || locations.length == 0)
			return "Ungültige Anzahl von Orten";
		
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

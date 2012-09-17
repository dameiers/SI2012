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
		String str = "";
		for(String tmp : locations)
		{
			str += tmp + " ";
		}
		System.out.println(str);
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
		PersonAgeStepModel pasm = PersonAgeStepModel.getInstance();
		
		if(locations == null || 
		   locations.length == 0 ||
		   locations.length > pasm.getPupils().size())
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

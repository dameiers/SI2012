package model.steps;

public class SchoolLocationStep extends InformationGatherStep 
{
	private static SchoolLocationStep instance;
	
	private String[] locations;
	
	private SchoolLocationStep() 
	{
		
	}
	
	public static SchoolLocationStep getInstance() 
	{
		return instance != null ? instance : (instance=new SchoolLocationStep());
	}
	
	public String getError() 
	{
		if(locations.length == 0)
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
	}

}

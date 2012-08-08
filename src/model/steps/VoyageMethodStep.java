package model.steps;

public class VoyageMethodStep extends InformationGatherStep 
{
	private static VoyageMethodStep instance;
	
	private VoyageMethodStep() 
	{
		
	}
	
	public static VoyageMethodStep getInstance() 
	{
		return instance != null ? instance : (instance=new VoyageMethodStep());
	}

	
	public String getError() 
	{
		return null;
	}
	
	public boolean byCar() 
	{
		return true;
	}
}

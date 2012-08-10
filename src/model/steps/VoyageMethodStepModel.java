package model.steps;

public class VoyageMethodStepModel extends InformationGatherStepModel 
{
	private static VoyageMethodStepModel instance;
	
	private VoyageMethodStepModel() 
	{
		
	}
	
	public static VoyageMethodStepModel getInstance() 
	{
		return instance != null ? instance : (instance=new VoyageMethodStepModel());
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

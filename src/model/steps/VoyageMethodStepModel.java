package model.steps;

public class VoyageMethodStepModel extends InformationGatherStepModel 
{
	private static VoyageMethodStepModel instance;
	
	private boolean byCar;
	
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
	
	public void setByCar(boolean byCar)
	{
		this.byCar = byCar;
		updateAlredayFilled();
	}
	
	public boolean byCar() 
	{
		return byCar;
	}
}

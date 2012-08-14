package model.steps;

import gui.steps.VoyageMethodStep;

public class VoyageMethodStepModel extends InformationGatherStepModel 
{
	private static VoyageMethodStepModel instance;
	
	private boolean byCar;
	
	private VoyageMethodStepModel() 
	{
		super("Verkehrsmittel", new VoyageMethodStep());
	}
	
	public static VoyageMethodStepModel getInstance() 
	{
		if(instance != null)
			return instance;
		
		instance = new VoyageMethodStepModel();
		return instance;
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

package model.steps;

import gui.steps.OriginLocationStep;

public class OriginLocationStepModel extends InformationGatherStepModel 
{
	private static OriginLocationStepModel instance;
	
	private String origin;
	
	private OriginLocationStepModel() 
	{
		super("Abfahrt", new OriginLocationStep());
	}
	
	public static OriginLocationStepModel getInstance() 
	{
		return instance != null ? instance : (instance=new OriginLocationStepModel());
	}
	
	public String getError() 
	{
		if(!hasValidOrigin())
			return "ungŸltiger Ort";
		
		return null;
	}
	
	public boolean hasValidOrigin()
	{
		return origin != null && origin.length() > 0;
	}

	public String getOrigin() 
	{
		return origin;
	}

	public void setOrigin(String origin) 
	{
		this.origin = origin;
		updateAlredayFilled();
	}
	
	
}

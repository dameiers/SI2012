package model.steps;

import gui.steps.DurationStep;

public class DurationStepModel extends InformationGatherStepModel 
{
	private static DurationStepModel instance;
	
	private String duration;

	private DurationStepModel() 
	{
		super("Dauer", new DurationStep());
	}
	
	public static DurationStepModel getInstance() 
	{
		return instance != null ? instance : (instance=new DurationStepModel());
	}

	
	public String getError() 
	{
		if(!hasValidDuration())
			return "Ungüliger Zeitraum";
		
		return null;
	}
	
	public boolean hasValidDuration()
	{
		return "oneDay".equals(duration) || "weekend".equals(duration);
	}

	public String getDuration() 
	{
		return duration;
	}

	public void setDuration(String duration) 
	{
		this.duration = duration;
		updateAlredayFilled();
	}
}

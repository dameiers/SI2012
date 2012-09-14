package model.steps;

import gui.steps.DurationStep;

public class DurationStepModel extends InformationGatherStepModel 
{
	public final static String ONE_DAY_DURATION = "OneDayEvent";
	public final static String WEEKEND_DURATION = "MultipleDaysEvent";
	private static DurationStepModel instance;
	private String duration;

	private DurationStepModel() 
	{
		super("Dauer", new DurationStep());
	}
	
	public static DurationStepModel getInstance() 
	{
		if(instance != null)
			return instance;
		
		instance = new DurationStepModel();
		return instance;
	}

	
	public String getError() 
	{
		if(!hasValidDuration())
			return "Ungueliger Zeitraum";
		
		return null;
	}
	
	public boolean hasValidDuration()
	{
		return ONE_DAY_DURATION.equals(duration) || WEEKEND_DURATION.equals(duration);
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

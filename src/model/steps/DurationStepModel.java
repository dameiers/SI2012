package model.steps;

public class DurationStepModel extends InformationGatherStepModel 
{
	public final static String ONE_DAY_DURATION = "one_day";
	public final static String WEEKEND_DURATION = "multiple_days";
	private static DurationStepModel instance;
	private String duration;

	private DurationStepModel() 
	{
		
	}
	
	public static DurationStepModel getInstance() 
	{
		return instance != null ? instance : (instance=new DurationStepModel());
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

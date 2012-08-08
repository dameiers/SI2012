package model.steps;

public class DurationStep extends InformationGatherStep 
{
	private static DurationStep instance;
	
	private String duration;

	private DurationStep() 
	{
		
	}
	
	public static DurationStep getInstance() 
	{
		return instance != null ? instance : (instance=new DurationStep());
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
	}
}

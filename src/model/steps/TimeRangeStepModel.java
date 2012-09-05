package model.steps;

public class TimeRangeStepModel extends InformationGatherStepModel 
{
	private static TimeRangeStepModel instance;

	private final static String[] VALID_TIME_RANGE_TYPES = {
		"summerbreak", 
		"autumbreak", 
		"easterbreak",
		"winterbreak",
		"misc"		
	};
	
	private final static String VALID_DATE = "[0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]";
	
	private String timeRangeTyp;
	private String fromDate;
	private String toDate;
	
	
	private TimeRangeStepModel() 
	{
		
	}
	
	public static TimeRangeStepModel getInstance() 
	{
		return instance != null ? instance : (instance=new TimeRangeStepModel());
	}

	public String getError() 
	{	
		if(!hasValidMiscTimeRange())
			return "Ungültiges von/bis Datum";
		if(!hasValidTimeRange())
			return "Ungültiger Zeitraum";
		
		return null;
	}
	
	public boolean hasValidMiscTimeRange()
	{
		if(timeRangeTyp.equals("misc")){
			return fromDate != null && fromDate.matches(VALID_DATE) &&
				   toDate != null && toDate.matches(VALID_DATE);	   
		}
		
		return true;
	}
	
	public boolean hasValidTimeRange()
	{
		for(int i=0; i<VALID_TIME_RANGE_TYPES.length; i++) {
			if(VALID_TIME_RANGE_TYPES[i].equals(timeRangeTyp)) return true;
		}
		return false;
	}
	
	public String getTimeRangeTyp() 
	{
		return timeRangeTyp;
	}

	public void setTimeRangeTyp(String timeRangeTyp) 
	{
		this.timeRangeTyp = timeRangeTyp;
		updateAlredayFilled();
	}

	public String getFromDate() 
	{
		if(timeRangeTyp.equals("autumbreak")){
			return "2012-10-22";
		}
		else if(timeRangeTyp.equals("winterbreak")){
			return "2012-12-24";
		}
		else if(timeRangeTyp.equals("easterbreak")){
			return "2013-03-25";
		}
		else if(timeRangeTyp.equals("summerbreak")){
			return "2013-07-08";
		}
		else if(timeRangeTyp.equals("misc")){
			return fromDate;
		}
		return null;
	}

	public void setFromDate(String fromDate) 
	{
		this.fromDate = fromDate;
		updateAlredayFilled();
	}

	public String getToDate() 
	{
		if(timeRangeTyp.equals("autumbreak")){
			return "2012-11-03";
		}
		else if(timeRangeTyp.equals("winterbreak")){
			return "2013-01-05";
		}
		else if(timeRangeTyp.equals("easterbreak")){
			return "2013-04-06";
		}
		else if(timeRangeTyp.equals("summerbreak")){
			return "2013-08-17";
		}
		else if(timeRangeTyp.equals("misc")){
			return toDate;
		}
		return null;
		
	}

	public void setToDate(String toDate) 
	{
		this.toDate = toDate;
		updateAlredayFilled();
	}
}

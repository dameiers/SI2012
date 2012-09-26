package model.steps;

import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import gui.steps.TimeRangeStep;

public class TimeRangeStepModel extends InformationGatherStepModel 
{
	private static TimeRangeStepModel instance = new TimeRangeStepModel();

	public final static String SUMMERBREAK_TIME_RANGE = "summerbreak";
	public final static String AUTUMNBREAK_TIME_RANGE = "autumnbreak";
	public final static String EASTERBREAK_TIME_RANGE = "easterbreak";
	public final static String WINTERBREAK_TIME_RANGE = "winterbreak";
	public final static String MISC_TIME_RANGE = "misc";
	
	
	private String timeRangeTyp;
	private GregorianCalendar fromDate;
	private GregorianCalendar toDate;
	private boolean validdate;
	
	
	private TimeRangeStepModel() 
	{
		super("Zeitrahmen", new TimeRangeStep());
		timeRangeTyp = null;
	}
	
	public boolean inSchoolBreak()
	{
		return hasValidTimeRange() &&
			   (timeRangeTyp == SUMMERBREAK_TIME_RANGE ||
			    timeRangeTyp == AUTUMNBREAK_TIME_RANGE ||
			    timeRangeTyp == EASTERBREAK_TIME_RANGE || 
			    timeRangeTyp == WINTERBREAK_TIME_RANGE); 
	}
	
	public static TimeRangeStepModel getInstance() 
	{
		System.out.println(instance);
		
		if(instance == null) {
			instance = new TimeRangeStepModel();
		}
		
		return instance;
	}

	public String getError() 
	{	
		if(!hasValidTimeRange())
			return "Ungueltiger Zeitraum";
		
		if(timeRangeTyp.equals(MISC_TIME_RANGE) && !validdate)
			return "Falsches Datums Format";
		
		if (fromDate.after(toDate)){
			return "Das Enddatum muss groesser oder gleich als das Startdatum sein.";
		}
		return null;
	}
	
	public void setValidDate(boolean b){
		
		this.validdate = b;
	}
	
	
	public boolean getValidDate()
	{
		
		
		return validdate;
	}
	
	
	public boolean hasValidTimeRange()
	{
		System.out.println(timeRangeTyp);
		if(timeRangeTyp != null && (timeRangeTyp.equals(AUTUMNBREAK_TIME_RANGE) || 
				timeRangeTyp.equals(WINTERBREAK_TIME_RANGE) || 
				timeRangeTyp.equals(SUMMERBREAK_TIME_RANGE) ||
				timeRangeTyp.equals(EASTERBREAK_TIME_RANGE)||
				timeRangeTyp.equals(MISC_TIME_RANGE))){
			return true;
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
		if(timeRangeTyp != MISC_TIME_RANGE){
			fillTimeRangeDates();
		}
		updateAlredayFilled();
	}

	private void fillTimeRangeDates() {
		if(timeRangeTyp.equals(SUMMERBREAK_TIME_RANGE)){
			fromDate = new GregorianCalendar(2013, 6, 6);
			toDate = new GregorianCalendar(2013,7,18);
		}else if(timeRangeTyp.equals(WINTERBREAK_TIME_RANGE)){
			fromDate = new GregorianCalendar(2012, 11, 22);
			toDate = new GregorianCalendar(2013,0,6);			
		}else if(timeRangeTyp.equals(EASTERBREAK_TIME_RANGE)){
			fromDate = new GregorianCalendar(2013, 2, 23);
			toDate = new GregorianCalendar(2013,3,7);
		}else{
			fromDate = new GregorianCalendar(2012, 9, 20);
			toDate = new GregorianCalendar(2012,10,4);
		}
	}

	public GregorianCalendar getFromDate() 
	{
		return fromDate;
	}

	public void setFromDate(GregorianCalendar fromDate) 
	{
		this.fromDate = fromDate;
		updateAlredayFilled();
	}

	public GregorianCalendar getToDate() 
	{
		return toDate;
	}

	public void setToDate(GregorianCalendar toDate) 
	{
		this.toDate = toDate;
		updateAlredayFilled();
	}

	@Override
	public void printToConsole() {
		System.out.println("from Date: " + getFromDate().getTime());
		System.out.println("to Date: " + getToDate().getTime());
	}
}

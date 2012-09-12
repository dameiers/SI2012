package model.steps;

import gui.steps.DistanceStep;


public class DistanceStepModel extends InformationGatherStepModel 
{
	private static DistanceStepModel instance;
	public final static String HOUR_UNIT ="h";
	public final static String DISTANCE_UNTI ="km";
	
	private String unit;
	private String distance;
	
	private DistanceStepModel() 
	{
		super("Distanz", new DistanceStep());
	}
	
	public static DistanceStepModel getInstance() 
	{
		if(instance != null)
			return instance;
		
		instance = new DistanceStepModel();
		return instance;
	}
	
	public String getError() {
		
		if(!hasValidDistance())
			return "Ungültige Entfernung";
		if(!hasValidUnit())
			return "Ungültige Einheit";
		
		return null;
	}
	
	public boolean hasValidDistance()
	{
		return distance != null && distance.matches("[0-9]+(,[0-9]+)?");
	}
	
	public boolean hasValidUnit()
	{
		return "h".equals(unit) || "km".equals(unit);
	}

	public String getUnit() 
	{
		return unit;
	}

	public void setUnit(String unit) 
	{
		this.unit = unit;
		updateAlredayFilled();
	}

	public String getDistance() 
	{
		return distance;
	}

	public void setDistance(String distance) 
	{
		this.distance = distance;
		updateAlredayFilled();
	}

}

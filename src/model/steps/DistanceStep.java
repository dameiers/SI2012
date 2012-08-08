package model.steps;


public class DistanceStep extends InformationGatherStep 
{
	private static DistanceStep instance;
	
	private String unit;
	private String distance;
	
	
	private DistanceStep() 
	{
		
	}
	
	public static DistanceStep getInstance() 
	{
		return instance != null ? instance : (instance=new DistanceStep());
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
	}

	public String getDistance() 
	{
		return distance;
	}

	public void setDistance(String distance) 
	{
		this.distance = distance;
	}

}

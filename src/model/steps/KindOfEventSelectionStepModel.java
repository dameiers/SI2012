package model.steps;

import gui.steps.KindOfEventSelectionStep;

public class KindOfEventSelectionStepModel extends InformationGatherStepModel 
{
	public static final String LIKE = "like";
	public static final String DONTLIKE = "notlike";
	public static final String MAYBE = "maybe";
	
	private static KindOfEventSelectionStepModel instance;

	private String cultureStatus;
	private String sportStatus;
	private String leisureTimeStatus;
	
	private KindOfEventSelectionStepModel() 
	{
		super("Art", new KindOfEventSelectionStep());
	}
	
	public static KindOfEventSelectionStepModel getInstance() 
	{
		if(instance != null)
			return instance;
		
		instance = new KindOfEventSelectionStepModel();
		return instance;
	}
	
	public String getError() 
	{
		if(!hasValidCultureStatus())
			return "Ungültige Angabe für Kultur";
		
		if(!hasValidLeisureTimeStatus())
			return "Ungültige Angabe für Freizeit";
		
		if(!hasValidSportStatus())
			return "Ungültige Angabe für Sport";
		
		return null;
	}
	
	public boolean hasValidCultureStatus()
	{
		return isValidAttribute(cultureStatus);
	}
	
	public boolean hasValidSportStatus()
	{
		return isValidAttribute(sportStatus);
	}
	
	public boolean hasValidLeisureTimeStatus()
	{
		return isValidAttribute(leisureTimeStatus);
	}
	
	private boolean isValidAttribute(String att)
	{
		if(att == null)
			return false;
		
		return att == LIKE ||
			   att == DONTLIKE ||
			   att == MAYBE;
	}

	public String getCultureStatus() {
		return cultureStatus;
	}

	public void setCultureStatus(String cultureStatus) 
	{
		this.cultureStatus = cultureStatus;
		updateAlredayFilled();
	}

	public String getSportStatus() 
	{
		return sportStatus;
	}

	public void setSportStatus(String sportStatus) 
	{
		this.sportStatus = sportStatus;
		updateAlredayFilled();
	}

	public String getLeisureTimeStatus() 
	{
		return leisureTimeStatus;
	}

	public void setLeisureTimeStatus(String leisureTimeStatus) 
	{
		this.leisureTimeStatus = leisureTimeStatus;
		updateAlredayFilled();
	}
}

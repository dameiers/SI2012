package model.steps;

import gui.steps.EventCategorySelectionStep;

import java.util.HashMap;

public class EventCategoryStepModel extends InformationGatherStepModel 
{
	private static EventCategoryStepModel instance;
	
	private HashMap<String, String> leisureTimeCategories;
	private HashMap<String, String> sportCategories;
	private HashMap<String, String> cultureCategories;
	
	private EventCategoryStepModel() 
	{
		super("Kategorie", new EventCategorySelectionStep(true, true, true));
	}
	
	public void setPreselection()
	{
		//TODO
		//PersonAgeStepModel personAgeStepModel = PersonAgeStepModel.getInstance();
		
	}
	
	public static EventCategoryStepModel getInstance() 
	{
		if(instance != null)
			return instance;
		
		instance = new EventCategoryStepModel();
		return instance;
	}

	public String getError() 
	{
		if(!hasValidCategorys())
			return "Ungueltige Auswhal";
		
		return null;
	}
	
	public boolean hasValidCategorys()
	{
		if(leisureTimeCategories == null || leisureTimeCategories.isEmpty()){
			return false;
		}
		if(sportCategories == null || sportCategories.isEmpty()){
			return false;
		}
		if(cultureCategories == null || cultureCategories.isEmpty()){
			return false;
		}
		return true;
	}


	public void setLeisureTimeCategories(HashMap<String, String> leisureTimeList) 
	{
		this.leisureTimeCategories = leisureTimeList;
		updateAlredayFilled();
	}

	public HashMap<String, String> getSportCategories() {
		return sportCategories;
	}

	public void setSportCategories(HashMap<String, String> sportCategories) {
		this.sportCategories = sportCategories;
		updateAlredayFilled();
	}

	public HashMap<String, String> getCultureCategories() {
		return cultureCategories;
	}

	public void setCultureCategories(HashMap<String, String> cultureCategories) {
		this.cultureCategories = cultureCategories;
		updateAlredayFilled();
	}
	
	
}

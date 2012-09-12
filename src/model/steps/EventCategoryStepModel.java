package model.steps;

import gui.components.LikeBox;
import gui.steps.EventCategorySelectionStep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import ontologyAndDB.OntToDbConnection;

public class EventCategoryStepModel extends InformationGatherStepModel 
{
	private static EventCategoryStepModel instance;
	
	private HashMap<String, String> leisureTimeCategories;
	private HashMap<String, String> sportCategories;
	private HashMap<String, String> cultureCategories;
	
	private EventCategoryStepModel() 
	{
		super("Kategorie", new EventCategorySelectionStep());
		leisureTimeCategories = new HashMap<String, String>();
		sportCategories = new HashMap<String, String>();
		cultureCategories = new HashMap<String, String>();
	}
	
	public void setPreselection()
	{
		try
		{
			OntToDbConnection ontoConn = OntToDbConnection.getInstance();
			PersonAgeStepModel personAgesModel = PersonAgeStepModel.getInstance();

			List<String> cultureCatList = ontoConn.getSubClassesOfClassByOntology("CultureEvent");
			List<String> leisureCatList = ontoConn.getSubClassesOfClassByOntology("LeisureTimeEvent");
			List<String> sportCatList = ontoConn.getSubClassesOfClassByOntology("SportEvents");
			
			String[] preferedCategories = personAgesModel.getPreferedStuffBasedOnAges();
			
			setCultureCategories(buildCategoriesListWithLikeState(cultureCatList, preferedCategories));
			setLeisureTimeCategories(buildCategoriesListWithLikeState(leisureCatList, preferedCategories));
			setSportCategories(buildCategoriesListWithLikeState(sportCatList, preferedCategories));
			
		} catch (Exception e)
		{	
			System.out.println("exception");
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	private HashMap<String, String> buildCategoriesListWithLikeState(List<String> catList, String[] preferedCats)
	{
		HashMap<String, String> result = new HashMap<String, String>();
		String tmpState;
		
		for(String cat : catList) {
			tmpState = Arrays.asList(preferedCats).contains(cat) ? LikeBox.LIKE : LikeBox.DONTLIKE; 
			result.put(cat, tmpState);
		}
		
		return result;
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
	
	public HashMap<String, String> getLeisureTimeCategories() {
		return leisureTimeCategories;
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

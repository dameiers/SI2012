package model.steps;

import gui.steps.GenreSelectionStep;

import java.util.HashMap;

public class GenreSelectionStepModel extends InformationGatherStepModel 
{
	private static GenreSelectionStepModel instance;
	
	private HashMap<String, HashMap<String, String>> categoryGenresMap;
	
	private GenreSelectionStepModel() 
	{
		super("Genre", new GenreSelectionStep(true, true, true));
		
	}
	
	public void setPreselection()
	{
		//TODO
		//PersonAgeStepModel personAgeStepModel = PersonAgeStepModel.getInstance();
		
	}
	
	public static GenreSelectionStepModel getInstance() 
	{
		if(instance != null)
			return instance;
		
		instance = new GenreSelectionStepModel();
		return instance;
	}

	public String getError() 
	{
		return null;
	}

	public HashMap<String, HashMap<String, String>> getCategoryGenresMap() 
	{
		return categoryGenresMap;
	}

	public void setCategoryGenresMap(
			HashMap<String, HashMap<String, String>> categoryGenresMap) 
	{
		this.categoryGenresMap = categoryGenresMap;
		updateAlredayFilled();
	}
}

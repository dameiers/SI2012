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
	
	public static GenreSelectionStepModel getInstance() 
	{
		return instance != null ? instance : (instance=new GenreSelectionStepModel());
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

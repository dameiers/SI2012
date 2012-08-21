package model.steps;

public class EventCategoryStepModel extends InformationGatherStepModel 
{
	private static EventCategoryStepModel instance;
	
	private String[] categorys;
	
	private EventCategoryStepModel() 
	{
		
	}
	
	public void setPreselection()
	{
		//TODO
		//PersonAgeStepModel personAgeStepModel = PersonAgeStepModel.getInstance();
		
	}
	
	public static EventCategoryStepModel getInstance() 
	{
		return instance != null ? instance : (instance=new EventCategoryStepModel());
	}

	public String getError() 
	{
		if(!hasValidCategorys())
			return "UngŸltige Auswhal";
		
		return null;
	}
	
	public boolean hasValidCategorys()
	{
		return categorys != null && categorys.length > 0;
	}

	public String[] getCategorys() 
	{
		return categorys;
	}

	public void setCategorys(String[] categorys) 
	{
		this.categorys = categorys;
		updateAlredayFilled();
	}
}

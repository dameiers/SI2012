package model.steps;

public class PersonAgeStepModel extends InformationGatherStepModel 
{
	private static PersonAgeStepModel instance;
	
	private int[] ages;
	
	private PersonAgeStepModel() 
	{
		
	}
	
	public static PersonAgeStepModel getInstance() 
	{
		return instance != null ? instance : (instance=new PersonAgeStepModel());
	}

	public String getError() 
	{
		if(ages.length == 0)
			return "Ungültige Anzahl von Altersangaben";
		
		return null;
	}

	public int[] getAges() 
	{
		return ages;
	}

	public void setAges(String[] ages) 
	{
		this.ages = new int[ages.length];
		
		for(int i=1; i<ages.length; i++) {
			this.ages[i] = Integer.parseInt(ages[i]);
		}
	}
	
	public void setAges(int[] ages)
	{
		this.ages = ages;
	}
}

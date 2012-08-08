package model.steps;

public class PersonAgeStep extends InformationGatherStep 
{
	private static PersonAgeStep instance;
	
	private int[] ages;
	
	private PersonAgeStep() 
	{
		
	}
	
	public static PersonAgeStep getInstance() 
	{
		return instance != null ? instance : (instance=new PersonAgeStep());
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

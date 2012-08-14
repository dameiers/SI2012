package model.steps;

import gui.steps.BudgetStep;

public class BudgetStepModel extends InformationGatherStepModel 
{
	private static BudgetStepModel instance;
	
	private String budget;

	private BudgetStepModel() 
	{
		super("Budget", new BudgetStep());
	}
	
	public static BudgetStepModel getInstance() 
	{
		return instance != null ? instance : (instance=new BudgetStepModel());
	}
	
	public String getError() 
	{
		if(!hasValidBudget())
			return "Ungültiges Budget";
		
		return null;
	}
	
	public boolean hasValidBudget()
	{
		return budget != null && budget.matches("[0-9]+");
	}

	public String getBudget() 
	{
		return budget;
	}

	public void setBudget(String budget) 
	{
		this.budget = budget;
		updateAlredayFilled();
	}
}

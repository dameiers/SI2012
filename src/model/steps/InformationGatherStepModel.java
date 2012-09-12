package model.steps;

import gui.steps.AbstractViewModelConnectionImpl;
import gui.steps.ViewModelConnection;

import javax.swing.JComponent;

public abstract class InformationGatherStepModel 
{
	private String displayName;
	private Boolean alredyFilled = false;
	private Boolean preselectionWasAlreadySet = false;
	private AbstractViewModelConnectionImpl viewModelConnection;
	
	public InformationGatherStepModel(String displayName, 
			AbstractViewModelConnectionImpl viewModelConnection) 
	{
		this.displayName = displayName;
		this.viewModelConnection = viewModelConnection;
	}
	
	public Boolean isAlredyFilled() 
	{
		return alredyFilled;
	}
	
	public AbstractViewModelConnectionImpl getViewModelConnection() 
	{
		return viewModelConnection;
	}
	
	public void updateAlredayFilled()
	{
		alredyFilled = (getError() == null);
	}
	
	public final void setPreselectionIfItWasntAlredySet()
	{
		if(!preselectionWasAlreadySet)
		{
			setPreselection();
			preselectionWasAlreadySet=true;
		}
	}
	
	public void setPreselection()
	{
		
	}
	
	public String getDisplayName()
	{
		return displayName;
	}
	
	public abstract String getError();
	
}

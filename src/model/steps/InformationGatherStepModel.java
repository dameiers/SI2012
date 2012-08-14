package model.steps;

import gui.steps.ViewModelConnection;

import javax.swing.JComponent;

public abstract class InformationGatherStepModel 
{
	private String displayName;
	private Boolean alredyFilled = false;
	private ViewModelConnection viewModelConnection;
	
	public InformationGatherStepModel(String displayName, 
									  ViewModelConnection viewModelConnection) 
	{
		this.displayName = displayName;
		this.viewModelConnection = viewModelConnection;
	}
	
	public Boolean isAlredyFilled() 
	{
		return alredyFilled;
	}
	
	public ViewModelConnection getViewModelConnection() 
	{
		return viewModelConnection;
	}
	
	public void updateAlredayFilled()
	{
		alredyFilled = (getError() == null);
	}
	
	public abstract String getError();
}

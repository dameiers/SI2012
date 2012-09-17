package model.steps;

import gui.steps.AbstractViewModelConnectionImpl;
import gui.steps.ViewModelConnection;

import javax.swing.JComponent;

public abstract class InformationGatherStepModel 
{
	private String displayName;
	private Boolean alredyFilled = false;
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
	
	protected void setPreselection()
	{
		
	}
	
	public final void setPreselectionIfModelIsVirgin()
	{
		if(!alredyFilled) {
			System.out.println("setPreselection for "+ getDisplayName());
			setPreselection();
		}
	}
	
	public String getDisplayName()
	{
		return displayName;
	}
	
	public void printToConsole()
	{
		System.out.println("Wer die Info braucht, macht sich dran die printToConsole Methode im jeweiligen Schritt zu implementieren ;)");
	}
	
	public abstract String getError();
	
}

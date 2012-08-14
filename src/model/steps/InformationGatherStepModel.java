package model.steps;

import javax.swing.JComponent;

public abstract class InformationGatherStepModel 
{
	private String displayName;
	private Boolean alredyFilled = false;
	
	public Boolean isAlredyFilled() 
	{
		return alredyFilled;
	}
	
	public void updateAlredayFilled()
	{
		alredyFilled = (getError() == null);
	}
	
	public abstract String getError();
}

package model.steps;

import javax.swing.JComponent;

public abstract class InformationGatherStepModel 
{
	
	private String displayName;
	private JComponent guiForm;
	private Boolean alredyFilled;
	
	public Boolean isAlredyFilled() {
		return alredyFilled;
	}
	
	public void updateAlredayFilled()
	{
		alredyFilled = (getError() == null);
	}
	
	public abstract String getError();
}

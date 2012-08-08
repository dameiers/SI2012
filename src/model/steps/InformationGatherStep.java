package model.steps;

import javax.swing.JComponent;

public abstract class InformationGatherStep {
	
	private String displayName;
	private String identifier;
	private JComponent guiForm;
	private Boolean alredyFilled;
	
	public Boolean isAlredyFilled() {
		return alredyFilled;
	}
	
	public void setAlredyFilled(Boolean alredyFilled) {
		this.alredyFilled = alredyFilled;
	}
	
	public abstract String getError();
}

package gui.steps;

import javax.swing.JComponent;

import model.steps.InformationGatherStepModel;

public interface ViewModelConnection {
	
	void fillModel();
	void fillMask();
	
	JComponent getVisualisationUI();
	
	InformationGatherStepModel getModel();

}

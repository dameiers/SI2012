package gui.steps;

import javax.swing.JComponent;

import model.steps.InformationGatherStepModel;

public interface ViewModelConnection {
	
	void fillModel();
	
	JComponent getVisualisationUI();
	
	InformationGatherStepModel getModel();

}

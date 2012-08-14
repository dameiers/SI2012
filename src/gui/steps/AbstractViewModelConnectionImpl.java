package gui.steps;

import javax.swing.JComponent;
import javax.swing.JPanel;

import model.steps.InformationGatherStepModel;

public abstract class AbstractViewModelConnectionImpl extends JPanel implements ViewModelConnection{


	public AbstractViewModelConnectionImpl()
	{
		super();
	}
	
	@Override
	public JComponent getVisualisationUI() {
		// TODO Auto-generated method stub
		return this;
	}

}

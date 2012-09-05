package gui;
import gui.steps.PersonDescriptionStep;
import gui.steps.TimeRangeStep;
import gui.steps.ViewModelConnection;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;

import javax.swing.WindowConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import model.Model;
import model.steps.InformationGatherStepModel;



/**
* This code was edited or generated using CloudGarden's Jigloo
* SWT/Swing GUI Builder, which is free for non-commercial
* use. If Jigloo is being used commercially (ie, by a corporation,
* company or business for any purpose whatever) then you
* should purchase a license for each developer using Jigloo.
* Please visit www.cloudgarden.com for details.
* Use of Jigloo implies acceptance of these licensing terms.
* A COMMERCIAL LICENSE HAS NOT BEEN PURCHASED FOR
* THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED
* LEGALLY FOR ANY CORPORATE OR COMMERCIAL PURPOSE.
*/
public class MainFrame extends javax.swing.JFrame implements ActionListener {
	private JPanel ctrlPnl;
	private JPanel mainPnl;
	private JButton backBtn;
	private JButton nextBtn;
	
	private Model model;
	private ViewModelConnection currentViewStepConnection;
	private Stack<ViewModelConnection> stepHistory;
	

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainFrame inst = new MainFrame();
				inst.setLocationRelativeTo(null);
				inst.setVisible(true);
			}
		});
	}
	
	public MainFrame() {
		super();
		initGUI();
	}
	
	public void lastStep()
	{
		
	}
	
	public void nextStep() 
	{
		InformationGatherStepModel stepModel = currentViewStepConnection.getModel();
		String error = stepModel.getError();
		
		if(error == null)
		{
			updateBreadcrubs(model.getInformationGatherTrace());
			
			InformationGatherStepModel 	nextStepModel = model.getNextStep();
			ViewModelConnection 		nextViewModelConnection = nextStepModel.getViewModelConnection();
			JComponent 					nextJComponent = nextViewModelConnection.getVisualisationUI();
			
			stepHistory.push(currentViewStepConnection);
			getContentPane().add(mainPnl, BorderLayout.CENTER);
			
			//remove(currentViewStepConnection.getVisualisationUI());
			//getContentPane().add(nextJComponent);
			
			currentViewStepConnection = nextViewModelConnection;
			pack();
		}
		else
		{
			displayError(error); 
		}
	}
	
	private void displayError(String error) 
	{
		
	}
	
	private void updateBreadcrubs(InformationGatherStepModel[] stepModels)
	{
		
	}
	
	private void initGUI() {
		try {
			setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
			BorderLayout thisLayout = new BorderLayout();
			getContentPane().setLayout(thisLayout);
			{
				ctrlPnl = new JPanel();
				GridBagLayout ctrlPnlLayout = new GridBagLayout();
				getContentPane().add(ctrlPnl, BorderLayout.SOUTH);
				ctrlPnl.setPreferredSize(new java.awt.Dimension(645, 48));
				ctrlPnl.setLayout(ctrlPnlLayout);
				ctrlPnlLayout.rowWeights = new double[] {0.1};
				ctrlPnlLayout.rowHeights = new int[] {7};
				ctrlPnlLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0};
				ctrlPnlLayout.columnWidths = new int[] {0, 0, 0, 0, -73};
				{
					nextBtn = new JButton();
					ctrlPnl.add(nextBtn, new GridBagConstraints(4, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					nextBtn.setText("Weiter");
					nextBtn.setBounds(566, 12, 58, 28);
				}
				{
					backBtn = new JButton();
					ctrlPnl.add(backBtn, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					backBtn.setText("Zur�ck");
					backBtn.setBounds(485, 12, 58, 28);
				}
			}
			{
				mainPnl = new JPanel();
				mainPnl.setPreferredSize(new Dimension(691, 416));
				mainPnl.setBorder(new EmptyBorder(10, 10, 10, 10));
				mainPnl.add(new TimeRangeStep(), BorderLayout.CENTER);
				getContentPane().add(mainPnl, BorderLayout.CENTER);				
			}
			pack();
			this.setSize(750, 650);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) 
	{
		nextStep();
	}

}

package gui.steps;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

import model.steps.InformationGatherStepModel;
import model.steps.PersonAgeStepModel;

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
public class PersonDescriptionStep extends AbstractViewModelConnectionImpl{
	private JPanel tiltePnl;
	private JPanel contentPnl;
	private JLabel ageLbl;
	private JComboBox ageCbo;
	private JComboBox groupCbo;
	private JLabel groupLbl;
	private JTextPane titleTxt;
	private PersonAgeStepModel model = PersonAgeStepModel.getInstance();

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new PersonDescriptionStep());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public PersonDescriptionStep() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			BorderLayout thisLayout = new BorderLayout();
			this.setLayout(thisLayout);
			this.setPreferredSize(new java.awt.Dimension(691, 416));
			{
				tiltePnl = new JPanel();
				BorderLayout tiltePnlLayout = new BorderLayout();
				tiltePnl.setLayout(tiltePnlLayout);
				this.add(tiltePnl, BorderLayout.NORTH);
				tiltePnl.setPreferredSize(new java.awt.Dimension(691, 51));
				{
					titleTxt = new JTextPane();
					tiltePnl.add(titleTxt, BorderLayout.WEST);
					titleTxt.setText("Bitte geben Sie zun�chst ihr Alter an und f�r welche Gruppe Sie ein Event suchen.");
					titleTxt.setPreferredSize(new java.awt.Dimension(626, 58));
					titleTxt.setBackground(new java.awt.Color(212,208,200));
					titleTxt.setEditable(false);
					titleTxt.setOpaque(false);
					titleTxt.setFont(new java.awt.Font("Segoe UI",0,18));
				}
			}
			{
				contentPnl = new JPanel();
				this.add(contentPnl, BorderLayout.CENTER);
				GridBagLayout contentPnlLayout = new GridBagLayout();
				contentPnlLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.1};
				contentPnlLayout.rowHeights = new int[] {20, 50, 50, 5};
				contentPnlLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.2};
				contentPnlLayout.columnWidths = new int[] {30, 100, 200, 5};
				contentPnl.setLayout(contentPnlLayout);
				contentPnl.setPreferredSize(new java.awt.Dimension(635, 277));
				{
					ageLbl = new JLabel();
					contentPnl.add(ageLbl, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					ageLbl.setText("Alter:");
				}
				{
					groupLbl = new JLabel();
					contentPnl.add(groupLbl, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					groupLbl.setText("Gruppe:");
				}
				{
					ComboBoxModel ageCboModel = 
							new DefaultComboBoxModel(
									new String[] { "Item One", "Item Two" });
					ageCbo = new JComboBox();
					contentPnl.add(ageCbo, new GridBagConstraints(2, 1, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					ageCbo.setModel(ageCboModel);
				}
				{
					ComboBoxModel groupCboModel = 
							new DefaultComboBoxModel(
									new String[] { "Item One", "Item Two" });
					groupCbo = new JComboBox();
					contentPnl.add(groupCbo, new GridBagConstraints(2, 2, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					groupCbo.setModel(groupCboModel);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void fillModel() {
		
	}

	@Override
	public InformationGatherStepModel getModel() {
		return model;
	}
	
	

}

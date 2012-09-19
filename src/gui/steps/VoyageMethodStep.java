package gui.steps;
import gui.components.LocationComboBox;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.steps.InformationGatherStepModel;
import model.steps.VoyageMethodStepModel;

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
public class VoyageMethodStep extends AbstractViewModelConnectionImpl {
	private JPanel tiltePnl;
	private JPanel contentPnl;
	private JRadioButton noBtn;
	private JRadioButton yesBtn;
	private ButtonGroup btnGroup;
	private JTextPane titleTxt;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new VoyageMethodStep());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public VoyageMethodStep() {
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
					titleTxt.setText("Ist die Anreise mit dem Auto moeglich?");
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
				contentPnl.setLayout(null);
				contentPnl.setPreferredSize(new java.awt.Dimension(635, 277));
				{
					yesBtn = new JRadioButton();
					contentPnl.add(yesBtn);
					yesBtn.setText("Ja");
					yesBtn.setBounds(69, 26, 52, 19);
					getBtnGroup().add(yesBtn);
				}
				{
					noBtn = new JRadioButton();
					contentPnl.add(noBtn);
					noBtn.setText("Nein");
					noBtn.setBounds(132, 26, 67, 19);
					getBtnGroup().add(noBtn);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ButtonGroup getBtnGroup() {
		if(btnGroup == null) {
			btnGroup = new ButtonGroup();
		}
		return btnGroup;
	}

	@Override
	public void fillModel() {
		VoyageMethodStepModel model = VoyageMethodStepModel.getInstance();
		model.setByCar(btnGroup.getSelection() == yesBtn.getModel());
	}

	@Override
	public InformationGatherStepModel getModel() {
		return VoyageMethodStepModel.getInstance();
	}

}

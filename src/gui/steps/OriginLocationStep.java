package gui.steps;
import gui.components.LocationComboBox;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import javax.swing.JComponent;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.steps.InformationGatherStepModel;
import model.steps.OriginLocationStepModel;

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
public class OriginLocationStep extends AbstractViewModelConnectionImpl {
	private JPanel tiltePnl;
	private JPanel contentPnl;
	private LocationComboBox locationComboBox;
	private JLabel locationLbl;
	private JTextPane titleTxt;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new OriginLocationStep());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public OriginLocationStep() {
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
					titleTxt.setText("Von wo aus m�chten Sie die Reise zum Event antreten?");
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
					locationLbl = new JLabel();
					contentPnl.add(locationLbl);
					locationLbl.setText("Ort: ");
					locationLbl.setBounds(72, 28, 87, 15);
				}
				{
					locationComboBox = new LocationComboBox();
					contentPnl.add(locationComboBox);
					locationComboBox.setBounds(171, 24, 136, 22);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void fillModel() {
		OriginLocationStepModel model = OriginLocationStepModel.getInstance();
		model.setOrigin(locationComboBox.getSelectedLocation());
	}

	@Override
	public InformationGatherStepModel getModel() {
		return OriginLocationStepModel.getInstance();
	}
	
	

}

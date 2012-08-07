package gui.steps;
import gui.components.SchoolLocationComponent;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextPane;

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
public class SchoolLocationStep extends javax.swing.JPanel {
	private JPanel tiltePnl;
	private JPanel contentPnl;
	private SchoolLocationComponent schoolLocationComponent1;
	private JPanel schoolLocationComponentContainer;
	private JTextPane titleTxt;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new SchoolLocationStep());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public SchoolLocationStep() {
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
					titleTxt.setText("Wo gehen Ihre Kinder zur Schule?");
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
				contentPnlLayout.rowWeights = new double[] {0.0, 0.1};
				contentPnlLayout.rowHeights = new int[] {200, 5};
				contentPnlLayout.columnWeights = new double[] {0.0};
				contentPnlLayout.columnWidths = new int[] {600};
				contentPnl.setLayout(contentPnlLayout);
				contentPnl.setPreferredSize(new java.awt.Dimension(635, 277));
				{
					schoolLocationComponentContainer = new JPanel();
					contentPnl.add(schoolLocationComponentContainer, new GridBagConstraints(0, 0, 1, 1, 0.0, 0.0, GridBagConstraints.NORTHWEST, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
					{
						schoolLocationComponent1 = new SchoolLocationComponent();
						schoolLocationComponentContainer.add(schoolLocationComponent1);
					}
				}
				//final int col =((GridLayout)contentPnl.getLayout()).getRows()+1;
				//((GridLayout)contentPnl.getLayout()).setRows(col);
				//contentPnl.add(new SchoolLocationComponent());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	

}

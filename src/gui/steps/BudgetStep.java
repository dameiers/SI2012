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
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.steps.BudgetStepModel;
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
public class BudgetStep extends AbstractViewModelConnectionImpl {
	private JPanel tiltePnl;
	private JPanel contentPnl;
	private JLabel jLabel1;
	private JTextField distanceTxt;
	private JTextPane titleTxt;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new BudgetStep());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public BudgetStep() {
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
					titleTxt.setText("Wie teuer darf das Event sein?");
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
					distanceTxt = new JTextField();
					contentPnl.add(distanceTxt);
					distanceTxt.setBounds(72, 25, 106, 22);
				}
				{
					jLabel1 = new JLabel();
					contentPnl.add(jLabel1);
					jLabel1.setText("\u20ac");
					jLabel1.setBounds(190, 28, 10, 15);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void fillModel() {
		BudgetStepModel model = BudgetStepModel.getInstance();
		model.setBudget(distanceTxt.getText());
	}

	@Override
	public InformationGatherStepModel getModel() {
		return  BudgetStepModel.getInstance();
	}
	
	

}

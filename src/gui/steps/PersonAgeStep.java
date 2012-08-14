package gui.steps;
import gui.components.PersonAgeComponent;
import gui.components.SchoolLocationComponent;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;

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
public class PersonAgeStep extends AbstractViewModelConnectionImpl implements ActionListener {
	private JPanel tiltePnl;
	private JPanel contentPnl;
	private JPanel addBtnContainer;
	private PersonAgeComponent ageComponent1;
	private JPanel ageCboContainer;
	private JButton addBtn;
	private JTextPane titleTxt;
	private PersonAgeStepModel model = PersonAgeStepModel.getInstance();

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new PersonAgeStep());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public PersonAgeStep() {
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
					titleTxt.setText("Alter der beteiligent Personen");
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
				contentPnlLayout.rowHeights = new int[] {300, 5};
				contentPnlLayout.columnWeights = new double[] {0.1, 0.0, 0.0, 0.1};
				contentPnlLayout.columnWidths = new int[] {5, 350, 75, 5};
				contentPnl.setLayout(contentPnlLayout);
				contentPnl.setPreferredSize(new java.awt.Dimension(635, 277));
				{
					ageCboContainer = new JPanel();
					FlowLayout schoolCboContainerLayout = new FlowLayout();
					contentPnl.add(ageCboContainer, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.VERTICAL, new Insets(0, 0, 0, 0), 0, 0));
					contentPnl.setSize(400, 300);
					ageCboContainer.setLayout(schoolCboContainerLayout);

					ageCboContainer.setPreferredSize(new java.awt.Dimension(400, 300));
					ageCboContainer.setMaximumSize(new java.awt.Dimension(400, 32767));
					ageCboContainer.add(new PersonAgeComponent(ageCboContainer));

				}
				{
					addBtnContainer = new JPanel();
					contentPnl.add(addBtnContainer, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					addBtnContainer.setLayout(null);
					{
						addBtn = new JButton();
						addBtnContainer.add(addBtn);
						addBtn.setText("+");
						addBtn.setActionCommand("add");
						addBtn.addActionListener(this);
						addBtn.setBounds(12, 16, 40, 22);
					}
				}

			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String[] getSelectedAges()
	{
		int locationCount = ageCboContainer.getComponentCount();
		String[] locations = new String[locationCount];
		for(int i=0; i<locationCount; i++) {
			locations[i] = ((PersonAgeComponent)ageCboContainer.getComponent(i)).getSelectedAges();
		}
		
		return locations;
	}
	
	public void actionPerformed(ActionEvent arg0) {			
		ageCboContainer.add(new PersonAgeComponent(ageCboContainer));
		ageCboContainer.revalidate();
		ageCboContainer.repaint();
	}

	@Override
	public void fillModel() {
		
	}

	@Override
	public InformationGatherStepModel getModel() {
		return model;
	}

}

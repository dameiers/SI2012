package gui.steps;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.ButtonGroup;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;

import javax.swing.JComponent;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JTextPane;

import model.steps.DurationStepModel;
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
public class DurationStep extends AbstractViewModelConnectionImpl {
	private JPanel tiltePnl;
	private JPanel contentPnl;
	private JRadioButton weekendBtn;
	private JRadioButton oneDayBtn;
	private ButtonGroup durationGroup;
	private JTextPane titleTxt;
	private DurationStepModel model = DurationStepModel.getInstance();

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new DurationStep());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public DurationStep() {
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
					titleTxt.setText("Wie lange darf das Event maximal dauern?");
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
				contentPnlLayout.rowWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.1};
				contentPnlLayout.rowHeights = new int[] {20, 50, 50, 50, 50, 50, 50, 5};
				contentPnlLayout.columnWeights = new double[] {0.0, 0.0, 0.2};
				contentPnlLayout.columnWidths = new int[] {30, 400, 5};
				contentPnl.setLayout(contentPnlLayout);
				contentPnl.setPreferredSize(new java.awt.Dimension(635, 277));
				{
					weekendBtn = new JRadioButton();
					contentPnl.add(weekendBtn, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					weekendBtn.setText("Wochenende");
					getTimeRangeGroup().add(weekendBtn);
				}
				{
					oneDayBtn = new JRadioButton();
					contentPnl.add(oneDayBtn, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					oneDayBtn.setText("Eint�gig");
					getTimeRangeGroup().add(oneDayBtn);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ButtonGroup getTimeRangeGroup() {
		if(durationGroup == null) {
			durationGroup = new ButtonGroup();
		}
		
		return durationGroup;
	}

	@Override
	public void fillModel() {
		final String duration = durationGroup.getSelection() == oneDayBtn.getModel() ? DurationStepModel.ONE_DAY_DURATION : DurationStepModel.WEEKEND_DURATION;
		model.setDuration(duration);
	}

	@Override
	public InformationGatherStepModel getModel() {
		return model;
	}

}

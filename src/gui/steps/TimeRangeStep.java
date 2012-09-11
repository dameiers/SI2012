package gui.steps;
import java.awt.BorderLayout;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

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
import model.steps.TimeRangeStepModel;

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
public class TimeRangeStep extends AbstractViewModelConnectionImpl{
	private JPanel tiltePnl;
	private JPanel contentPnl;
	private JRadioButton summerbreakBtn;
	private JLabel toLbl;
	private JTextField fromDateTxt;
	private JLabel fromLbl;
	private JPanel miscTimeRangePnl;
	private JRadioButton miscTimeRange;
	private JTextField toDateTxt;
	private JRadioButton easterbreak;
	private JRadioButton winterbreakBtn;
	private JRadioButton autumbreakBtn;
	private ButtonGroup timeRangeGroup;
	private JTextPane titleTxt;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new TimeRangeStep());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public TimeRangeStep() {
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
					titleTxt.setText("In welchem Zeitraum soll das Event statt finden?");
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
					summerbreakBtn = new JRadioButton();
					contentPnl.add(summerbreakBtn, new GridBagConstraints(1, 1, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					summerbreakBtn.setText("Sommerferien");
					getTimeRangeGroup().add(summerbreakBtn);
				}
				{
					autumbreakBtn = new JRadioButton();
					contentPnl.add(autumbreakBtn, new GridBagConstraints(1, 2, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					contentPnl.add(getWinterbreakBtn(), new GridBagConstraints(1, 3, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					contentPnl.add(getEasterbreak(), new GridBagConstraints(1, 4, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					contentPnl.add(getMiscTimeRange(), new GridBagConstraints(1, 5, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
					contentPnl.add(getMiscTimeRangePnl(), new GridBagConstraints(1, 6, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.BOTH, new Insets(0, 0, 0, 0), 0, 0));
					autumbreakBtn.setText("Herbstferien");
					getTimeRangeGroup().add(autumbreakBtn);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private ButtonGroup getTimeRangeGroup() {
		if(timeRangeGroup == null) {
			timeRangeGroup = new ButtonGroup();
		}
		return timeRangeGroup;
	}
	
	private JRadioButton getWinterbreakBtn() {
		if(winterbreakBtn == null) {
			winterbreakBtn = new JRadioButton();
			winterbreakBtn.setText("Weihnachtsferien");
			getTimeRangeGroup().add(winterbreakBtn);
		}
		return winterbreakBtn;
	}
	
	private JRadioButton getEasterbreak() {
		if(easterbreak == null) {
			easterbreak = new JRadioButton();
			easterbreak.setText("Osterferien");
			getTimeRangeGroup().add(easterbreak);
		}
		return easterbreak;
	}
	
	private JRadioButton getMiscTimeRange() {
		if(miscTimeRange == null) {
			miscTimeRange = new JRadioButton();
			miscTimeRange.setText("Eigener Zeitraum");
			getTimeRangeGroup().add(miscTimeRange);
		}
		return miscTimeRange;
	}

	private JPanel getMiscTimeRangePnl() {
		if(miscTimeRangePnl == null) {
			miscTimeRangePnl = new JPanel();
			miscTimeRangePnl.setLayout(null);
			miscTimeRangePnl.add(getFromLbl());
			miscTimeRangePnl.add(getFromDateTxt());
			miscTimeRangePnl.add(getToLbl());
			miscTimeRangePnl.add(getToDateTxt());
		}
		return miscTimeRangePnl;
	}
	
	private JLabel getFromLbl() {
		if(fromLbl == null) {
			fromLbl = new JLabel();
			fromLbl.setText("von:");
			fromLbl.setBounds(37, 18, 27, 21);
		}
		return fromLbl;
	}
	
	private JTextField getFromDateTxt() {
		if(fromDateTxt == null) {
			fromDateTxt = new JTextField();
			fromDateTxt.setBounds(71, 15, 96, 28);
		}
		return fromDateTxt;
	}
	
	private JLabel getToLbl() {
		if(toLbl == null) {
			toLbl = new JLabel();
			toLbl.setText("bis:");
			toLbl.setBounds(179, 18, 22, 21);
		}
		return toLbl;
	}
	
	private JTextField getToDateTxt() {
		if(toDateTxt == null) {
			toDateTxt = new JTextField();
			toDateTxt.setBounds(213, 15, 10, 28);
			toDateTxt.setSize(96, 28);
		}
		return toDateTxt;
	}

	@Override
	public void fillModel() {
		TimeRangeStepModel model = TimeRangeStepModel.getInstance();
		//there is a own time range defined
		if(timeRangeGroup.getSelection() == miscTimeRange.getModel()){
			model.setTimeRangeTyp(TimeRangeStepModel.MISC_TIME_RANGE);
			SimpleDateFormat df = new SimpleDateFormat("dd-mm-yyyy");
			final GregorianCalendar fromDate = new GregorianCalendar();
			final GregorianCalendar toDate = new GregorianCalendar();
			try {
				fromDate.setTime(df.parse(fromDateTxt.getText()));
				toDate.setTime(df.parse(toDateTxt.getText()));
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			model.setFromDate(fromDate);
			model.setToDate(toDate);
		}else{
			if(timeRangeGroup.getSelection() == easterbreak.getModel()){
				model.setTimeRangeTyp(TimeRangeStepModel.EASTERBREAK_TIME_RANGE);
			}else if(timeRangeGroup.getSelection() == winterbreakBtn.getModel()){
				model.setTimeRangeTyp(TimeRangeStepModel.WINTERBREAK_TIME_RANGE);
			}else if(timeRangeGroup.getSelection() == summerbreakBtn.getModel()){
				model.setTimeRangeTyp(TimeRangeStepModel.SUMMERBREAK_TIME_RANGE);
			}else{
				model.setTimeRangeTyp(TimeRangeStepModel.AUTUMNBREAK_TIME_RANGE);	
			}
			
		}

	}

	@Override
	public InformationGatherStepModel getModel() {
		return TimeRangeStepModel.getInstance();
	}

}

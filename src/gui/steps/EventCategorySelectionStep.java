package gui.steps;

import gui.components.LikeBox;
import gui.components.LikeSelectionList;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import ontologyAndDB.OntToDbConnection;
import ontologyAndDB.exception.OntologyConnectionUnknowClassException;

import model.IntelligentEventCollector;
import model.steps.EventCategoryStepModel;
import model.steps.InformationGatherStepModel;
import model.steps.KindOfEventSelectionStepModel;

/**
 * This code was edited or generated using CloudGarden's Jigloo SWT/Swing GUI
 * Builder, which is free for non-commercial use. If Jigloo is being used
 * commercially (ie, by a corporation, company or business for any purpose
 * whatever) then you should purchase a license for each developer using Jigloo.
 * Please visit www.cloudgarden.com for details. Use of Jigloo implies
 * acceptance of these licensing terms. A COMMERCIAL LICENSE HAS NOT BEEN
 * PURCHASED FOR THIS MACHINE, SO JIGLOO OR THIS CODE CANNOT BE USED LEGALLY FOR
 * ANY CORPORATE OR COMMERCIAL PURPOSE.
 */
public class EventCategorySelectionStep extends AbstractViewModelConnectionImpl{
	private JPanel tiltePnl;
	private JPanel contentPnl;
	private LikeSelectionList sportCategoryList;
	private LikeSelectionList leisureTimeCategoryList;
	private LikeSelectionList cultureCategoryList;
	private JTextPane titleTxt;
	private boolean sportCategoryVisible;
	private boolean leisureTimeCategoryVisible;
	private boolean cultureCategoryVisible;
	private int insertPos = 1;


	/**
	 * Auto-generated main method to display this JPanel inside a new JFrame.
	 */
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new EventCategorySelectionStep());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}

	public EventCategorySelectionStep() 
	{
		this(true, true, true);
	}

	public EventCategorySelectionStep(boolean leisureTimeVisible,
			boolean sportVisible, boolean cultureVisible) {
		this.cultureCategoryVisible = cultureVisible;
		this.leisureTimeCategoryVisible = leisureTimeVisible;
		this.sportCategoryVisible = sportVisible;
		initGUI();
	}
	
	private static boolean shouldDisplayCategory(String likeStatus)
	{
		return likeStatus != null && (likeStatus.equals(KindOfEventSelectionStepModel.LIKE) ||
			   likeStatus.equals(KindOfEventSelectionStepModel.MAYBE));
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
					titleTxt.setText("Welche Art von Event moechten Sie besuchen?");
					titleTxt.setPreferredSize(new java.awt.Dimension(626, 58));
					titleTxt.setBackground(new java.awt.Color(212, 208, 200));
					titleTxt.setEditable(false);
					titleTxt.setOpaque(false);
					titleTxt.setFont(new java.awt.Font("Segoe UI", 0, 18));
				}
			}
			{
				contentPnl = new JPanel();
				this.add(contentPnl, BorderLayout.CENTER);
				GridBagLayout contentPnlLayout = new GridBagLayout();
				contentPnlLayout.rowWeights = new double[] { 0.0, 0.0, 0.1 };
				contentPnlLayout.rowHeights = new int[] { 20, 300, 7 };
				contentPnlLayout.columnWeights = new double[] { 0.0, 0.0, 0.0,
						0.0, 0.1 };
				contentPnlLayout.columnWidths = new int[] { 30, 200, 200, 200,
						7 };
				contentPnl.setLayout(contentPnlLayout);
				contentPnl.setPreferredSize(new java.awt.Dimension(691, 365));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean isSportCategoryVisible() {
		return sportCategoryVisible;
	}

	public void setSportCategoryVisible(boolean sportCategoryVisible) {
		this.sportCategoryVisible = sportCategoryVisible;
		updateCategoryLists();
	}

	public boolean isLeisureTimeCategoryVisible() {
		return leisureTimeCategoryVisible;
	}

	public void setLeisureTimeCategoryVisible(boolean leisureTimeCategoryVisible) {
		this.leisureTimeCategoryVisible = leisureTimeCategoryVisible;
		updateCategoryLists();
	}

	public boolean isCultureCategoryVisible() {
		return cultureCategoryVisible;
	}

	public void setCultureCategoryVisible(boolean cultureCategoryVisible) {
		this.cultureCategoryVisible = cultureCategoryVisible;
		updateCategoryLists();
	}

	private void updateCategoryLists() {
		EventCategoryStepModel model = EventCategoryStepModel.getInstance();
		contentPnl.removeAll();
		insertPos = 1;
		if (cultureCategoryVisible) {
			cultureCategoryList = new LikeSelectionList("Kultur", model.getCultureCategories());
			contentPnl.add(cultureCategoryList, new GridBagConstraints(
					insertPos, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
					GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			insertPos++;
			cultureCategoryList.addActionListener(IntelligentEventCollector.getInstance());
		}
		if (leisureTimeCategoryVisible) {
			leisureTimeCategoryList = new LikeSelectionList("Freizeit", model.getLeisureTimeCategories());
			contentPnl.add(leisureTimeCategoryList, new GridBagConstraints(
					insertPos, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
					GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			insertPos++;
			leisureTimeCategoryList.addActionListener(IntelligentEventCollector.getInstance());
		}
		if (sportCategoryVisible) {
			sportCategoryList = new LikeSelectionList("Sport", model.getSportCategories());
			contentPnl.add(sportCategoryList, new GridBagConstraints(insertPos,
					1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH,
					GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			insertPos++;
			sportCategoryList.addActionListener(IntelligentEventCollector.getInstance());
		}
	}

	@Override
	public void fillModel() {
		EventCategoryStepModel model = EventCategoryStepModel.getInstance();
		
		if(cultureCategoryVisible){
			model.setCultureCategories(cultureCategoryList.getSelectionList());
			IntelligentEventCollector.getInstance().addEventstoOnto(cultureCategoryList.getSelectionList());
		}
		if(sportCategoryVisible){
			model.setSportCategories(sportCategoryList.getSelectionList());
			IntelligentEventCollector.getInstance().addEventstoOnto(sportCategoryList.getSelectionList());
			
		}
		if(leisureTimeCategoryVisible){
			model.setLeisureTimeCategories(leisureTimeCategoryList.getSelectionList());
			IntelligentEventCollector.getInstance().addEventstoOnto(leisureTimeCategoryList.getSelectionList());
		}
	}
	
	@Override
	public void fillMask() {
		KindOfEventSelectionStepModel kindOfModel = KindOfEventSelectionStepModel.getInstance();
		setLeisureTimeCategoryVisible(shouldDisplayCategory(kindOfModel.getLeisureTimeStatus()));
	    setSportCategoryVisible(shouldDisplayCategory(kindOfModel.getSportStatus())); 
	    setCultureCategoryVisible(shouldDisplayCategory(kindOfModel.getCultureStatus()));
	    updateCategoryLists();
	}

	@Override
	public InformationGatherStepModel getModel() {
		return EventCategoryStepModel.getInstance();
	}
	
}

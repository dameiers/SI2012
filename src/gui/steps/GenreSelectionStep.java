package gui.steps;
import gui.components.LikeSelectionList;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.ListModel;
import javax.swing.WindowConstants;

import model.IntelligentEventCollector;
import model.steps.EventCategoryStepModel;
import model.steps.GenreSelectionStepModel;
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
public class GenreSelectionStep extends AbstractViewModelConnectionImpl{
	private JPanel tiltePnl;
	private JPanel contentPnl;
	private LikeSelectionList theatreGenreList;
	private LikeSelectionList concertGenresList;
	private LikeSelectionList cinemaGenreList;
	private JTextPane titleTxt;
	private boolean cinemaGenreVisible;
	private boolean theatreGenreVisible;
	private boolean concertGenreVisible;
	private int insertPos =1;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new GenreSelectionStep());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public GenreSelectionStep(){
		this(true,true,true);
	}
	
	public GenreSelectionStep(boolean cinemaGenreVisible, boolean theatreGenreVisible, boolean concertGenreVisible) {
		super();
		this.cinemaGenreVisible = cinemaGenreVisible;
		this.theatreGenreVisible = theatreGenreVisible;
		this.concertGenreVisible = concertGenreVisible;
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
					titleTxt.setText("Welche Art von Event moechten Sie besuchen?");
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
				contentPnlLayout.rowWeights = new double[] {0.0, 0.0, 0.1};
				contentPnlLayout.rowHeights = new int[] {20, 300, 7};
				contentPnlLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.1};
				contentPnlLayout.columnWidths = new int[] {30, 200, 200, 200, 7};
				contentPnl.setLayout(contentPnlLayout);
				contentPnl.setPreferredSize(new java.awt.Dimension(691, 365));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	public boolean isTheatreGenreVisible() {
		return theatreGenreVisible;
	}

	public void setTheatreGenreVisible(boolean theatreGenreVisible) {
		this.theatreGenreVisible = theatreGenreVisible;
		updateGenreLists();
	}

	public boolean isCinemaGenreVisible() {
		return cinemaGenreVisible;
	}

	public void setCinemaGenreVisible(boolean cinemaGenreVisible) {
		this.cinemaGenreVisible = cinemaGenreVisible;
		updateGenreLists();
	}

	public boolean isConcertGenreVisible() {
		return concertGenreVisible;
	}

	public void setConcertGenreVisible(boolean concertGenreVisible) {
		this.concertGenreVisible = concertGenreVisible;
		updateGenreLists();
	}
	
	private void updateGenreLists(){
		GenreSelectionStepModel model = GenreSelectionStepModel.getInstance(); 
		contentPnl.removeAll();
		insertPos =1;
		if(cinemaGenreVisible){
			cinemaGenreList = new LikeSelectionList("Kino", model.getCinemaGenres());
			contentPnl.add(cinemaGenreList, new GridBagConstraints(insertPos, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			insertPos++;
			cinemaGenreList.addActionListener(IntelligentEventCollector.getInstance());
		}
		if(concertGenreVisible){
			concertGenresList = new LikeSelectionList("Konzert", model.getConcertGenres());
			contentPnl.add(concertGenresList, new GridBagConstraints(insertPos, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			insertPos++;
			concertGenresList.addActionListener(IntelligentEventCollector.getInstance());
		}
		if(theatreGenreVisible){
			theatreGenreList = new LikeSelectionList("Theater", model.getTheatreGenres());
			contentPnl.add(theatreGenreList, new GridBagConstraints(insertPos, 1, 1, 1, 0.0, 0.0, GridBagConstraints.NORTH, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
			insertPos++;
			theatreGenreList.addActionListener(IntelligentEventCollector.getInstance());
		}
	}

	@Override
	public void fillModel() {
		GenreSelectionStepModel model = GenreSelectionStepModel.getInstance();
		
		if(cinemaGenreVisible) {
			model.setCinemaGenres(cinemaGenreList.getSelectionList());
			IntelligentEventCollector.getInstance().addEventstoOnto(cinemaGenreList.getSelectionList());
		} 
		
		if(concertGenreVisible) {
			model.setConcertGenres(concertGenresList.getSelectionList());
			IntelligentEventCollector.getInstance().addEventstoOnto(concertGenresList.getSelectionList());
		}
		
		if(theatreGenreVisible) {
			model.setTheatreGenres(theatreGenreList.getSelectionList());
			IntelligentEventCollector.getInstance().addEventstoOnto(theatreGenreList.getSelectionList());
		}
	}
	
	@Override
	public void fillMask() {
		EventCategoryStepModel eventCategoryStepModel = EventCategoryStepModel.getInstance();
		 
		setCinemaGenreVisible(eventCategoryStepModel.isCategoryDesired("CinemaEvent"));
		setConcertGenreVisible(eventCategoryStepModel.isCategoryDesired("ConcertEvent"));
		setTheatreGenreVisible(eventCategoryStepModel.isCategoryDesired("TheatreEvent"));
		updateGenreLists();
	}

	@Override
	public InformationGatherStepModel getModel() {
		return GenreSelectionStepModel.getInstance();
	}

}

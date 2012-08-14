package gui.components;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;

public class LikeSelectionList extends JPanel {

	public final static String CULTURE_ID ="Kulturell";
	public static final String LEISURE_TIME_ID ="Freizeit";
	public static final String SPORT_ID ="Sportlich";
	private ArrayList<String> elems;
	private String title;
	//map<sportlich, liked>
	private HashMap<String,LikeBox> map = new HashMap<String,LikeBox>();

	public LikeSelectionList() {
		elems = new ArrayList<String>();
		elems.add("Kulturell");
		elems.add("Sportlich");
		elems.add("Freizeit");
		title = "Art des Events";
		init();
	}

	public LikeSelectionList(String title, List<String> elems) {
		this.elems = new ArrayList<String>(elems);
		this.title = title;
		init();
	}

	private void init() {
		GridBagLayout layout = new GridBagLayout();
		layout.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.1 };
		layout.columnWidths = new int[] { 5, 50, 100, 5 };

		double[] rowWeights = new double[elems.size()];
		double[] rowHeights = new double[elems.size()];

		for (int i = 0; i < rowWeights.length; i++) {
			 
			if (i == rowWeights.length - 1) {
				rowWeights[i] = 0.1;
			} else {
				rowWeights[i] = 0.0;
			}
			if (i==0) {
				rowHeights[i]=5;
			}else{
				rowHeights[i] = 20;	
			}
			
		}

		this.setLayout(layout);
		this.setBorder(new TitledBorder(title));

		for (int i= 0; i < elems.size(); i++) {
			final LikeBox lcb = new LikeBox();
			lcb.setBorder(new EmptyBorder(0,0, 5,0));
			map.put(elems.get(i),lcb);
			GridBagConstraints lcbConstrains = new GridBagConstraints();
			lcbConstrains.gridx = 1;
			lcbConstrains.gridy = i;
			this.add(lcb, lcbConstrains);
			GridBagConstraints lblConstrains = new GridBagConstraints();
			lblConstrains.gridx = 2;
			lblConstrains.gridy = i;
			lblConstrains.anchor = GridBagConstraints.WEST;
			final JLabel lbl = new JLabel(elems.get(i));
			lbl.setBorder(new EmptyBorder(0, 5, 0, 0));
			this.add(lbl, lblConstrains);
		}
	}
	
	/**
	 * 
	 * @return for example map<culture,liked>
	 */
	public HashMap<String,String> getSelectionList(){
		final HashMap<String, String> resMap = new HashMap<String, String>();
		for(String s : map.keySet()){
			final String likestring = (String)((LikeBox)map.get(s)).getSelectedItem();
			resMap.put(s,likestring);
		}
		return resMap;
	}
}

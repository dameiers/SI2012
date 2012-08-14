package gui.steps;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import model.steps.DistanceStepModel;
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
public class DistanceStep extends javax.swing.JPanel implements ViewModelConnection{
	private JPanel tiltePnl;
	private JPanel contentPnl;
	private JTextPane hintTxt;
	private JComboBox unitCbo;
	private JTextField distanceTxt;
	private JTextPane titleTxt;
	private DistanceStepModel model = DistanceStepModel.getInstance();

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		frame.getContentPane().add(new DistanceStep());
		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}
	
	public DistanceStep() {
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
					titleTxt.setText("Wie weit darf das Event entfernt sein?");
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
					unitCbo = new JComboBox(new String[] { "Km/h", "Stunden" });
					contentPnl.add(unitCbo);
					unitCbo.setBounds(198, 24, 112, 22);
				}
				{
					hintTxt = new JTextPane();
					contentPnl.add(hintTxt);
					hintTxt.setText("Hinweis: Ohne Auto kann sich die Reisezeit verzoegern!");
					hintTxt.setBounds(12, 79, 391, 35);
					hintTxt.setEditable(false);
					hintTxt.setBackground(new java.awt.Color(255,43,52));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
		public static double[] getLatLon(String city) throws Exception {
		double[] pos = new double[2];
		URL citylat = new URL("http://nominatim.openstreetmap.org/search?q="
				+ city + "&format=xml");
		URLConnection con = citylat.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null){
			if (inputLine.contains("lat='")){
				int index = inputLine.indexOf("lat='");
				String lat = inputLine.substring(index+5, inputLine.indexOf("'", index+5));
				pos[0]=Double.parseDouble(lat);
			}
			if (inputLine.contains("lon='")){
				int index = inputLine.indexOf("lon='");
				String lon = inputLine.substring(index+5, inputLine.indexOf("'", index+5));
				pos[1]=Double.parseDouble(lon);
			}
		}
			
		in.close();
		return pos;
	}
	
	public static double getDistance(String from, String to) throws Exception{
		double[] pos1 = getLatLon(from);
		double[] pos2 = getLatLon(to);
		double dLon = Math.toRadians(pos2[1]-pos1[1]);
		double dLat = Math.toRadians(pos2[0]-pos1[0]);
		double lat1 = Math.toRadians(pos1[0]);
		double lat2 = Math.toRadians(pos2[0]);
		double a = Math.pow(Math.sin(dLat/2), 2)+ Math.pow(Math.sin(dLon/2), 2)* Math.cos(lat1) * Math.cos(lat2); 
		double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return 6371*c;
	}

	@Override
	public void fillModel() {
		// TODO Auto-generated method stub
		model.setDistance(distanceTxt.getText());
		model.setUnit((String)unitCbo.getSelectedItem());
	}

	@Override
	public JComponent getVisualisationUI() {
		return this;
	}

	@Override
	public InformationGatherStepModel getModel() {
		// TODO Auto-generated method stub
		return model;
	}
	
	

}

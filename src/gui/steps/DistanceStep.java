package gui.steps;
import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;

import javax.swing.WindowConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import ontologyAndDB.OntToDbConnection;

import model.steps.DistanceStepModel;
import model.steps.InformationGatherStepModel;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
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
public class DistanceStep extends AbstractViewModelConnectionImpl{
	private JPanel tiltePnl;
	private JPanel contentPnl;
	private JTextPane hintTxt;
	private JComboBox unitCbo;
	private JTextField distanceTxt;
	private JTextPane titleTxt;
	
	private HashMap<String, Double> cityandDist;
	private DefaultTableModel jTable1Model;
	private DistanceStepModel dmodel;

	/**
	* Auto-generated main method to display this 
	* JPanel inside a new JFrame.
	*/
	public static void main(String[] args) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
            	JFrame frame = new JFrame();
        		frame.getContentPane().add(new DistanceStep());
        		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        		frame.pack();
        		frame.setVisible(true);
            }
        });
		
	}
	
	public DistanceStep() {
		super();
		dmodel = DistanceStepModel.getInstance();
		cityandDist = new HashMap<String, Double>();
		
		initGUI();
	}
	
	private void addToHash(String city, double dist){
		cityandDist.put(city, new Double(dist));
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
					unitCbo = new JComboBox(new String[] { "km", "h" });
					contentPnl.add(unitCbo);
					unitCbo.setBounds(198, 24, 112, 22);
				}
				{
					hintTxt = new JTextPane();
					contentPnl.add(hintTxt);
					hintTxt.setText("Hinweis: Ohne Auto kann sich die Reisezeit verzoegern!");
					hintTxt.setBounds(66, 76, 391, 35);
					hintTxt.setEditable(false);
					hintTxt.setBackground(new java.awt.Color(255,43,52));
				}
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	

	
	@Override
	public void fillModel() {
		DistanceStepModel model = DistanceStepModel.getInstance();
		model.setDistance(distanceTxt.getText());
		model.setUnit((String)unitCbo.getSelectedItem());
	}

	@Override
	public InformationGatherStepModel getModel() {
		// TODO Auto-generated method stub
		return DistanceStepModel.getInstance();
	}
	
	class SearchListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			System.out.println(distanceTxt.getText());
			String input = distanceTxt.getText();
			input = input.replace(',', '.');
			final double dist;
			try {
				dist = Double.parseDouble(input);
			} catch (Exception e){
				JOptionPane.showMessageDialog(null, "Die Eingabe ist ungültig.", "Fehler", JOptionPane.ERROR_MESSAGE);
				return;
			}
			jTable1Model.getDataVector().clear();
				Runnable run = new Runnable() {
		            public void run() {
		            	try {
		            		System.out.println("run");
		            		dmodel.getReachableCities(11);
						} catch (Exception e) {
							JOptionPane.showMessageDialog(null, "Bearbeitung fehlerhaft.", "Fehler", JOptionPane.ERROR_MESSAGE);
							e.printStackTrace();
						}
		            }
		        };
			 
			new Thread(run).start();
		}
		
	}

}

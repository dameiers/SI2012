package gui.steps;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import javax.swing.WindowConstants;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.SwingUtilities;

import model.steps.InformationGatherStepModel;
import model.steps.TableviewModel;


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
public class Tableview extends AbstractViewModelConnectionImpl {
	private JSplitPane jSplitPane1;
	private JTextField jTextField11;
	private JLabel jLabel12;
	private JScrollPane jScrollPane1;
	private JLabel jLabel4;
	private JLabel jLabel5;
	private JTextField jTextField10;
	private JLabel jLabel11;
	private JTextField jTextField9;
	private JLabel jLabel10;
	private JTextField jTextField8;
	private JLabel jLabel9;
	private JTextField jTextField7;
	private JLabel jLabel8;
	private JTextField jTextField6;
	private JLabel jLabel7;
	private JTextField jTextField5;
	private JLabel jLabel6;
	private JTextField jTextField4;
	private JTextField jTextField3;
	private JTextField jTextField2;
	private JLabel jLabel3;
	private JTextField jTextField1;
	private JLabel jLabel2;
	private JLabel jLabel1;
	private JPanel jPanel1;
	private JTable jTable1;
	private DefaultTableModel jTable1Model;
	private TableviewModel tvmodel;

	public DefaultTableModel getTableModel() {
		return jTable1Model;
	}

	public void setTableModel(DefaultTableModel jTable1Model) {
		this.jTable1Model = jTable1Model;
	}

	/**
	* Auto-generated main method to display this JFrame
	*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				JFrame frame = new JFrame();
				Tableview inst = new Tableview();
				frame.add(inst, BorderLayout.CENTER);
				frame.pack();
				frame.setVisible(true);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
		});
	}
	
	public Tableview() {
		super();
		initGUI();
	}
	
	private void initGUI() {
		try {
			
			{
				jSplitPane1 = new JSplitPane();
				add(getJSplitPane1(), BorderLayout.CENTER);
				jSplitPane1.setAutoscrolls(true);
				jSplitPane1.setOrientation(JSplitPane.VERTICAL_SPLIT);
				jSplitPane1.setDividerLocation(300);
				jSplitPane1.setPreferredSize(new java.awt.Dimension(592, 604));
				{
					jScrollPane1 = new JScrollPane();
					jSplitPane1.add(jScrollPane1, JSplitPane.RIGHT);
					jScrollPane1.setPreferredSize(new java.awt.Dimension(590, 564));
				}
				{
					jPanel1 = new JPanel();
					jSplitPane1.add(jPanel1, JSplitPane.LEFT);
					jPanel1.setLayout(null);
					{
						jLabel1 = new JLabel();
						jPanel1.add(jLabel1);
						jLabel1.setText("Informationen zum Event");
						jLabel1.setBounds(191, 12, 203, 14);
						jLabel1.setFont(new java.awt.Font("Arial",1,16));
					}
					{
						jLabel2 = new JLabel();
						jPanel1.add(jLabel2);
						jLabel2.setText("Name:");
						jLabel2.setBounds(25, 50, 51, 14);
					}
					{
						jTextField1 = new JTextField();
						jPanel1.add(jTextField1);
						jTextField1.setBounds(65, 50, 105, 21);
					}
					{
						jLabel3 = new JLabel();
						jPanel1.add(jLabel3);
						jLabel3.setText("Startdatum:");
						jLabel3.setBounds(184, 50, 76, 14);
					}
					{
						jTextField2 = new JTextField();
						jPanel1.add(jTextField2);
						jTextField2.setBounds(260, 50, 90, 21);
					}
					{
						jLabel4 = new JLabel();
						jPanel1.add(jLabel4);
						jLabel4.setText("Enddatum:");
						jLabel4.setBounds(368, 50, 60, 14);
					}
					{
						jTextField3 = new JTextField();
						jPanel1.add(jTextField3);
						jTextField3.setBounds(436, 47, 112, 21);
					}
					{
						jLabel5 = new JLabel();
						jPanel1.add(jLabel5);
						jLabel5.setText("Ort:");
						jLabel5.setBounds(25, 90, 38, 14);
					}
					{
						jTextField4 = new JTextField();
						jPanel1.add(jTextField4);
						jTextField4.setBounds(63, 87, 107, 21);
					}
					{
						jLabel6 = new JLabel();
						jPanel1.add(jLabel6);
						jLabel6.setText("Kinderbetreuung:");
						jLabel6.setBounds(184, 90, 118, 14);
					}
					{
						jTextField5 = new JTextField();
						jPanel1.add(jTextField5);
						jTextField5.setBounds(302, 87, 48, 21);
					}
					{
						jLabel7 = new JLabel();
						jPanel1.add(jLabel7);
						jLabel7.setText("Mindestalter:");
						jLabel7.setBounds(368, 90, 90, 14);
					}
					{
						jTextField6 = new JTextField();
						jPanel1.add(jTextField6);
						jTextField6.setBounds(458, 80, 59, 21);
					}
					{
						jLabel8 = new JLabel();
						jPanel1.add(jLabel8);
						jLabel8.setText("Preis: (Kinder)");
						jLabel8.setBounds(25, 132, 98, 14);
					}
					{
						jTextField7 = new JTextField();
						jPanel1.add(jTextField7);
						jTextField7.setBounds(141, 129, 75, 21);
					}
					{
						jLabel9 = new JLabel();
						jPanel1.add(jLabel9);
						jLabel9.setText("Preis: (Erwachsene)");
						jLabel9.setBounds(247, 132, 121, 14);
					}
					{
						jTextField8 = new JTextField();
						jPanel1.add(jTextField8);
						jTextField8.setBounds(380, 129, 86, 21);
					}
					{
						jLabel10 = new JLabel();
						jPanel1.add(jLabel10);
						jLabel10.setText("Kategorie:");
						jLabel10.setBounds(26, 167, 89, 14);
					}
					{
						jTextField9 = new JTextField();
						jPanel1.add(jTextField9);
						jTextField9.setBounds(102, 164, 178, 21);
					}
					{
						jLabel11 = new JLabel();
						jPanel1.add(jLabel11);
						jLabel11.setText("Genre:");
						jLabel11.setBounds(302, 171, 61, 14);
					}
					{
						jTextField10 = new JTextField();
						jPanel1.add(jTextField10);
						jTextField10.setBounds(368, 164, 180, 21);
					}
					{
						jLabel12 = new JLabel();
						jPanel1.add(jLabel12);
						jLabel12.setText("Beschreibung:");
						jLabel12.setBounds(26, 204, 89, 14);
					}
					{
						jTextField11 = new JTextField();
						jPanel1.add(jTextField11);
						jTextField11.setBounds(115, 204, 439, 21);
					}
				}
				{
					 jTable1Model = 
							new DefaultTableModel(
									new String[][] { { "One", "Two" }, { "Three", "Four" } },
									new String[] { "Event ID", "Event Name" });
					jTable1 = new JTable();
					jScrollPane1.setViewportView(jTable1);
					jTable1.setModel(jTable1Model);
					jTable1.setLayout(null);
					jTable1.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

						@Override
						public void valueChanged(ListSelectionEvent arg0) {
							int selectedrow = jTable1.getSelectedRow();
							HashMap<String, String> selectedevent = tvmodel.getEventInfo(selectedrow);
							jTextField1.setText(selectedevent.get("event_id"));
							jTextField2.setText(selectedevent.get("startdatum"));
							jTextField3.setText(selectedevent.get("enddatum"));
							jTextField4.setText(selectedevent.get("ort"));
							jTextField5.setText(selectedevent.get("kinderbetreuung"));
							jTextField6.setText(selectedevent.get("mindestalter"));
							jTextField7.setText(selectedevent.get("preis_kinder"));
							jTextField8.setText(selectedevent.get("preis_erwachsene"));
							jTextField9.setText(selectedevent.get("kategorie"));
							jTextField10.setText(selectedevent.get("genre"));
							jTextField11.setText(selectedevent.get("beschreibung"));
						}
						
					});
				}
			}
			
			setSize(600, 700);
		} catch (Exception e) {
		    //add your error handling code here
			e.printStackTrace();
		}
	}
	
	public JSplitPane getJSplitPane1() {
		return jSplitPane1;
	}

	@Override
	public void fillModel() {
		 tvmodel = TableviewModel.getInstance();
		 tvmodel.fillTableModel();
	}

	@Override
	public InformationGatherStepModel getModel() {
		// TODO Auto-generated method stub
		return null;
	}

}
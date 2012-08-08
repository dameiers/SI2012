package gui.components;

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
import javax.swing.JPanel;

import javax.swing.WindowConstants;
import javax.swing.JFrame;
import javax.swing.JLabel;

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
public class SchoolLocationComponent extends javax.swing.JPanel implements ActionListener {
	private JLabel schoolLocationLbl;
	private JComboBox schoolLocationCbo;
	private JButton removeBtn;
	private JPanel container;

	
	public SchoolLocationComponent(JPanel container) {
		super();
		initGUI();
		this.container = container;
	}
	
	private void initGUI() {
		try {
			GridBagLayout thisLayout = new GridBagLayout();
			this.setPreferredSize(new java.awt.Dimension(400, 48));
			thisLayout.rowWeights = new double[] {0.1};
			thisLayout.rowHeights = new int[] {7};
			thisLayout.columnWeights = new double[] {0.0, 0.0, 0.0, 0.0, 0.1};
			thisLayout.columnWidths = new int[] {20, 100, 200, 50, 5};
			this.setLayout(thisLayout);
			this.setMaximumSize(new java.awt.Dimension(400, 63));
			{
				schoolLocationLbl = new JLabel();
				this.add(schoolLocationLbl, new GridBagConstraints(1, 0, 1, 1, 0.0, 0.0, GridBagConstraints.WEST, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				schoolLocationLbl.setText("Schulort");
			}
			{
				ComboBoxModel schoolLocationCboModel = 
						new DefaultComboBoxModel(
								new String[] { "Item One", "Item Two" });
				schoolLocationCbo = new JComboBox();
				this.add(schoolLocationCbo, new GridBagConstraints(2, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.HORIZONTAL, new Insets(0, 0, 0, 0), 0, 0));
				schoolLocationCbo.setModel(schoolLocationCboModel);
			}
			{
				removeBtn = new JButton();
				this.add(removeBtn, new GridBagConstraints(3, 0, 1, 1, 0.0, 0.0, GridBagConstraints.CENTER, GridBagConstraints.NONE, new Insets(0, 0, 0, 0), 0, 0));
				removeBtn.setText("-");
				removeBtn.addActionListener(this);
				removeBtn.setActionCommand("remove");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void actionPerformed(ActionEvent arg0) {
		if(container.getComponentCount() > 1)
		{
			container.remove(this);
			container.revalidate(); 
			container.repaint();
		}
	}

}

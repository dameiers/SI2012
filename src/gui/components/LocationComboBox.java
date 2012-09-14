package gui.components;

import javax.swing.JComboBox;

public class LocationComboBox extends JComboBox {

	private final static String[] locations = {"Saarbruecken", "Saarlouis", "Lebach"};
	
	public LocationComboBox() {
		super(locations);
	}
	
	public String getSelectedLocation() {
		return locations[getSelectedIndex()];
	}
}

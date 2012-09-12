package gui.components;

import javax.swing.JComboBox;

public class PersonAgeComboBox extends JComboBox {

	private final static String[] ageCaption = {
		"Kind", 
		"Teenager", 
		"Junger Erwachsener",
		"Erwachsener",
		"Alter Erwachsener"
	};
	
	private final static String[] ageKey = {
		"Child", 
		"Teenager", 
		"YoungAdults",
		"Adults",
		"OldAdults"		
	};
	
	public PersonAgeComboBox() {
		super(ageCaption);
	}
	
	public String getSelectedAge() {
		return ageKey[getSelectedIndex()];
	}
}

package gui.components;

import javax.swing.JComboBox;

public class PersonAgeComboBox extends JComboBox {
	
	public static final String CHILD ="Child";
	public static final String TEEN ="Teenager";
	public static final String YOUNG_ADULT ="YoungAdults";
	public static final String ADULT ="Adults";
	public static final String OLD_ADULT ="OldAdults";

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

package model.steps;

import java.util.Collection;

import ontologyAndDB.OntToDbConnection;
import gui.steps.PersonDescriptionStep;


public class PersonDescriptionStepModel extends InformationGatherStepModel 
{
	private static PersonDescriptionStepModel instance;
	
	private final static String[] VALID_GROUPS = {
		"Einzeln",
		"Familie"
	};
	
	private String age;
	private String group;
	
	private PersonDescriptionStepModel() 
	{
		super("Persoenlich", new PersonDescriptionStep());
	}
	
	public static PersonDescriptionStepModel getInstance() 
	{
		if(instance != null)
			return instance;
		
		instance = new PersonDescriptionStepModel();
		return instance;
	}
	
	public String getError() {
		
		if(!hasValidAge())
			return "Ungueltiges Alter";
		
		if(!hasValidGroup())
			return "Ungueltige Gruppe";
		
		return null;
	}
	
	public boolean hasValidAge()
	{	
		return age != null && (
				age.equals("Child") || 
				age.equals("Teenager") || 
				age.equals("YoungAdults") ||
				age.equals("Adults") ||
				age.equals("OldAdults"));
	}
	
	public boolean hasValidGroup()
	{
		for(int i=0; i<VALID_GROUPS.length; i++) {
			if(VALID_GROUPS[i].equals(group)) return true;
		}
		return false;
	}

	public String getAge() 
	{
		return age;
	}

	public void setAge(String age) 
	{
		this.age = age;
		updateAlredayFilled();
	}

	public String getGroup() 
	{
		return group;
	}

	public void setGroup(String group) 
	{
		this.group = group;
		updateAlredayFilled();
	}
	
	public boolean isFamily() 
	{
		return getGroup().equals("Familie");
	}
	
	public boolean isAdult()
	{
		return (age.equals("YoungAdults") ||
				age.equals("Adults") ||
				age.equals("OldAdults"));
	}
	
	public boolean isDriveablePerson() {
		OntToDbConnection onto = OntToDbConnection.getInstance();
		
		onto.removeAllIndividualsOfClass("Person");
		onto.fillOntWithPersons(new String[] {getAge()});
		onto.InfereceAndSaveOntology();
		Collection<Integer> driveablePersonsIds = onto.getInvidualsFromOntologieClassByReasoner("DriveablePerson");
		onto.removeAllIndividualsOfClass("Person");
		
		return driveablePersonsIds.size() != 0;
	}
}

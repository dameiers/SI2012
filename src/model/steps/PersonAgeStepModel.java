package model.steps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import ontologyAndDB.OntToDbConnection;
import ontologyAndDB.exception.OntologyConnectionUnknowClassException;

import gui.steps.PersonAgeStep;

public class PersonAgeStepModel extends InformationGatherStepModel 
{
	private static PersonAgeStepModel instance;
	
	private int[] ages;
	
	private PersonAgeStepModel() 
	{
		super("Personen", new PersonAgeStep());
	}
	
	public static PersonAgeStepModel getInstance() 
	{
		if(instance != null)
			return instance;
		
		instance = new PersonAgeStepModel();
		return instance;
	}

	public String getError() 
	{
		if(ages.length == 0)
			return "Ungültige Anzahl von Altersangaben";
		
		return null;
	}
	
	public boolean hasAdultPerson()
	{
		if(ages == null) 
			return false;
		
		for(int i=0; i<ages.length; i++)
		{
			if(ages[i] >= 18) {
				return true;
			}
		}
		return false;
	}

	public int[] getAges() 
	{
		return ages;
	}

	public void setAges(String[] ages) 
	{
		this.ages = new int[ages.length];
		
		for(int i=1; i<ages.length; i++) {
			this.ages[i] = Integer.parseInt(ages[i]);
		}
		updateAlredayFilled();
	}
	
	public void setAges(int[] ages)
	{
		this.ages = ages;
		updateAlredayFilled();
	}
	
	public String[] getPreferedStuffBasedOnAges() throws OWLOntologyCreationException
	{
		OntToDbConnection ontoConn = OntToDbConnection.getInstance();

		String[] ageClasses = getAgeClasses();
		
		HashSet<String> result = new HashSet<String>();
		
		for(String ageClass : ageClasses) {
			try {
				String className = ageClass + "PreferredEvents";
				
				if(ageClass.equals("Child")) {
					className = ageClass + "FriendlyEvent";
				}
				
				result.addAll(ontoConn.getSubClassesOfClassByOntology(className));
			} catch (OntologyConnectionUnknowClassException e) {
				e.printStackTrace();
			}
		}
		
		String[] strArr = new String[1];
		return result.toArray(strArr);
	}
	
	public String[] getAgeClasses()
	{
		PersonDescriptionStepModel personDescriptionStepModel = PersonDescriptionStepModel.getInstance();
		
		
		if(!isAlredyFilled()) {
			String[] result = {getAgeClass(personDescriptionStepModel.getAge())};
			return result;
		}
		
	    List<String> ageClasses = new ArrayList<String>(); 
	    
	    for(int age : getAges()) {
	    	if(!ageClasses.contains(getAgeClass(age))) {
	    		ageClasses.add(getAgeClass(age));
	    	}
	    }
		
	    String[] strArray = new String[1];
		return ageClasses.toArray(strArray);
	}
	
	public static String getAgeClass(int age)
	{
		if(age < 13)
			return "Child";
		if(age < 18)
			return "Teenager";
		if(age < 30)
			return "YoungAdults";
		if(age < 55)
			return "Adults";
		
		return "OldAdults";
	}
}

package model.steps;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import ontologyAndDB.OntToDbConnection;
import ontologyAndDB.exception.OntologyConnectionUnknowClassException;

import gui.steps.PersonAgeStep;

public class PersonAgeStepModel extends InformationGatherStepModel {
	private static PersonAgeStepModel instance;

	private String[] ages;

	private PersonAgeStepModel() {
		super("Personen", new PersonAgeStep());
	}

	public static int getAvgAgeByAgeGroup(String ageGroup) 
	{
		int ageAsNumber = 0;
		
		if(ageGroup.equals("Child")) {
			ageAsNumber = 5;
		} else if(ageGroup.equals("Teenager")) {
			ageAsNumber = 13;
		} else if(ageGroup.equals("YoungAdults")) {
			ageAsNumber = 24;
		} else if(ageGroup.equals("Adults")) {
			ageAsNumber = 40;
		} else {
			ageAsNumber = 70;
		}
	
		return ageAsNumber;
	}
	
	public static PersonAgeStepModel getInstance() {
		if (instance != null)
			return instance;

		instance = new PersonAgeStepModel();
		return instance;
	}

	public void printToConsole() {
		if (ages == null) {
			System.out.println("Null");
		} else {
			String str = "";
			for (int i = 0; i < ages.length; i++) {
				str += ages[i] + " ";
			}
			System.out.println(str);
		}
	}

	public String getError() {
		if (ages.length == 0)
			return "UngŸltige Anzahl von Altersangaben";

		return null;
	}

	public boolean hasAdultPerson() {
		if (ages == null)
			return false;

		for (int i = 0; i < ages.length; i++) {
			if (ages[i].equals("YoungAdults") || ages[i].equals("Adults")
					|| ages[i].equals("OldAdults")) {
				return true;
			}
		}
		return false;
	}

	public String[] getAgesExcludingOrderingPerson() {
		if (ages == null) {
			return new String[0];
		}

		return ages;
	}
	
	public String[] getAgesIncludingOrderingPerson() {
		PersonDescriptionStepModel pdsm = PersonDescriptionStepModel.getInstance();
		
		ArrayList<String> result = new ArrayList<String>();
		
		if(isAlredyFilled()) {
			result.addAll(Arrays.asList(getAgesExcludingOrderingPerson()));
		}
		
		result.add(pdsm.getAge());
		String[] tmpArr = new String[result.size()];
		
		for(int i=0; i<result.size(); i++) {
			tmpArr[i] = result.get(i);
		}
		
		return tmpArr;
	}

	public void setAges(String[] ages) {
		this.ages = ages;
		updateAlredayFilled();
	}
	
	public boolean containsAgeClass(String ageClass) {
				
		Collection<String> ageClasses = new LinkedList<String>();
		ageClasses.addAll(Arrays.asList(getAgesIncludingOrderingPerson()));
		
		for (String age : ageClasses) {
			if (age.equals(ageClass)) {

				return true;
			}
		}

		return false;
	}

	public Collection<String> getPupils() {
		Collection<String> result = new LinkedList<String>();

		ages = getAgesIncludingOrderingPerson();
		if (ages != null) {
			for (int i = 0; i < ages.length; i++) {
				if (ages[i].equals("Child") || ages[i].equals("Teenager")
						|| ages[i].equals("YoungAdults")) {
					result.add(ages[i]);
				}
			}
		}

		return result;
	}
	
	public String[] getPrefferedStuffBasedOnAgeClass(String ageClass)
	{
		OntToDbConnection ontoConn = OntToDbConnection.getInstance();
		HashSet<String> result = new HashSet<String>();
		
		String className = ageClass + "PreferredEvents";
		result.addAll(ontoConn.getSubClassesOfClassByOntology(className));
		result.addAll(getCorrespondingEventCategoriesFromGenres(filterGenresFromPreferedStuff(result)));
		
		String[] strArr = new String[1];
		return result.toArray(strArr);
	}

	public String[] getPreferedStuffBasedOnAgeClasses()
			throws OWLOntologyCreationException 
	{
		String[] ageClasses = getAgesIncludingOrderingPerson();

		HashSet<String> result = new HashSet<String>();

		for (String ageClass : ageClasses) {
			String[] tmpResult = getPrefferedStuffBasedOnAgeClass(ageClass);
			for(String likedStuff : tmpResult) {
				result.add(likedStuff);
			}
		}
		
		result = filterEventsConsidereingSpacialAgeClasses(result);
		
		String[] strArr = new String[1];
		return result.toArray(strArr);
	}
	
	public boolean hasDriveablePersons() {
		OntToDbConnection onto = OntToDbConnection.getInstance();
		
		onto.removeAllIndividualsOfClass("Person");
		onto.fillOntWithPersons(getAgesIncludingOrderingPerson());
		onto.InfereceAndSaveOntology();
		Collection<Integer> driveablePersonsIds = onto.getInvidualsFromOntologieClassByReasoner("DriveablePerson");
		onto.removeAllIndividualsOfClass("Person");
		
		return driveablePersonsIds.size() != 0;
	}
	
	private HashSet<String> filterEventsConsidereingSpacialAgeClasses(
			HashSet<String> events) throws OWLOntologyCreationException {

		HashSet<String> result = events;
		
		if (containsAgeClass("Child")) 
		{
			System.out.println("Da ein Kind in der Gruppe ist werden nur Kinderfreundliche Events betrachtet");
		
			result = new HashSet<String>();
			String[] childPrefStuff = getPrefferedStuffBasedOnAgeClass("Child");
			
			for(String prefStuff : childPrefStuff) {
				result.add(prefStuff);
			}
			
		} else if(containsAgeClass("Teenager")) 
		{
			System.out.println("Da ein Jugentlicher in der Gruppe ist werden nur ensprechent geeignete Events betrachtet");
			
			result = new HashSet<String>();
			String[] teenPrefStuff = getPrefferedStuffBasedOnAgeClass("Teenager");
			
			for(String prefStuff : teenPrefStuff) {
				result.add(prefStuff);
			}
		}

		return result;
	}
	

	private Collection<String> filterGenresFromPreferedStuff(
			Collection<String> likedStuff) {
		List<String> result = new ArrayList<String>();

		for (String item : likedStuff) {
			if (Pattern.matches(".*Genre$", item)) {
				result.add(item);
			}
		}

		return result;
	}

	private Collection<String> getCorrespondingEventCategoriesFromGenres(
			Collection<String> genres) {
		HashSet<String> result = new HashSet<String>();

		OntToDbConnection onto = OntToDbConnection.getInstance();

		for (String genre : genres) {

			result.addAll(mapGenreCategoriesToEventCategories(onto
					.getSuperClassesOfClassFromOntology(genre)));
		}

		return result;
	}

	private Collection<String> mapGenreCategoriesToEventCategories(
			Collection<String> genreCats) {
		Collection<String> result = new ArrayList<String>();

		for (String genreCat : genreCats) {
			result.add(genreCat.replace("Genres", "Event"));
		}

		return result;
	}
}

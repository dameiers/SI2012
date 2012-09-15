package model.steps;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
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

	public static PersonAgeStepModel getInstance() {
		if (instance != null)
			return instance;

		instance = new PersonAgeStepModel();
		return instance;
	}

	public String getError() {
		if (ages.length == 0)
			return "Ungültige Anzahl von Altersangaben";

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

	public String[] getAges() {
		if (ages == null) {
			return new String[0];
		}

		return ages;
	}

	public void setAges(String[] ages) {
		this.ages = ages;
		updateAlredayFilled();
	}

	public String[] getPreferedStuffBasedOnAges()
			throws OWLOntologyCreationException {
		OntToDbConnection ontoConn = OntToDbConnection.getInstance();
		PersonDescriptionStepModel pdsm = PersonDescriptionStepModel
				.getInstance();

		String[] ageClasses = getAges();

		HashSet<String> result = new HashSet<String>();

		for (String ageClass : ageClasses) {
			String className = ageClass + "PreferredEvents";
			result.addAll(ontoConn.getSubClassesOfClassByOntology(className));
		}

		String className = pdsm.getAge() + "PreferredEvents";
		result.addAll(ontoConn.getSubClassesOfClassByOntology(className));

		result.addAll(getCorrespondingEventCategoriesFromGenres(filterGenresFromPreferedStuff(result)));

		String[] strArr = new String[1];
		return result.toArray(strArr);
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

		System.out.println("-------");
		System.out.println(result);
		System.out.println("-------");

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

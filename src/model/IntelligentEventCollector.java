package model;

import gui.components.LikeBox;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

import javax.swing.SwingUtilities;

import model.steps.DistanceStepModel;
import ontologyAndDB.OntToDbConnection;

public class IntelligentEventCollector extends EventCollector implements
		ActionListener {

	private static IntelligentEventCollector instance;
	private HashSet<String> alreadyLaodedClasses = new HashSet<String>();
	private HashSet<String> newLaodedClasses = new HashSet<String>();

	private IntelligentEventCollector() {

	}

	public static IntelligentEventCollector getInstance() {
		if (instance == null) {
			instance = new IntelligentEventCollector();
		}

		return instance;
	}

	@Override
	public void setDistanceView() {
		ontToDbConnection.removeAllIndividuals();

		final String distanceUnit = distanceStep.getUnit();
		ArrayList<String> reachableCities = null;
		try {
			if (distanceUnit.equals(DistanceStepModel.DISTANCE_UNTI)) {

				reachableCities = distanceStep
						.getReachableCitiesByDistance(Double
								.parseDouble(distanceStep.getDistance()));
			} else {
				reachableCities = distanceStep
						.getReachableCitiesByTime(60 * 60 * 1000 * Double
								.parseDouble(distanceStep.getDistance()));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		if (reachableCities != null) {
			ontToDbConnection.setDistanceView(reachableCities);
		}

		// calculate the events that are preffered and laod them into the
		// ontology
		final Set<Integer> prefetchEvents = new HashSet<Integer>();
		// take the ageCategories
		final String[] ageCategories = personAgeStep
				.getAgesIncludingOrderingPerson();

		// and calculate the preffered categories and genres
		for (int i = 0; i < ageCategories.length; i++) {
			final String ageClass = ageCategories[i];
			OntToDbConnection ontoConn = OntToDbConnection.getInstance();

			// take the corresponding classes
			String className = ageClass + "PreferredEvents";
			final ArrayList<String> preferredCats = ontoConn
					.getSubClassesOfClassByOntology(className);
			String genres = "";
			String categories = "";
			for (String category : preferredCats) {
				if (Pattern.matches(".*Genre$", category)) {
					genres = genres + "'" + category + "',";
				} else {
					categories = categories + "'" + category + "',";
				}
				alreadyLaodedClasses.add(category);
			}

			String prefferedGenreQuery = "select view.name, view.startdatum, view.enddatum, view.ort, view.kinderbetreuung, view.mindestalter, view.event_id, kat.kategorie_name, genre_name, view.kinderfreundlich, kat.kategorie_id, genre.genre_name from reachablecitiesview view"
					+ " left join \"Event\" event on view.event_id = event.event_id left join \"Kategorie\" kat on view.kategorie = kat.kategorie_id"
					+ " left join \"Event_Genre\" foo on foo.event = event.event_id"
					+ " left join \"Genre\" genre on foo.genre = genre.genre_id"
					+ " where genre_name in (" + genres + ")";

			String prefferedCategoryQuery = "select view.name, view.startdatum, view.enddatum, view.ort, view.kinderbetreuung, view.mindestalter, view.event_id, kat.kategorie_name, genre_name, view.kinderfreundlich, kat.kategorie_id, genre.genre_name from reachablecitiesview view"
					+ " left join \"Event\" event on view.event_id = event.event_id left join \"Kategorie\" kat on view.kategorie = kat.kategorie_id"
					+ " left join \"Event_Genre\" foo on foo.event = event.event_id"
					+ " left join \"Genre\" genre on foo.genre = genre.genre_id"
					+ " where genre_name in (" + categories + ")";

			prefferedCategoryQuery = prefferedCategoryQuery.substring(0,
					prefferedCategoryQuery.lastIndexOf(','));
			prefferedCategoryQuery = prefferedCategoryQuery + ")";
			prefferedGenreQuery = prefferedGenreQuery.substring(0,
					prefferedGenreQuery.lastIndexOf(','));
			prefferedGenreQuery = prefferedGenreQuery + ")";

			// fetch the events that fulfill the constraint of category and
			// genre from distanceview
			ResultSet rs = ontToDbConnection.executeQuery(prefferedGenreQuery
					+ " union " + prefferedCategoryQuery);
			ontToDbConnection.fillOntWithEvents(rs);
			// if (rs != null) {
			// try {
			// while (rs.next()) {
			// final Integer id = rs.getInt("event_id");
			// prefetchEvents.add(id);
			// }
			// } catch (SQLException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			// }
		}
		// put them into the ontology
		// ontToDbConnection.fillOntWithEvents(prefetchEvents);
		// ontToDbConnection.fillOntWithEventsFromDistanceView();

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				ontToDbConnection.InfereceAndSaveOntology();
			}
		});

	}

	@Override
	public void actionPerformed(ActionEvent e) {

		if (e.getSource() instanceof LikeBox) {
			LikeBox lb = (LikeBox) e.getSource();
			final String key = lb.getKey();
			if (((String) lb.getSelectedItem()).equals(LikeBox.DONTLIKE)) {
				// remove from ontology
				if (alreadyLaodedClasses.contains(key)) {
					ontToDbConnection.removeAllIndividualsOfClass(key);
					ontToDbConnection.InfereceAndSaveOntology();
					alreadyLaodedClasses.remove(key);
				}
			} else {
				if (!alreadyLaodedClasses.contains(key) ) {
					final Set<Integer> prefetchEvents = new HashSet<Integer>();
					String query = "";
					if (Pattern.matches(".*Genre$", key)) {
						query = "select view.name, view.startdatum, view.enddatum, view.ort, view.kinderbetreuung, view.mindestalter, view.event_id, kat.kategorie_id, genre_name, view.kinderfreundlich from reachablecitiesview view"
								+ " left join \"Event\" event on view.event_id = event.event_id left join \"Kategorie\" kat on view.kategorie = kat.kategorie_id"
								+ " left join \"Event_Genre\" foo on foo.event = event.event_id"
								+ " left join \"Genre\" genre on foo.genre = genre.genre_id"
								+ " where genre_name in ('" + key + "')";
					} else {
						query = "select view.name, view.startdatum, view.enddatum, view.ort, view.kinderbetreuung, view.mindestalter, view.event_id, kat.kategorie_id, genre_name, view.kinderfreundlich from reachablecitiesview view"
								+ " left join \"Event\" event on view.event_id = event.event_id left join \"Kategorie\" kat on view.kategorie = kat.kategorie_id"
								+ " left join \"Event_Genre\" foo on foo.event = event.event_id"
								+ " left join \"Genre\" genre on foo.genre = genre.genre_id"
								+ " where kategorie_name in ('" + key + "')";
					}

					ResultSet rs = ontToDbConnection.executeQuery(query);
					ontToDbConnection.fillOntWithEvents(rs);
					alreadyLaodedClasses.add(key);
					newLaodedClasses.add(key);
					ontToDbConnection.InfereceAndSaveOntology();
				}
			}
		}
	}
	
	public void addEventstoOnto(HashMap<String,String> events){
		for(String elem : events.keySet()) {
			String value = events.get(elem);
			if (value.equals(LikeBox.DONTLIKE)) {
				// remove from ontology
				if (alreadyLaodedClasses.contains(elem)) {
					ontToDbConnection.removeAllIndividualsOfClass(elem);
					ontToDbConnection.InfereceAndSaveOntology();
					alreadyLaodedClasses.remove(elem);
				}
			} else {
				if (!alreadyLaodedClasses.contains(elem) ) {
					String query = "";
					if (Pattern.matches(".*Genre$", elem)) {
						query = "select view.name, view.startdatum, view.enddatum, view.ort, view.kinderbetreuung, view.mindestalter, view.event_id, kat.kategorie_id, genre_name, view.kinderfreundlich from reachablecitiesview view"
								+ " left join \"Event\" event on view.event_id = event.event_id left join \"Kategorie\" kat on view.kategorie = kat.kategorie_id"
								+ " left join \"Event_Genre\" foo on foo.event = event.event_id"
								+ " left join \"Genre\" genre on foo.genre = genre.genre_id"
								+ " where genre_name in ('" + elem + "')";
					} else {
						query = "select view.name, view.startdatum, view.enddatum, view.ort, view.kinderbetreuung, view.mindestalter, view.event_id, kat.kategorie_id, genre_name, view.kinderfreundlich from reachablecitiesview view"
								+ " left join \"Event\" event on view.event_id = event.event_id left join \"Kategorie\" kat on view.kategorie = kat.kategorie_id"
								+ " left join \"Event_Genre\" foo on foo.event = event.event_id"
								+ " left join \"Genre\" genre on foo.genre = genre.genre_id"
								+ " where kategorie_name in ('" + elem + "')";
					}

					ResultSet rs = ontToDbConnection.executeQuery(query);
					ontToDbConnection.fillOntWithEvents(rs);
					alreadyLaodedClasses.add(elem);
					newLaodedClasses.add(elem);
					ontToDbConnection.InfereceAndSaveOntology();
				}
			}
		}
		
	}
	
	public void clearNewAddedEvents(){
		for (String elem : newLaodedClasses){
			alreadyLaodedClasses.remove(elem);
		}
		newLaodedClasses.clear();
	}


}

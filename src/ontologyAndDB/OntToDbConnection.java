package ontologyAndDB;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Set;

import model.steps.DistanceStepModel;
import model.steps.PersonAgeStepModel;
import ontologyAndDB.exception.OWLConnectionUnknownTypeException;
import ontologyAndDB.exception.OntologyConnectionDataPropertyException;
import ontologyAndDB.exception.OntologyConnectionIndividualAreadyExistsException;
import ontologyAndDB.exception.OntologyConnectionUnknowClassException;
import ontologyAndDB.exception.ViewDoesntExistsException;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.reasoner.ClassExpressionNotInProfileException;
import org.semanticweb.owlapi.reasoner.FreshEntitiesException;
import org.semanticweb.owlapi.reasoner.InconsistentOntologyException;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.ReasonerInterruptedException;
import org.semanticweb.owlapi.reasoner.TimeOutException;
import org.semanticweb.owlapi.util.OWLEntityRemover;

public class OntToDbConnection {

	private static OntToDbConnection instance;

	private DBConnection dbCon;
	private OntologyConnection ontCon;
	// private boolean reachCitiesViewIsSet;
	// private boolean holidayViewIsSet;

	private final String HOLIDAY_VIEW_NAME = "holidayview";
	private final String REACHABLE_CITIES_VIEW_NAME = "reachablecitiesview";
	public static String THEATRE_GENRE = "TheatreGenre";
	public static String Cinema_GENRE = "CinemaGenre";
	public static String CONCERT_GENRE = "ConcertGenre";

	// ///////////////////////////////////////////////// Constructor
	// //////////////////////////////////////////////////////

	public static OntToDbConnection getInstance() {
		if (instance == null) {
			instance = new OntToDbConnection();
//			instance.openOntology("evntologie_latest.owl");
			instance.createAndOpenWorkingCopy();
		}
		return instance;
	}

	private OntToDbConnection() {
		dbCon = new DBConnection();
		ontCon = new OntologyConnection();
//		createAndOpenWorkingCopy();
		// holidayViewIsSet=false;
		// reachCitiesViewIsSet=false;
	}

	// ///////////////////////////////////////////////// Ontologie -
	// Manipulation- Methods////////////////////////////////

	public void removeAllIndividuals() {
		ontCon.removeAllIndividuals();
	}
	
	public void removeAllIndividualsOfClass(String className) {
		ontCon.removeAllIndividualsOfClass(className);
	}

	public void removeIndividualsFromClass(String className) {
		ontCon.removeAllIndividualsOfClass(className);
	}

	public void openOntology(String ontologyFilePath) {
		ontCon.openOntology(ontologyFilePath);
	}
	
	private void reopenOntology() {
		ontCon.reopenOntology();
	}
	
	public void createAndOpenWorkingCopy(){
		ontCon.saveOntologieToWorkingCopy();
		ontCon.openOntology("eventologie_latest_workingcopy.owl");
	}

	public void InfereceAndSaveToFile(String owlFilePath) {
		ontCon.preAndSave(owlFilePath);
		reopenOntology();
	}

	public void InfereceAndSaveOntology() {
		ontCon.preAndSave();
		reopenOntology();
	}

	public void fillOntWithAllEvents() {
		ResultSet rs;
		rs = dbCon.executeQuery("Select * from \"Event\"");
		fillOntWithEvents(rs);

	}
	
	public void fillOntWithEvents(Set<Integer> events){
		ResultSet rs;
		String s = "";
		for(Integer i : events){
			s = s+i+",";
		}
		s = s.substring(0,s.lastIndexOf(","));
		rs = dbCon.executeQuery("select * from \"Event\" where event_id in ("+s+")");
		fillOntWithEvents(rs);
	}

	public void fillOntWithEventsUntilNumber(int eventNumber)
			throws SQLException, OntologyConnectionDataPropertyException,
			OWLConnectionUnknownTypeException,
			OntologyConnectionIndividualAreadyExistsException,
			OntologyConnectionUnknowClassException {
		ResultSet rs = dbCon
				.executeQuery("Select * from \"Event\" WHERE event_id < "
						+ eventNumber);
		fillOntWithEvents(rs);
	}

	public void fillOntWithEventsFromDistanceView() {
		ResultSet rs;
		rs = dbCon.executeQuery("SELECT * FROM \"" + REACHABLE_CITIES_VIEW_NAME
				+ "\"");
		fillOntWithEvents(rs);

	}
	
	/**
	 * @param ages The agegroups per exaple: "Child" or "Teenager"
	 */
	public void fillOntWithPersons(String[] ages) {
		Calendar c = new GregorianCalendar();
		long millis = c.getTimeInMillis();
		
		for(int i=0; i<ages.length; i++) {
			putPersonIndividumIntoOnt(ages[i], String.valueOf((millis+i)).substring(6));
		}
		
		ontCon.saveOntologie();
	}
	
	private void putPersonIndividumIntoOnt(String age, String id) {
		int AgeAsNumber = PersonAgeStepModel.getAvgAgeByAgeGroup(age);
			
		try {
			
			OWLNamedIndividual individ = ontCon.createIndividual(id);
			ontCon.addIndividualToClass(individ, "Person");
			ontCon.setObjectPropertieToIndividual(individ, "hasAge", AgeAsNumber);
			
		} catch (OntologyConnectionIndividualAreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OntologyConnectionDataPropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OWLConnectionUnknownTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OntologyConnectionUnknowClassException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}

	public void fillOntWithEvents(ResultSet dataBaseEvents) {
		ResultSet rs2;
		ResultSet rs3;
		ResultSet rs4;
		try {
			while (dataBaseEvents.next()) {
				// Individual erzeugen
				OWLNamedIndividual individ = ontCon.createIndividual(String
						.valueOf(dataBaseEvents.getInt("event_id")));
				// data propertie : Kinderbetreeung
				ontCon.setObjectPropertieToIndividual(individ, "hasChildCare",
						dataBaseEvents.getBoolean("kinderbetreuung"));
				// data propertie: childfriendly
				ontCon.setObjectPropertieToIndividual(individ,
						"isChildFriendly",
						dataBaseEvents.getBoolean("kinderfreundlich"));
				// data propertie : hasconcreteDuration
				long timeDiff = dataBaseEvents.getTimestamp("enddatum")
						.getTime()
						- dataBaseEvents.getTimestamp("startdatum").getTime();
				int durationInDays = (int) (timeDiff / 1000 / 3600 / 24);
				ontCon.setObjectPropertieToIndividual(individ,
						"hasConcreteDuration", durationInDays);
				// hinzufügen zur passenden Event-Klasse
				rs2 = (dbCon
						.executeQuery("select kategorie_name from \"Kategorie\" where kategorie_id="
								+ dataBaseEvents.getInt("kategorie_id")));
				rs2.next();
				String eventKategorie = rs2.getString(1);
				ontCon.addIndividualToClass(individ, eventKategorie);
				// falls genre gesetzt ist , hinzufügen zur Genre-Klasse
				rs3 = (dbCon
						.executeQuery("select genre from \"Event_Genre\" where event="
								+ String.valueOf(dataBaseEvents
										.getInt("event_id"))));
				if (rs3.next()) {
					int genreID = rs3.getInt(1);
					rs4 = (dbCon
							.executeQuery("select genre_name from \"Genre\" where genre_id="
									+ String.valueOf(genreID)));
					rs4.next();
					String genreKategorie = rs4.getString(1);
					ontCon.addIndividualToClass(individ, genreKategorie);
				}
				ontCon.saveOntologie();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OntologyConnectionIndividualAreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OntologyConnectionDataPropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OWLConnectionUnknownTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OntologyConnectionUnknowClassException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// //////////////////////////////// Distance-Methods
	// /////////////////////////////////////////////////////////////

	/**
	 * Returns the Cities which occur in the DB
	 * 
	 * @throws SQLException
	 */
	public ArrayList<String> getCitiesFromDB() {
		ArrayList<String> cities = new ArrayList<String>();
		ResultSet rs;
		try {
			rs = dbCon.executeQuery("Select Distinct ort from \""
					+ HOLIDAY_VIEW_NAME + "\"");
			while (rs.next()) {
				cities.add(rs.getString("ort"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return cities;
	}

	/**
	 * Creates a view in the DB with all reachable Events
	 * 
	 * @param reachableCities
	 *            all Cities that are reachable
	 * @throws SQLException
	 */
	public void setDistanceView(ArrayList<String> reachableCities) {

		String sqlInStat = "";
		for (String city : reachableCities) {
			sqlInStat = sqlInStat.concat("'" + city + "' ,");
		}
		
		String cities = "";
		
		if (reachableCities.size()>0) 
		{
			cities = sqlInStat.substring(0, sqlInStat.lastIndexOf(","));
		}
		else
		{
			cities = "'_'";
		}
		
		String sqlStatement = " SELECT * FROM \"" + HOLIDAY_VIEW_NAME
				+ "\" WHERE ort IN (" + cities + ")";
		dbCon.createView(REACHABLE_CITIES_VIEW_NAME, sqlStatement);
	}

	// //////////////////////////////////////////////////////////
	// Time-Methods//////////////////////////////////////////

	// TODO Format der Eingabe mit der Übergabe von der GUI anpassen
	public void setHolidayView(String startDate, String endDate) {
		String sqlStatement = "";
		sqlStatement = " SELECT * FROM \"Event\" WHERE startdatum >= '"
				+ startDate + " 00:00:00' AND enddatum <= '" + endDate + " 23:59:00'";
		dbCon.createView(HOLIDAY_VIEW_NAME, sqlStatement);
	}

	// ////////////////////////////////////////Reasoner Based Methods
	// ////////////////////////////////////////////////////////

	/**
	 * Returns the direct Subclases of a Class
	 * 
	 * @param className
	 *            the Name of the superclass
	 * @return Non rekursive list of subclasses
	 * @throws OntologyConnectionUnknowClassException
	 */
	public ArrayList<String> getSubClassesOfClassByReasoner(String className) {
		ArrayList<String> result = null;
		try {
			result = ontCon.getClassNamesOnly(ontCon
					.getSubClassesByReasoner(className));
		} catch (OntologyConnectionUnknowClassException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<String> getSuperClassesOfClassByReasoner(String className) {
		return ontCon.getSuperClassesByReasoner(className);

	}

	public ArrayList<Integer> getInvidualsFromOntologieClassByReasoner(
			String className) {
		ArrayList<Integer> result = new ArrayList<Integer>();
		try {
			result = ontCon.getEventIdsByClassByReasoner(className);
		} catch (InconsistentOntologyException e) {
			e.printStackTrace();
		} catch (ClassExpressionNotInProfileException e) {
			e.printStackTrace();
		} catch (FreshEntitiesException e) {
			e.printStackTrace();
		} catch (TimeOutException e) {
			e.printStackTrace();
		} catch (ReasonerInterruptedException e) {
			e.printStackTrace();
		} catch (OntologyConnectionUnknowClassException e) {
			e.printStackTrace();
		}
		return result;
	}

	public ArrayList<Integer> getIndividualUnionOverClassesByReasoner(
			ArrayList<String> classNames) {
		ArrayList<Integer> individs = new ArrayList<Integer>();
		for (String s : classNames) {
			individs.addAll(this.getInvidualsFromOntologieClassByReasoner(s));
		}
		return individs;
	}

	public ArrayList<Integer> getIndividualIntersectionOverClassesByReasoner(
			ArrayList<String> classNames) throws IllegalStateException {
		if (classNames.size() < 2)
			throw new IllegalStateException(
					"2 ore more Classes needed to make an intersection");
		ArrayList<Integer> list1;
		ArrayList<Integer> list2;
		ArrayList<Integer> list3 = this
				.getInvidualsFromOntologieClassByReasoner(classNames.get(0));
		for (int i = 1; i < classNames.size(); i++) {
			list1 = list3;
			list3 = new ArrayList<Integer>();
			list2 = this.getInvidualsFromOntologieClassByReasoner(classNames
					.get(i));
			for (Integer s : list1) {
				if (list2.contains(s))
					list3.add(s);
			}
		}
		return list3;
	}

	// ////////////////////////////////////////Ontology Based Queries
	// ////////////////////////////////////////////////////////

	public ArrayList<String> getSuperClassesOfClassFromOntology(String className) {
		return ontCon.getSuperClassesByClassFromOntology(className);
	}

	public ArrayList<Integer> getEventIdsByClassByOntology(String className) {
		return ontCon.getEventIdsByClassByOntology(className);
	}

	public ArrayList<String> getSubClassesOfClassByOntology(String className) {
		return ontCon.getSubClassesByClassFromOntology(className);
	}

	// ////////////////////////////////////////////////Database
	// Methods//////////////////////////////////////////////////////////////////

	public HashMap<String, double[]> getCityPositions() {

		HashMap<String, double[]> pos = new HashMap<String, double[]>();

		ResultSet rs = dbCon.executeQuery("Select * FROM \"Stadt\"");
		try {
			while (rs.next()) {
				double lat = rs.getDouble(2);
				double lon = rs.getDouble(3);
				double d[] = { lat, lon };
				pos.put(rs.getString("stadt_name"), d);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return pos;
	}

	public ResultSet getDataFromDbByEvent_Id(ArrayList<Integer> eventIDs){
		if (eventIDs != null && !eventIDs.isEmpty()) {
			String s = new String(" ");
			int i;
			for (i = 0; i < eventIDs.size() - 1; i++) {
				s = s.concat(String.valueOf(eventIDs.get(i)) + ",");
			}
			s = s.concat(String.valueOf(eventIDs.get(i)));
			/*
			String sqlStatement = "SELECT * "
					+ "FROM 	\"Event\" ,\"Event_Genre\" ,\"Genre\" ,\"Kategorie\" , \"Preisliste\" "
					+ "WHERE  \"Event\".event_id = \"Event_Genre\".event "
					+ "AND	\"Event_Genre\".genre = \"Genre\".genre_id "
					+ "AND 	\"Event\".kategorie = \"Kategorie\".kategorie_id "
					+ "AND	\"Event\".event_id = \"Preisliste\".event "
					+ "AND event_id in (" + s + ")";
			*/
			
			String sqlStatement = "SELECT name, startdatum, enddatum, ort, kinderbetreuung, kinderfreundlich, mindestalter, event_id, beschreibung, kategorie_name, kinder, erwachsene, ermaessigt, genre_name FROM \"Event\" event join \"Kategorie\" kategorie on event.kategorie = kategorie.kategorie_id" 
					+ " left join \"Preisliste\" preis on preis.event = event.event_id" 
					+ " left join \"Event_Genre\" eventGenre on event.event_ID = eventGenre.event" 
					+ " left join \"Genre\" genre on eventGenre.genre = genre.genre_id"
					+ " where event_id in ("+s +")";
			
			// System.out.println(sqlStatement.toString());
			return dbCon.executeQuery(sqlStatement);
		} else {
			System.out.println("received an empty list of event id's");
			return null;
		}

	}

	public void disconnectFromDB() {
		dbCon.disconnect();
	}

	public ArrayList<Integer> EventsFromDBByMaxCosts(int numberGrownUp,
			int numberChilden, int numberReducedCost, int MaxSum,
			ArrayList<Integer> searchField) {
		ArrayList<Integer> events = new ArrayList<Integer>();
		ResultSet rs = dbCon
				.executeQuery("Select * from \"Event\" where \"event_id\" in ("
						+ searchField + ")");
		try {
			while (rs.next()) {
				int event_id = rs.getInt("event_id");
				ResultSet rs2 = dbCon
						.executeQuery("SELECT * FROM \"Preisliste\" WHERE event = "
								+ event_id);
				if (rs2.getInt("kinder") * numberChilden
						+ rs2.getInt("erwachsene") * numberChilden
						+ rs2.getInt("ermaessigt") * numberReducedCost < MaxSum)
					events.add(event_id);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return events;
	}

	public ResultSet executeQuery(String sqlStatement) {
		return dbCon.executeQuery(sqlStatement);
	}

	public void executeUpdate(String sqlStatement) {
		dbCon.executeUpdate(sqlStatement);
	}

}
package ontologyAndDB;
import java.sql.*;
import java.util.ArrayList;
import java.util.Set;

import ontologyAndDB.exception.OWLConnectionUnknownTypeException;
import ontologyAndDB.exception.OntologyConnectionDataPropertyException;
import ontologyAndDB.exception.OntologyConnectionIndividualAreadyExistsException;
import ontologyAndDB.exception.OntologyConnectionUnknowClassException;
import ontologyAndDB.exception.ViewDoesntExistsException;

import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.util.OWLEntityRemover;



public class OntToDbConnection {
	
	private 	DBConnection 		dbCon;
	private		OntologyConnection  ontCon;
	private 	boolean				reachCitiesViewIsSet;
	private		boolean				holidayViewIsSet;
	
	private final String HOLIDAY_VIEW_NAME ="HolidayView";
	private final String REACHABLE_CITIES_VIEW_NAME ="ReachableCitiesView";
	
	public OntToDbConnection(){
		 dbCon = new DBConnection();
		 ontCon = new OntologyConnection("evntologie_latest.owl");
		 holidayViewIsSet=false;
		 reachCitiesViewIsSet=false;
	}
	/////////////////////////////////////////////////// Fill Ontologie /////////////////////////////////////////////////////
	
	public void fillOntWithAllEvents() throws SQLException, OntologyConnectionDataPropertyException, OWLConnectionUnknownTypeException, OntologyConnectionIndividualAreadyExistsException, OntologyConnectionUnknowClassException{	
	  ResultSet rs = dbCon.executeQuery("Select * from \"Event\"");
	  fillOntWithEvents(rs);
	}
	public void fillOntWithEvents () throws SQLException, OntologyConnectionDataPropertyException, OWLConnectionUnknownTypeException, OntologyConnectionIndividualAreadyExistsException, OntologyConnectionUnknowClassException, ViewDoesntExistsException{
		if (!holidayViewIsSet)
				throw new ViewDoesntExistsException(HOLIDAY_VIEW_NAME + " doesnt exist yet");
		ResultSet rs = dbCon.executeQuery("SELECT * FROM \""+HOLIDAY_VIEW_NAME+"\"");
		 fillOntWithEvents(rs);
	}
	
	private void fillOntWithEvents(ResultSet dataBaseEvents) throws SQLException, OntologyConnectionDataPropertyException, OWLConnectionUnknownTypeException, OntologyConnectionIndividualAreadyExistsException, OntologyConnectionUnknowClassException{	
		  ResultSet rs2;
		  ResultSet rs3;
		  ResultSet rs4;
		  while(dataBaseEvents.next()){
			 
			  // Individual erzeugen
			  OWLNamedIndividual individ = ontCon.createIndividual(String.valueOf(dataBaseEvents.getInt("event_id")));
			  
			  // data propertie : Kinderbetreeung
			  ontCon.setObjectPropertieToIndividual(individ, "hasChildCare", dataBaseEvents.getBoolean("kinderbetreuung"));
			  // data propertie: childfriendly
			  ontCon.setObjectPropertieToIndividual(individ, "isChildFriendly", dataBaseEvents.getBoolean("kinderfreundlich"));
			  // data propertie : hasconcreteDuration 
			  long timeDiff = dataBaseEvents.getTimestamp("enddatum").getTime() - dataBaseEvents.getTimestamp("startdatum").getTime();
			  int durationInDays = (int)(timeDiff / 1000 / 3600 / 24) ;
			  ontCon.setObjectPropertieToIndividual(individ, "hasConcreteDuration", durationInDays+1);
			  // hinzufügen zur passenden Event-Klasse
			  rs2 = (dbCon.executeQuery("select bezeichnung from \"Kategorie\" where kategorie_id="+  dataBaseEvents.getInt("kategorie") ));
			  rs2.next();
			  String eventKategorie = rs2.getString(1);
			  ontCon.addIndividualToClass(individ, eventKategorie);
			 
			  // falls genre gesetzt ist , hinzufügen zur Genre-Klasse
			  rs3 = (dbCon.executeQuery("select genre from \"Event_Genre\" where event=" + String.valueOf(dataBaseEvents.getInt("event_id") )));
			  if (rs3.next()){
			  int genreID = rs3.getInt(1);
			  rs4 = (dbCon.executeQuery("select bezeichnung from \"Genre\" where genre_id=" + String.valueOf(genreID )));
			  rs4.next();
				  String genreKategorie = rs4.getString(1);
				  ontCon.addIndividualToClass(individ, genreKategorie);
			  }


			  ontCon.saveOntologie();
		  }	
		}
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	////////////////////////////////// Entfernungsmethoden /////////////////////////////////////////////////////////////
	
	/**
	 * Returns the Cities which occur in the DB
	 * @throws SQLException 
	 */
	public ArrayList<String> getCitiesFromDB () throws SQLException{
		ArrayList<String> cities = new ArrayList<String>();
		ResultSet rs = dbCon.executeQuery("Select Distinct ort from \"Event\"");
		while(rs.next()){
			cities.add(rs.getString("ort"));
		}
		return cities;
	}
	
	/**
	 * Creates a view in the DB with all reachable Events
	 * @param reachableCities all Cities that are reachable
	 * @throws SQLException 
	 */
	public void setDistanceView (ArrayList<String> reachableCities) throws SQLException{
		String sqlInStat = reachableCities.toString().replace("[","").replace("]","").trim();
		String sqlStatement =  " SELECT * FROM \"Event\" WHERE ort IN ("+sqlInStat+"(" ;
		dbCon.createView(REACHABLE_CITIES_VIEW_NAME, sqlStatement);
		reachCitiesViewIsSet = true;
		
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	//////////////////////////////////////////////////////////// Zeit-Methoden //////////////////////////////////////////
	
	public void setHolidayView (String startDate, String endDate) throws SQLException, ViewDoesntExistsException{
		if ( !reachCitiesViewIsSet )
			throw new ViewDoesntExistsException(REACHABLE_CITIES_VIEW_NAME+" hasnt been created yet");
		String sqlStatement ="";
		//TODO Implementieren der Zeitberecchnung und des passenden SQL statements
		dbCon.createView(HOLIDAY_VIEW_NAME, sqlStatement);
		holidayViewIsSet =true;
	}
		
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	
	
	public ResultSet executeQuery (String sqlStatement)throws SQLException{		
		return dbCon.executeQuery(sqlStatement);
	}
	
	public void removeAllIndividuals (){
		ontCon.removeAllIndividuals();
	}
	
	public ArrayList<Integer> getInvidualsFromOntologieClass (String className){
		return ontCon.getEventIdsByClass(className);
	}
  
	public ArrayList<Integer> getIndividualUnionOverClasses (ArrayList<String> classNames) {
		//TODO Implementieren : getIndividual Union OverClasses 
		return new  ArrayList<Integer>();
	}
	
	public ArrayList<Integer> getIndividualIntersectionOverClasses ( ArrayList<String> classNames) throws Exception {
		if (classNames.size()<2)
			throw new Exception("2 ore more Classes needed to make an intersection");
		ArrayList<Integer> list1 ;
		ArrayList<Integer> list2 ;
		ArrayList<Integer> list3 = this.getInvidualsFromOntologieClass(classNames.get(0));
		for ( int i = 1; i < classNames.size(); i++ ){
			list1 = list3;
			list3 = new ArrayList<Integer>();
			list2 = this.getInvidualsFromOntologieClass(classNames.get(i));
			for (Integer s : list1){
				if (list2.contains(s))
					list3.add(s);
			}
		}
		return list3;
	} 
	
	/**
	 *  Returns the direct Subclases of a Class
	 * @param className the Name of the superclass
	 * @return Non rekursive list of subclasses
	 */
	public ArrayList<String> getSubClassesOfClass(String className){
		return ontCon.getClassNamesOnly(ontCon.getSubClasses(className));
	}
	
	public ArrayList<String> getSuperClassesOfClass ( String className){
		//TODO Implementieren :get Super ClassesOfClass
		return new ArrayList<String> ();
	}
	
	public ResultSet getDataFromDbByEvent_Id ( ArrayList<Integer> eventIDs) throws SQLException{
		String s = new String(" ");
		int i ;
		for ( i=0  ; i < eventIDs.size()-1;i++){
			s = s.concat(String.valueOf(eventIDs.get(i))+"," );
		}
		s = s.concat(String.valueOf(eventIDs.get(i)));
		return  dbCon.executeQuery("Select * from \"Event\" where \"event_id\" in (" + s + ")");
	}
}
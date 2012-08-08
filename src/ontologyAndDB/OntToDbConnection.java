package ontologyAndDB;
import java.sql.*;
import java.util.ArrayList;

import ontologyAndDB.exception.OWLConnectionUnknownTypeException;
import ontologyAndDB.exception.OntologyConnectionDataPropertyException;
import ontologyAndDB.exception.OntologyConnectionIndividualAreadyExistsException;
import ontologyAndDB.exception.OntologyConnectionUnknowClassException;

import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.util.OWLEntityRemover;



public class OntToDbConnection {
	
	private 	DBConnection 		dbCon;
	private		OntologyConnection  ontCon;
	
	public OntToDbConnection(){
		 dbCon = new DBConnection();
		 ontCon = new OntologyConnection("evntologie_latest.owl");
	}
	
	public void fillOntWithIndividuals() throws SQLException, OntologyConnectionDataPropertyException, OWLConnectionUnknownTypeException, OntologyConnectionIndividualAreadyExistsException, OntologyConnectionUnknowClassException{	
	  ResultSet rs = dbCon.executeQuery("Select * from \"Event\"");
	  while(rs.next()){
		  OWLNamedIndividual individ = ontCon.createIndividual(String.valueOf(rs.getInt("event_id")));
		  ontCon.setObjectPropertieToIndividual(individ, "hasChildCare", rs.getBoolean("kinderbetreuung"));
		  ResultSet rs2 = dbCon.executeQuery("select bezeichnung from \"Kategorie\" where kategorie_id="+  rs.getInt("kategorie"));
		  rs2.next();
		  ontCon.addIndividualToClass(individ, rs2.getString(1));
		  ontCon.addIndividualToClass(individ, "ComedyGenre");
		  ontCon.saveOntologie();
	  }	
	}
	
	public void removeAllIndividuals (){
		ontCon.removeAllIndividuals();
	}
	
	public ArrayList<Integer> getInvidualsFromOntologieClass (String className){
		return ontCon.getEventIdsByClass(className);
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
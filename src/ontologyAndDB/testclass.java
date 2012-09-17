package ontologyAndDB;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;
import java.util.Calendar;

import ontologyAndDB.exception.OWLConnectionUnknownTypeException;
import ontologyAndDB.exception.OntologyConnectionDataPropertyException;
import ontologyAndDB.exception.OntologyConnectionIndividualAreadyExistsException;
import ontologyAndDB.exception.OntologyConnectionUnknowClassException;
import ontologyAndDB.exception.ViewDoesntExistsException;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.io.OWLOntologyCreationIOException;
import org.semanticweb.owlapi.io.OWLOntologyDocumentTarget;
import org.semanticweb.owlapi.io.OWLParser;
import org.semanticweb.owlapi.io.OWLParserException;
import org.semanticweb.owlapi.io.UnparsableOntologyException;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyFormat;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.UnloadableImportException;
import org.semanticweb.owlapi.reasoner.ConsoleProgressMonitor;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;


public class testclass {

	public static void main (String args[]) throws SQLException, OntologyConnectionDataPropertyException, OWLConnectionUnknownTypeException, OntologyConnectionIndividualAreadyExistsException, OntologyConnectionUnknowClassException, OWLOntologyCreationException, OWLOntologyStorageException{
	
		OntToDbConnection t = OntToDbConnection.getInstance();
		//t.openOntology("evntologie_latest.owl");
		Calendar cal =		Calendar.getInstance();
		System.out.println("Start : "+cal.getTime());
//		t.fillOntWithEventsUntilNumber(100);
		t.fillOntWithAllEvents();
//		t.removeAllIndividuals();
		//ResultSet rs = t.getDataFromDbByEvent_Id(t.getEventIdsFromOntologieClass("BalletEvent"));
		//while (rs.next()){
		//	System.out.println ( rs.getString(1));
		//}
		//"file://TestOntology.owl"
		System.out.println( t.getSubClassesOfClassByOntology("CinemaGenres"));
		//System.out.println (t.getSuperClassesOfClassByOntology("ComedyGenre"));
		//System.out.println (t.getEventIdsByClassByOntology("WeekendEvent"));
		//System.out.println (t.getInvidualsFromOntologieClass("Event")); 
		//System.out.println( t.getCitiesFromDB().toString());
		
		t.InfereceAndSaveOntology();
		//t.InfereceAndSaveOntology();
		
		
		//System.out.println (t.getInvidualsFromOntologieClass("Event")); 
		
		/*
		try{
		t.setHolidayView("1999-01-01","2020-01-01");
		}catch(ViewDoesntExistsException e)
		{
			e.printStackTrace();
		}
		ResultSet rs = t.executeQuery("Select * From HolidayView");
		rs.next() ;
		rs.next() ;
		System.out.println(rs.getString(1));
		*/
		
		/*
		ArrayList<Integer> list=new ArrayList<Integer>();
		list.add(2);
		list.add(3);
		list.add(5);
		ResultSet rs = t.getDataFromDbByEvent_Id(list);
		rs.next() ;
		System.out.println(rs.getString(1));
		*/
		
		//t.removeIndividualsFromClass("CinemaEvent");
		t.disconnectFromDB();
		cal =		Calendar.getInstance();
		System.out.println("Ende : "+cal.getTime());
	}
}

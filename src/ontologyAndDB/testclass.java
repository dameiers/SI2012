package ontologyAndDB;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;
import java.util.Calendar;

import ontologyAndDB.exception.OWLConnectionUnknownTypeException;
import ontologyAndDB.exception.OntologyConnectionDataPropertyException;
import ontologyAndDB.exception.OntologyConnectionIndividualAreadyExistsException;
import ontologyAndDB.exception.OntologyConnectionUnknowClassException;

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

	public static void main (String args[]) throws SQLException, OntologyConnectionDataPropertyException, OWLConnectionUnknownTypeException, OntologyConnectionIndividualAreadyExistsException, OntologyConnectionUnknowClassException{
	
		OntToDbConnection t = new OntToDbConnection();
		Calendar cal =		Calendar.getInstance();
		System.out.println("Start : "+cal.getTime());
		//t.fillOntWithAllEvents();
		//t.removeAllIndividuals();
		//ResultSet rs = t.getDataFromDbByEvent_Id(t.getEventIdsFromOntologieClass("BalletEvent"));
		//while (rs.next()){
		//	System.out.println ( rs.getString(1));
		//}
		
		//System.out.println (t.getSubClassesOfClass("Event"));
		//System.out.println (t.getInvidualsFromOntologieClass("Event")); 
		//System.out.println( t.getCitiesFromDB().toString());
		//t.preAndSave();
		System.out.println (t.getInvidualsFromOntologieClass("Event")); 
		t.disconnectFromDB();
		System.out.println("Ende : "+cal.getTime());
	}
}

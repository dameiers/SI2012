package ontologyAndDB;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.Set;

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
		//t.fillOntWithIndividuals();
		//t.removeAllIndividuals();
		ResultSet rs = t.getDataFromDbByEvent_Id(t.getInvidualsFromOntologieClass("BalletEvent"));
		while (rs.next()){
			System.out.println ( rs.getString(1));
		}
		
		/*
		try {
			
			OWLOntologyManager manager = OWLManager.createOWLOntologyManager();
			
			OWLDataFactory factory = manager.getOWLDataFactory();
			
            File file = new File("evntologie_new_prototype.owl");
            
            // Now load the local copy
            OWLOntology localEventOntology = manager.loadOntologyFromOntologyDocument(file);
            System.out.println("Loaded ontology: " + localEventOntology);

            // We can always obtain the location where an ontology was loaded from
            IRI documentIRI = manager.getOntologyDocumentIRI(localEventOntology);
            System.out.println("    from: " + documentIRI);
           
            OWLOntologyFormat format = manager.getOntologyFormat(localEventOntology);
            System.out.println(" format: " + format);
            
            
         
 
            for (OWLClass cls : localEventOntology.getClassesInSignature())
            	System.out.println( cls);
            
           
            IRI iri = IRI.create("file:/E:/eclipse_reasoner/workspace/prototype/evntologie_new_prototype#SourceTestKlasse");
            OWLClass clsAMethodA = factory.getOWLClass(iri);
           
            
            //ADD Class : SourceTestKlasse
            OWLDeclarationAxiom declarationAxiom = factory.getOWLDeclarationAxiom(clsAMethodA);
            manager.addAxiom(localEventOntology, declarationAxiom);
            
            OWLNamedIndividual testobject = factory.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/ontologies/2012/6/Ontology1342622372824#SourceTesObject"));
            
            OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(clsAMethodA,testobject);
            
            manager.addAxiom(localEventOntology, classAssertion);
            
          
            
            // 2ter versuch
            OWLClass cultureEventClass = factory.getOWLClass(IRI.create("file:/E:/eclipse_reasoner/workspace/prototype/evntologie_new_prototype#CultureEvent"));
      
            
       
      

           
            OWLNamedIndividual testobject2 = factory.getOWLNamedIndividual(IRI.create("http://www.semanticweb.org/ontologies/2012/6/Ontology1342622372824#SourceTesObject2"));
          

            OWLClassAssertionAxiom classAssertion2 = factory.getOWLClassAssertionAxiom(cultureEventClass,testobject2);
            
            manager.addAxiom(localEventOntology, classAssertion2);
            
           
            
            

            OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
            ConsoleProgressMonitor progressMonitor = new ConsoleProgressMonitor();
        	OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor);
        	OWLReasoner reasoner = reasonerFactory.createReasoner(localEventOntology, config);
        	reasoner.precomputeInferences();
        	
        	boolean consistent = reasoner.isConsistent();
        	System.out.println("Consistent: " + consistent);
        	System.out.println("\n");
            
            
            
            
         
            
            
           manager.saveOntology(localEventOntology);
       
       
		}
		 catch (OWLOntologyCreationIOException e) {
	            // IOExceptions during loading get wrapped in an OWLOntologyCreationIOException
	            IOException ioException = e.getCause();
	            if (ioException instanceof FileNotFoundException) {
	                System.out.println("Could not load ontology. File not found: " + ioException.getMessage());
	            }
	            else if (ioException instanceof UnknownHostException) {
	                System.out.println("Could not load ontology. Unknown host: " + ioException.getMessage());
	            }
	            else {
	                System.out.println("Could not load ontology: " + ioException.getClass().getSimpleName() + " " + ioException.getMessage());
	            }
	        }
			
	        catch (UnparsableOntologyException e) {
	            // If there was a problem loading an ontology because there are syntax errors in the document (file) that
	            // represents the ontology then an UnparsableOntologyException is thrown
	            System.out.println("Could not parse the ontology: " + e.getMessage());
	            // A map of errors can be obtained from the exception
	            Map<OWLParser, OWLParserException> exceptions = e.getExceptions();
	            // The map describes which parsers were tried and what the errors were
	            for (OWLParser parser : exceptions.keySet()) {
	                System.out.println("Tried to parse the ontology with the " + parser.getClass().getSimpleName() + " parser");
	                System.out.println("Failed because: " + exceptions.get(parser).getMessage());
	            }
	        }
	        catch (UnloadableImportException e) {
	            // If our ontology contains imports and one or more of the imports could not be loaded then an
	            // UnloadableImportException will be thrown (depending on the missing imports handling policy)
	            System.out.println("Could not load import: " + e.getImportsDeclaration());
	            // The reason for this is specified and an OWLOntologyCreationException
	            OWLOntologyCreationException cause = e.getOntologyCreationException();
	            System.out.println("Reason: " + cause.getMessage());
	        }
	        catch (OWLOntologyCreationException e) {
	            System.out.println("Could not load ontology: " + e.getMessage());
	       }  catch (OWLOntologyStorageException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	*/
	}
}

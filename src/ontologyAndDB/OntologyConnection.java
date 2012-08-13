package ontologyAndDB;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

import ontologyAndDB.exception.OWLConnectionUnknownTypeException;
import ontologyAndDB.exception.OntologyConnectionDataPropertyException;
import ontologyAndDB.exception.OntologyConnectionIndividualAreadyExistsException;
import ontologyAndDB.exception.OntologyConnectionUnknowClassException;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLEntity;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;
import org.semanticweb.owlapi.model.PrefixManager;
import org.semanticweb.owlapi.reasoner.ConsoleProgressMonitor;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerConfiguration;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.SimpleConfiguration;
import org.semanticweb.owlapi.util.DefaultPrefixManager;



public class OntologyConnection {

	private 	OWLOntologyManager 		manager;
	private 	OWLDataFactory 			factory;
	private  	File 					file;
	private		OWLOntology 			ontology;
	private 	IRI 					documentIRI;
	private		PrefixManager 			pm; 
	private 	OWLReasoner 			reasoner;
	private 	String					ontID;
	private 	Set<OWLDataProperty>	dataProperties;
	private 	Set<OWLNamedIndividual> individuals;
	private		Set<OWLClass>			classes;
	
	protected OntologyConnection(String filePath){
		try{			
			manager = OWLManager.createOWLOntologyManager();
			factory = manager.getOWLDataFactory();
			file = new File(filePath);
			ontology = manager.loadOntologyFromOntologyDocument(file);
            documentIRI = manager.getOntologyDocumentIRI(ontology);
            ontID = ontology.getOntologyID().toString();
            ontID = ontID.substring(1, ontID.length()-1);
            pm = new DefaultPrefixManager(ontID+"#");
            OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
            ConsoleProgressMonitor progressMonitor = new ConsoleProgressMonitor();
        	OWLReasonerConfiguration config = new SimpleConfiguration(progressMonitor);
        	reasoner = reasonerFactory.createReasoner(ontology, config);
        	individuals= ontology.getIndividualsInSignature();	
        	dataProperties = ontology.getDataPropertiesInSignature();
        	classes = ontology.getClassesInSignature();
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
	}
	
	
	protected void saveOntologie (){
		try {
			manager.saveOntology(ontology);
		} catch (OWLOntologyStorageException e) {
			e.printStackTrace();
		}
	}
	
	protected OWLNamedIndividual setObjectPropertieToIndividual(OWLNamedIndividual individual , String objectPropertieName ,String value)throws OntologyConnectionDataPropertyException,OWLConnectionUnknownTypeException{
		OWLDataProperty prop = getOWLDataProperty( ontID+"#"+objectPropertieName);
		if ( null == prop)
			throw new OntologyConnectionDataPropertyException(objectPropertieName + " : Doesnt exist");
		OWLDataPropertyAssertionAxiom assertion = factory.getOWLDataPropertyAssertionAxiom( prop, individual, value);				
		AddAxiom addAxiomChange = new AddAxiom(ontology, assertion);
		manager.applyChange(addAxiomChange);		
		return individual;	 
	}
	
	protected OWLNamedIndividual setObjectPropertieToIndividual(OWLNamedIndividual individual , String objectPropertieName ,boolean value)throws OntologyConnectionDataPropertyException,OWLConnectionUnknownTypeException{
		OWLDataProperty prop = getOWLDataProperty( ontID+"#"+objectPropertieName);
		if ( null == prop)
			throw new OntologyConnectionDataPropertyException(objectPropertieName + " : Doesnt exist");
		OWLDataPropertyAssertionAxiom assertion = factory.getOWLDataPropertyAssertionAxiom( prop, individual,value);				
		AddAxiom addAxiomChange = new AddAxiom(ontology, assertion);
		manager.applyChange(addAxiomChange);		
		return individual;	 
	}
	
	protected OWLNamedIndividual setObjectPropertieToIndividual(OWLNamedIndividual individual , String objectPropertieName ,Integer value)throws OntologyConnectionDataPropertyException,OWLConnectionUnknownTypeException{
		OWLDataProperty prop = getOWLDataProperty( ontID+"#"+objectPropertieName);
		if ( null == prop)
			throw new OntologyConnectionDataPropertyException(objectPropertieName + " : Doesnt exist");
		OWLDataPropertyAssertionAxiom assertion = factory.getOWLDataPropertyAssertionAxiom( prop, individual, value);			
		AddAxiom addAxiomChange = new AddAxiom(ontology, assertion);
		manager.applyChange(addAxiomChange);		
		return individual;	 
	}
	
	
	protected void addIndividualToClass (OWLNamedIndividual individual , String ClassName)throws OntologyConnectionUnknowClassException{
		OWLClass cl = getClass( ontID+"#"+ClassName);
		if (null == cl)
			throw new OntologyConnectionUnknowClassException("Unknown class : "+ClassName);
		OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(cl,individual);
        manager.addAxiom(ontology, classAssertion);
	}

	protected OWLNamedIndividual createIndividual (String name) throws OntologyConnectionIndividualAreadyExistsException{
		if ( null != getIndividual(  ontID+"#"+name ) ) 
			throw new OntologyConnectionIndividualAreadyExistsException(name + "  :  Already Exists");
		OWLNamedIndividual individual = factory.getOWLNamedIndividual(name,pm);
		individuals.add(individual);
		return individual;
	}
		
	protected void printSubClasses(String className){
		printNodeSet (getSubClasses(className));
	}
	
	protected NodeSet<OWLClass> getSubClasses (String className){
		OWLClass superClass = factory.getOWLClass(className ,pm);
		return  reasoner.getSubClasses(superClass, true);
	}
	
	private void printNodeSet(NodeSet<OWLClass> nodes) {
		Set<OWLClass> clses = nodes.getFlattened();
		System.out.println("Subclasses:");
		for(OWLClass cls : clses) {
			System.out.println(" " + cls.toString().replaceFirst(ontID+"#", "").replace("<", "").replace(">", ""));	}
		System.out.println("\n");
	}
	
	protected ArrayList<String> getClassNamesOnly(NodeSet<OWLClass> classList){
		Set<OWLClass> clses = classList.getFlattened();
		ArrayList<String> subClasses =new ArrayList<String> ();
		for(OWLClass cls : clses) {
			subClasses.add(cls.toString().replaceFirst(ontID+"#", "").replace("<", "").replace(">", ""));	
			}
		return subClasses;
	}
		
	private OWLDataProperty getOWLDataProperty (String dataPropIRI){	
		for ( OWLDataProperty prop : dataProperties ){
			if (prop.toString().replace("<", "").replace(">","").trim().equals(dataPropIRI))
				return prop;
		}
		return null;
	}
	
	private OWLNamedIndividual getIndividual (String individualIRI){
		for ( OWLNamedIndividual indi : individuals){
			if (indi.getIRI().toString().equals(individualIRI))
				return indi;
		}
		return null;
	}
	
	private OWLNamedIndividual getIndividual (OWLNamedIndividual individ){
		for ( OWLNamedIndividual indi : individuals){
			if (indi.equals(individ))
				return indi;
		}
		return null;
	}
	
	private OWLClass getClass (String classIRI){
		for ( OWLClass cl : classes){
			if (cl.getIRI().toString().equals(classIRI))
				return cl;
		}
		return null;
	}
	
protected void removeAllIndividuals (){
		 
		OWLEntityRemover remover = new OWLEntityRemover(manager, Collections.singleton(ontology));
		//System.out.println("Number of individuals: " + ontology.getIndividualsInSignature().size());

		 for(OWLNamedIndividual ind : ontology.getIndividualsInSignature()) {
			 ind.accept(remover);
			 }
		manager.applyChanges(remover.getChanges());
		//System.out.println("Number of individuals: " + ontology.getIndividualsInSignature().size());
		remover.reset();
		saveOntologie();
	}
	
	
		
	//TODO Testen ob auc unterklassen elemente ausgegeben werden?
	protected ArrayList<Integer> getEventIdsByClass(String className){
		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (OWLIndividual invid :  factory.getOWLClass(className, pm).getIndividuals(ontology)){
			ids.add(Integer.valueOf(invid.toStringID().replace(pm.getDefaultPrefix(),"")));
		}
		return ids;
	}
	
}

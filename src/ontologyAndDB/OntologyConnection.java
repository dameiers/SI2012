package ontologyAndDB;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

import ontologyAndDB.exception.OWLConnectionUnknownTypeException;
import ontologyAndDB.exception.OntologyConnectionDataPropertyException;
import ontologyAndDB.exception.OntologyConnectionIndividualAreadyExistsException;
import ontologyAndDB.exception.OntologyConnectionUnknowClassException;

import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.util.InferredAxiomGenerator;
import org.semanticweb.owlapi.util.InferredClassAssertionAxiomGenerator;
import org.semanticweb.owlapi.util.InferredOntologyGenerator;
import org.semanticweb.owlapi.util.InferredSubClassAxiomGenerator;
import org.semanticweb.owlapi.util.OWLEntityRemover;
import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLClassExpression;
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
import org.semanticweb.owlapi.reasoner.InferenceType;
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
	private		OWLReasonerFactory 		reasonerFactory;
	
	
	////////////////////////////////////////////////////Constructor//////////////////////////////////////////////////////////
	
	protected OntologyConnection(){
		manager = OWLManager.createOWLOntologyManager();
		factory = manager.getOWLDataFactory();
		reasonerFactory = new Reasoner.ReasonerFactory();
	}
	
	///////////////////////////////////////////////////Ontology-Methods//////////////////////////////////////////////////////////////	
	
	protected void saveOntologie (){
		try {
			manager.saveOntology(ontology);
		} catch (OWLOntologyStorageException e) {
			e.printStackTrace();
		}
	}
	
	protected void openOntology(String filePath) throws OWLOntologyCreationException{
		file = new File(filePath);
		ontology = manager.loadOntologyFromOntologyDocument(file);
        documentIRI = manager.getOntologyDocumentIRI(ontology);
        ontID = ontology.getOntologyID().toString();
        ontID = ontID.substring(1, ontID.length()-1);
        pm = new DefaultPrefixManager(ontID+"#");
        individuals= ontology.getIndividualsInSignature();	
		dataProperties = ontology.getDataPropertiesInSignature();
		classes = ontology.getClassesInSignature();
		reasoner = reasonerFactory.createReasoner(ontology);
	}
	
	protected void preAndSave(String owlFilePath) throws OWLOntologyCreationException, OWLOntologyStorageException{
		reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
		reasoner.precomputeInferences(InferenceType.CLASS_ASSERTIONS);
		reasoner.precomputeInferences(InferenceType.DIFFERENT_INDIVIDUALS);
		reasoner.precomputeInferences(InferenceType.SAME_INDIVIDUAL);
		reasoner.precomputeInferences(InferenceType.DATA_PROPERTY_ASSERTIONS);
		reasoner.precomputeInferences(InferenceType.DATA_PROPERTY_HIERARCHY);
		reasoner.precomputeInferences(InferenceType.DISJOINT_CLASSES);
		reasoner.precomputeInferences(InferenceType.OBJECT_PROPERTY_ASSERTIONS);
		reasoner.precomputeInferences(InferenceType.OBJECT_PROPERTY_HIERARCHY);
		
		List<InferredAxiomGenerator<? extends OWLAxiom>> gens = new ArrayList<InferredAxiomGenerator<? extends OWLAxiom>>();
		gens.add(new InferredSubClassAxiomGenerator());
		gens.add(new InferredClassAssertionAxiomGenerator());
		
		File ontfile = new File(owlFilePath);
		OWLOntology ont = manager.loadOntologyFromOntologyDocument(ontfile);
		
		InferredOntologyGenerator iog = new InferredOntologyGenerator(reasoner, gens);
		iog.fillOntology(manager, ont);
		manager.saveOntology(ont);
		
		System.out.println("Infered and Saved!");
	}
	
	protected void preAndSave() throws OWLOntologyCreationException, OWLOntologyStorageException{
		reasoner.precomputeInferences(InferenceType.CLASS_HIERARCHY);
		reasoner.precomputeInferences(InferenceType.CLASS_ASSERTIONS);
		reasoner.precomputeInferences(InferenceType.DIFFERENT_INDIVIDUALS);
		reasoner.precomputeInferences(InferenceType.SAME_INDIVIDUAL);
		reasoner.precomputeInferences(InferenceType.DATA_PROPERTY_ASSERTIONS);
		reasoner.precomputeInferences(InferenceType.DATA_PROPERTY_HIERARCHY);
		reasoner.precomputeInferences(InferenceType.DISJOINT_CLASSES);
		reasoner.precomputeInferences(InferenceType.OBJECT_PROPERTY_ASSERTIONS);
		reasoner.precomputeInferences(InferenceType.OBJECT_PROPERTY_HIERARCHY);
		
		List<InferredAxiomGenerator<? extends OWLAxiom>> gens = new ArrayList<InferredAxiomGenerator<? extends OWLAxiom>>();
		gens.add(new InferredSubClassAxiomGenerator());
		gens.add(new InferredClassAssertionAxiomGenerator());
   		
		InferredOntologyGenerator iog = new InferredOntologyGenerator(reasoner, gens);
		iog.fillOntology(manager, ontology);
		manager.saveOntology(ontology);
		
		System.out.println("Infered and Saved!");
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
	
	
	///////////////////////////////////////////////////Individual-Methods////////////////////////////////////////////////////////////
	
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
		
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * @param classList
	 * @return
	 */
	protected ArrayList<String> getClassNamesOnly(NodeSet<OWLClass> classList){
		if (classList.isEmpty())
			return null;
		Set<OWLClass> clses = classList.getFlattened();
		ArrayList<String> subClasses =new ArrayList<String> ();
		for(OWLClass cls : clses) {
			subClasses.add(cls.toString().replaceFirst(ontID+"#", "").replace("<", "").replace(">", ""));	
			}
		return subClasses;
	}
		
	protected ArrayList<String> getClassNamesOnly(Set<OWLClassExpression> classList){
		if (classList.isEmpty())
			return null;
		ArrayList<String> subClasses =new ArrayList<String> ();
		for(OWLClassExpression cls : classList) {
			subClasses.add(cls.toString().replaceFirst(ontID+"#", "").replace("<", "").replace(">", ""));	
			}
		return subClasses;
	}
	
	
	////////////////////////////////////////////Reasoner-Calls//////////////////////////////////////////////////////////////////
	/**
	 * 
	 * @param className
	 * @return the names of the SuperClasses or null if there are no superclasses
	 * @throws OntologyConnectionUnknowClassException
	 */
	protected ArrayList<String> getSuperClassesByReasoner (String className) throws OntologyConnectionUnknowClassException{
		OWLClass cl = getClassByName(className);
		if(null == cl)
				throw new OntologyConnectionUnknowClassException("Unknown : "+className);
		NodeSet<OWLClass> nodes = reasoner.getSuperClasses(cl, true);
		return this.getClassNamesOnly(nodes);
	}
	
	protected ArrayList<Integer> getEventIdsByClassByReasoner(String className){
		ArrayList<Integer> ids = new ArrayList<Integer>();
		NodeSet<OWLNamedIndividual> invids =reasoner.getInstances( getClassByName (className), false);
		Set<OWLNamedIndividual> flatinvids= invids.getFlattened();
		for (OWLIndividual invid : flatinvids ){
			ids.add(Integer.valueOf(invid.toStringID().replace(pm.getDefaultPrefix(),"")));
		}
		return ids;
	}
	
	protected NodeSet<OWLClass> getSubClassesByReasoner (String className) throws OntologyConnectionUnknowClassException{
		OWLClass superClass = getClassByName(className );
		if (superClass==null)
			throw new OntologyConnectionUnknowClassException("unknow :"+className);
		return  reasoner.getSubClasses(superClass, true);
	}
	
	////////////////////////////////////////////////////Ontology-Base-Queries///////////////////////////////////////////////////////
	
	/**
	 * 
	 * @param className
	 * @return the names of the SuperClasses or null if there are no superclasses
	 * @throws OntologyConnectionUnknowClassException
	 */
	protected ArrayList<String> getSuperClassesByClassFromOntology(String className) throws OntologyConnectionUnknowClassException{
		OWLClass cl = getClassByName(className);
		if(null == cl)
				throw new OntologyConnectionUnknowClassException("Unknown : "+className);
		Set<OWLClassExpression> nodes = cl.getSuperClasses(ontology);
		return this.getClassNamesOnly(nodes);
	}
	
	protected ArrayList<Integer> getEventIdsByClassByOntology(String className){
		ArrayList<Integer> ids = new ArrayList<Integer>();
		Set<OWLIndividual> invids = getClassByName(className).getIndividuals(ontology);
		for (OWLIndividual invid : invids ){
			ids.add(Integer.valueOf(invid.toStringID().replace(pm.getDefaultPrefix(),"")));
		}
		return ids;
	}
	
	protected ArrayList<String> getSubClassesByClassFromOntology (String className) throws OntologyConnectionUnknowClassException{
		className = SpellingMistakeCorrector.correct(className);
		OWLClass superClass = getClassByName(className );
		if (superClass==null)
			throw new OntologyConnectionUnknowClassException("unknow :"+className);
		return getClassNamesOnly(superClass.getSubClasses(ontology));
	}
	
	
	
	////////////////////////////////////////////////////Private Methods///////////////////////////////////////////////////////////
	
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
	
	/**
	 * 
	 * @param className
	 * @return OWLClass or null if the class doesnt exist
	 */
	private OWLClass getClassByName ( String className){
		return this.getClass(ontID+"#"+className);
	}
	
	
}

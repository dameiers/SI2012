package ontologyAndDB;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import ontologyAndDB.exception.OntologyConnectionUnknowClassException;

import org.semanticweb.HermiT.Reasoner;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyCreationException;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.InferenceType;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.util.InferredAxiomGenerator;
import org.semanticweb.owlapi.util.InferredClassAssertionAxiomGenerator;
import org.semanticweb.owlapi.util.InferredOntologyGenerator;
import org.semanticweb.owlapi.util.InferredSubClassAxiomGenerator;

public class VirtualOntologyConnection {

	private OWLOntologyManager manager;
	private InferredOntologyGenerator inferredOntology;
	private OWLOntology ontology;
	private OWLDataFactory factory;
	private String ontID;
	private Set<OWLClass> classes;
	private static VirtualOntologyConnection instance;

	private VirtualOntologyConnection() {
		manager = OWLManager.createOWLOntologyManager();
		factory = manager.getOWLDataFactory();
		File f = new File("evntologie_latest.owl");
		classes = ontology.getClassesInSignature();
		
		try {
			ontology = manager.loadOntologyFromOntologyDocument(f);
			
			ontID = ontology.getOntologyID().toString();
			ontID = ontID.substring(1, ontID.length() - 1);
			
			OWLReasonerFactory reasonerFactory = new Reasoner.ReasonerFactory();
			OWLReasoner reasoner = reasonerFactory.createReasoner(ontology);
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

			inferredOntology = new InferredOntologyGenerator(reasoner,
					gens);
			inferredOntology.fillOntology(manager, ontology);
			
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
	}

	public static VirtualOntologyConnection getInstance() {
		if (instance == null) {
			instance = new VirtualOntologyConnection();
		}
		
		return instance;
	}
	
	protected void addIndividualToClass(OWLNamedIndividual individual, String className) throws OntologyConnectionUnknowClassException{
		OWLClass cl = getClass(ontID + "#" + className);
		if (null == cl)
			throw new OntologyConnectionUnknowClassException("Unknown class : "
					+ className);
		OWLClassAssertionAxiom classAssertion = factory.getOWLClassAssertionAxiom(cl, individual);
		manager.addAxiom(ontology, classAssertion);
		
		updateInferredOntology();
	}

	private void updateInferredOntology() {
		inferredOntology.fillOntology(manager,ontology);
	}
	
	private OWLClass getClass(String classIRI) {
		for (OWLClass cl : classes) {
			if (cl.getIRI().toString().equals(classIRI))
				return cl;
		}
		return null;
	}
}

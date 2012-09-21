package model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import model.steps.*;


public class Model extends Observable 
{	
	private static Model instance;
	private TraceBuilder traceBuilder;
	
	public static Model getInstance() 
	{
		if(instance == null) {
			instance = new Model();
		}
		return instance;
	}
	
	private Model() 
	{
		traceBuilder = new TraceBuilder();
	}
	
	/**
	 * Returns all events considering the data which is already asked
	 */
	public HashMap<String, String>[] getEvents()
	{	
		return (EventCollector.getInstance()).getEvents();
	}
	
	/**
	 * Returns the current question order belonging to the data already asked
	 */
	public InformationGatherStepModel[] getInformationGatherTrace()
	{
		return traceBuilder.build();
	}
	
	/**
	 * Returns the step fallowing after previousModel
	 */
	public InformationGatherStepModel getNextStep(InformationGatherStepModel previousModel)
	{
		InformationGatherStepModel[] steps = getInformationGatherTrace();

		for(int i=0; i<steps.length-1; i++) {
			if(steps[i].equals(previousModel)) {
				return steps[i+1];
			}
		}
		
		return null;
	}
	
	/**
	 * Returns the next Step which needs to be asked in the current question order
	 */
	public InformationGatherStepModel getNextStep()
	{
		InformationGatherStepModel[] steps = getInformationGatherTrace();
		InformationGatherStepModel result = null;
		for(int i=0; i<steps.length; i++) {
			if(steps[i].isAlredyFilled() == false) {
				result = steps[i];
				break;
			}
		}
		return result;
	}
	
	public void setVoyageMethodStepRequired(boolean voyaageMethodStepRequired)
	{
		traceBuilder.setVoyageMethodStepRequired(voyaageMethodStepRequired);
	}
	
	public void setSchoolLocationStepRequired(boolean schoolLocationStepRequired)
	{
		traceBuilder.setSchoolLocationStepRequired(schoolLocationStepRequired);
	}

}

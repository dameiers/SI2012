package model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import model.steps.*;


public class Model extends Observable 
{	
	public InformationGatherStepModel[] getInformationGatherTrace()
	{
		return (new TraceBuilder()).build();
	}
	
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
	
	public HashMap<String, String>[] getEvents()
	{	
		return null;
	}
}

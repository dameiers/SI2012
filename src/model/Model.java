package model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import model.steps.*;


public class Model extends Observable 
{
	private static PersonDescriptionStep	personDescriptionStep = PersonDescriptionStep.getInstance();
	private static TimeRangeStep 			timeRangeStep = TimeRangeStep.getInstance();
	private static DurationStep 			durationStep = DurationStep.getInstance();
	private static SchoolLocationStep		schoolLocationStep = SchoolLocationStep.getInstance();
	private static PersonAgeStep 			personAgeStep = PersonAgeStep.getInstance();
	private static VoyageMethodStep			voyageMethodStep = VoyageMethodStep.getInstance();
	private static DistanceStep				distanceStep = DistanceStep.getInstance();
	
	public InformationGatherStep[] getInformationGatherTrace()
	{
		List<InformationGatherStep> result = new ArrayList<InformationGatherStep>();
		
		result.add(personDescriptionStep);
		result.add(timeRangeStep);
		
		if("summerbreak".equals(timeRangeStep.getTimeRangeTyp())) { 
			result.add(schoolLocationStep);
		}
		
		result.add(durationStep);
		
		if(!"single".equals(personDescriptionStep.getGroup())){
			result.add(personAgeStep);
		} 	
		
		if(personDescriptionStep.getAge() >= 18) {
			result.add(voyageMethodStep);
		}
		
		result.add(distanceStep);
		
		
		return (InformationGatherStep[])result.toArray();
	}
	
	public InformationGatherStep getNextStep()
	{
		InformationGatherStep[] steps = getInformationGatherTrace();
		InformationGatherStep result = null;
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

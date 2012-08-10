package model;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Observable;

import model.steps.*;


public class Model extends Observable 
{
	private static PersonDescriptionStepModel	personDescriptionStep = PersonDescriptionStepModel.getInstance();
	private static TimeRangeStepModel 			timeRangeStep = TimeRangeStepModel.getInstance();
	private static DurationStepModel 			durationStep = DurationStepModel.getInstance();
	private static SchoolLocationStepModel		schoolLocationStep = SchoolLocationStepModel.getInstance();
	private static PersonAgeStepModel 			personAgeStep = PersonAgeStepModel.getInstance();
	private static VoyageMethodStepModel		voyageMethodStep = VoyageMethodStepModel.getInstance();
	private static DistanceStepModel			distanceStep = DistanceStepModel.getInstance();
		
	public InformationGatherStepModel[] getInformationGatherTrace()
	{
		List<InformationGatherStepModel> result = new ArrayList<InformationGatherStepModel>();
		
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
		
		return (InformationGatherStepModel[])result.toArray();
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

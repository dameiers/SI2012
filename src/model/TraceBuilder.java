package model;

import java.util.ArrayList;
import java.util.List;

import model.steps.*;

public class TraceBuilder 
{
	private static PersonDescriptionStepModel	personDescriptionStep = PersonDescriptionStepModel.getInstance();
	private static TimeRangeStepModel 			timeRangeStep = TimeRangeStepModel.getInstance();
	private static DurationStepModel 			durationStep = DurationStepModel.getInstance();	
	private static SchoolLocationStepModel		schoolLocationStep = SchoolLocationStepModel.getInstance();
	private static PersonAgeStepModel 			personAgeStep = PersonAgeStepModel.getInstance();
	private static VoyageMethodStepModel		voyageMethodStep = VoyageMethodStepModel.getInstance();
	private static DistanceStepModel			distanceStep = DistanceStepModel.getInstance();
	
	public InformationGatherStepModel[] build()
	{
		if(!personDescriptionStep.isAlredyFilled())
			return initianalTrace();
		
		return dynamicTrace();
	}
	
	private InformationGatherStepModel[] dynamicTrace()
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
	
	private InformationGatherStepModel[] initianalTrace()
	{
		return null;
	}

}

package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import ontologyAndDB.OntToDbConnection;

import model.steps.*;

/**
 * Helper Class used in the model class
 */
class TraceBuilder
{
	private static PersonDescriptionStepModel	personDescriptionStep = PersonDescriptionStepModel.getInstance();
	private static TimeRangeStepModel 			timeRangeStep = TimeRangeStepModel.getInstance();
	private static DurationStepModel 			durationStep = DurationStepModel.getInstance();	
	private static SchoolLocationStepModel		schoolLocationStep = SchoolLocationStepModel.getInstance();
	private static PersonAgeStepModel 			personAgeStep = PersonAgeStepModel.getInstance();
	private static VoyageMethodStepModel		voyageMethodStep = VoyageMethodStepModel.getInstance();
	private static DistanceStepModel			distanceStep = DistanceStepModel.getInstance();
	private static OriginLocationStepModel		originLocationStep = OriginLocationStepModel.getInstance();
	private static KindOfEventSelectionStepModel kindOfEventSelectionStepModel = KindOfEventSelectionStepModel.getInstance();
	private static EventCategoryStepModel		eventCategoryStepModel = EventCategoryStepModel.getInstance();
	private static GenreSelectionStepModel 		genreSelectionStepModel = GenreSelectionStepModel.getInstance();
	private static BudgetStepModel				budgetStepModel = BudgetStepModel.getInstance();
	private static TableviewModel				tableviewModel = TableviewModel.getInstance();
	
	private boolean voyaageMethodStepRequired = false;
	private boolean schoolLocationStepRequired = false;
	
	public InformationGatherStepModel[] build()
	{	
		if(!personDescriptionStep.isAlredyFilled())
			return initianalTrace();
		
		return dynamicTrace();
	}
	
	private InformationGatherStepModel[] initianalTrace()
	{
		List<InformationGatherStepModel> result = new ArrayList<InformationGatherStepModel>();
		
		result.add(personDescriptionStep);
		result.add(timeRangeStep);
		result.add(durationStep);
		result.add(voyageMethodStep);
		result.add(distanceStep);
		result.add(originLocationStep);
		result.add(kindOfEventSelectionStepModel);
		result.add(eventCategoryStepModel);
		result.add(genreSelectionStepModel);
		result.add(budgetStepModel);
		
		InformationGatherStepModel[] newResult =  new InformationGatherStepModel[result.size()];
		
		for(int i=0; i<result.size(); i++) {
			newResult[i] = result.get(i);
		}
		
		return newResult;
	}
	
	private InformationGatherStepModel[] dynamicTrace()
	{
		List<InformationGatherStepModel> result = new ArrayList<InformationGatherStepModel>();
		
		result.add(personDescriptionStep);
		result.add(timeRangeStep);
		
		if(isPersonAgesStepRequired()) {
			result.add(personAgeStep);
		} 
		
		if(isSchoolLocationStepRequired()) {
			result.add(schoolLocationStep);
		}
		
		result.add(durationStep);
		
		if(isVoyageMethodStepRequired()) {
			result.add(voyageMethodStep);
		}
		
		result.add(distanceStep);
		result.add(originLocationStep);
		result.add(kindOfEventSelectionStepModel);
		result.add(eventCategoryStepModel);
		
		if(isGenreStepRequired()) {
			result.add(genreSelectionStepModel);
		}
		
		result.add(budgetStepModel);
		result.add(tableviewModel);
		
		InformationGatherStepModel[] newResult =  new InformationGatherStepModel[result.size()];
		
		for(int i=0; i<result.size(); i++) {
			newResult[i] = result.get(i);
		}
		
		return newResult;
	}
	
	private boolean isSchoolLocationStepRequired() {
		return schoolLocationStepRequired;
	}
	
	private boolean isPersonAgesStepRequired() {
		return personDescriptionStep.isAlredyFilled() && !"Einzeln".equals(personDescriptionStep.getGroup());
	}
	
	private boolean isVoyageMethodStepRequired() {
		return voyaageMethodStepRequired;
	}
	
	private boolean isGenreStepRequired() {
		//TODO: Ask the ontologie
		return eventCategoryStepModel.hasCategoryBelongingToSomeGenres();
	}
	
	public void setSchoolLocationStepRequired(boolean schoolLocationStepRequired) {
		this.schoolLocationStepRequired = schoolLocationStepRequired;
	}
	
	public void setVoyageMethodStepRequired(boolean voyaageMethodStepRequired) {
		this.voyaageMethodStepRequired = voyaageMethodStepRequired;
	}
	
}

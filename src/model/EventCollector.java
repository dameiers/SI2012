package model;

import java.util.HashMap;

import model.steps.BudgetStepModel;
import model.steps.DistanceStepModel;
import model.steps.DurationStepModel;
import model.steps.EventCategoryStepModel;
import model.steps.GenreSelectionStepModel;
import model.steps.KindOfEventSelectionStepModel;
import model.steps.OriginLocationStepModel;
import model.steps.PersonAgeStepModel;
import model.steps.PersonDescriptionStepModel;
import model.steps.SchoolLocationStepModel;
import model.steps.TimeRangeStepModel;
import model.steps.VoyageMethodStepModel;

/**
 * Helper Class used in the model class
 */
class EventCollector 
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
	
	/**
	 * looks up the asked data from all step models and returns a list of events
	 */
	public HashMap<String, String>[] getEvents()
	{
		return getDummyData();
	}
	
	public HashMap<String, String>[] getDummyData()
	{
		HashMap<String, String>[] result = new HashMap[2];
		
		result[0] = new HashMap<String, String>();
		result[0].put("name", "Das Konzert Event");
		result[0].put("location", "Saarbrücken");
		
		result[1] = new HashMap<String, String>();
		result[1].put("name", "Das Kino Event");
		result[1].put("location", "Neunkirchen");
		
		return result;
	}

}

package test;

import java.util.HashMap;

import model.EventCollector;
import model.Model;
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

public class EventCollectorTest {

	public static void main(String[] args) {

		PersonDescriptionStepModel personDescriptionStep = PersonDescriptionStepModel.getInstance();
		TimeRangeStepModel timeRangeStep = TimeRangeStepModel.getInstance();
		DurationStepModel durationStep = DurationStepModel.getInstance();
		PersonAgeStepModel personAgeStep = PersonAgeStepModel.getInstance();
		DistanceStepModel distanceStep = DistanceStepModel.getInstance();
		KindOfEventSelectionStepModel kindOfEventSelectionStepModel = KindOfEventSelectionStepModel.getInstance();
		EventCategoryStepModel eventCategoryStepModel = EventCategoryStepModel.getInstance();
		GenreSelectionStepModel genreSelectionStepModel = GenreSelectionStepModel.getInstance();
		BudgetStepModel budgetStepModel = BudgetStepModel.getInstance();

		
		personDescriptionStep.setAge("YoungAdults");
		personDescriptionStep.setGroup("Einzeln");
		
		timeRangeStep.setTimeRangeTyp("autumnbreak");
		
		durationStep.setDuration("OneDayEvent");
		distanceStep.setDistance("5000");
		
		kindOfEventSelectionStepModel.setCultureStatus("like");
		kindOfEventSelectionStepModel.setLeisureTimeStatus("like");
		kindOfEventSelectionStepModel.setSportStatus("like");
		
		eventCategoryStepModel.setPreselection();
		genreSelectionStepModel.setPreselection();
		
		System.out.println(eventCategoryStepModel.getCultureCategories());
		
		System.out.println(genreSelectionStepModel.getConcertGenres());
		System.out.println(genreSelectionStepModel.getTheatreGenres());
		System.out.println(genreSelectionStepModel.getCinemaGenres());
		
		budgetStepModel.setBudget("5000");
		
		
		
		EventCollector collector = EventCollector.getInstance();
		collector.setEventIDs();
		
		HashMap<String, String>[] events = collector.getEvents();
		
		System.out.println();
		System.out.println();
		System.out.println(events == null ? 0 : events.length + " Events gefunden");
		System.out.println("---------------------");
		
		if(events != null) 
		{
			for(HashMap<String, String> event : events) {
				System.out.println(event);
				System.out.println("---------------------");
			}
		}	
		
		
		
		System.out.println("test");
		
	}
}

package model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import ontologyAndDB.OntToDbConnection;
import ontologyAndDB.exception.OWLConnectionUnknownTypeException;
import ontologyAndDB.exception.OntologyConnectionDataPropertyException;
import ontologyAndDB.exception.OntologyConnectionIndividualAreadyExistsException;
import ontologyAndDB.exception.OntologyConnectionUnknowClassException;
import ontologyAndDB.exception.ViewDoesntExistsException;

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
	
	private ArrayList<Integer> eventIDs;
	/**
	 * looks up the asked data from all step models and returns a list of events
	 */
	public HashMap<String, String>[] getEvents()
	{
		return getDatabaseData(eventIDs);
	}
	
	public HashMap<String, String>[] getDatabaseData(ArrayList<Integer> eventIDs)
	{
				
		try {
			ResultSet rs = OntToDbConnection.getInstance().getDataFromDbByEvent_Id(eventIDs);
			int columns = rs.getMetaData().getColumnCount();
			
		    
			LinkedList<HashMap<String, String>> list = new LinkedList<HashMap<String, String>>();
			
			while(rs.next()){
				
				HashMap<String, String> tmprow = new HashMap<String, String>();
				
				for(int i=0; i<columns; i++){
					String key = rs.getMetaData().getColumnLabel(i+1);
					tmprow.put(key, rs.getString(key));
				}
				
				list.addLast(tmprow);
			}
		
			return list.toArray(new HashMap[0]);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void setEventIDs (){
		ArrayList<Integer> tmpEventIDs = new ArrayList<Integer>();
		
		OntToDbConnection con = OntToDbConnection.getInstance();
		
		//----------------------Holiday-View-Setzen----------------------------
		try {
			con.setHolidayView(timeRangeStep.getFromDate(), timeRangeStep.getToDate());
			con.fillOntWithEventsFromHolidayView();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ViewDoesntExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OntologyConnectionDataPropertyException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OWLConnectionUnknownTypeException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OntologyConnectionIndividualAreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (OntologyConnectionUnknowClassException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//---------------------------------------------------------------------
		
		
		//----------------------Duration-Ontologie-----------------------------
		if(durationStep.getDuration().equals("weekend")){
			
		} else if(durationStep.getDuration().equals("oneDay")){
		
		}
		//---------------------------------------------------------------------
		
		
		
		
		this.eventIDs = tmpEventIDs;
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

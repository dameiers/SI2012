package model;

import gui.components.LikeBox;
import gui.steps.DistanceStep;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

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
class EventCollector {
	private static PersonDescriptionStepModel personDescriptionStep = PersonDescriptionStepModel
			.getInstance();
	private static TimeRangeStepModel timeRangeStep = TimeRangeStepModel
			.getInstance();
	private static DurationStepModel durationStep = DurationStepModel
			.getInstance();
	private static SchoolLocationStepModel schoolLocationStep = SchoolLocationStepModel
			.getInstance();
	private static PersonAgeStepModel personAgeStep = PersonAgeStepModel
			.getInstance();
	private static VoyageMethodStepModel voyageMethodStep = VoyageMethodStepModel
			.getInstance();
	private static DistanceStepModel distanceStep = DistanceStepModel
			.getInstance();
	private static OriginLocationStepModel originLocationStep = OriginLocationStepModel
			.getInstance();
	private static KindOfEventSelectionStepModel kindOfEventSelectionStepModel = KindOfEventSelectionStepModel
			.getInstance();
	private static EventCategoryStepModel eventCategoryStepModel = EventCategoryStepModel
			.getInstance();
	private static GenreSelectionStepModel genreSelectionStepModel = GenreSelectionStepModel
			.getInstance();
	private static BudgetStepModel budgetStepModel = BudgetStepModel
			.getInstance();
	private OntToDbConnection ontToDbConnection;
	private ArrayList<Integer> eventIDs = new ArrayList<Integer>();

	public EventCollector() {
		try {
			ontToDbConnection = OntToDbConnection.getInstance();
		} catch (OWLOntologyCreationException e) {
			e.printStackTrace();
		}
	}

	/**
	 * looks up the asked data from all step models and returns a list of events
	 */
	public HashMap<String, String>[] getEvents() {
		return getDatabaseData(eventIDs);
	}

	public HashMap<String, String>[] getDatabaseData(ArrayList<Integer> eventIDs) {
		if (eventIDs != null && !eventIDs.isEmpty()) {
			try {
				final ResultSet rs = ontToDbConnection
						.getDataFromDbByEvent_Id(eventIDs);
				ArrayList<HashMap<String, String>> list = null;
				if (rs != null) {

					final int columns = rs.getMetaData().getColumnCount();
					list = new ArrayList<HashMap<String, String>>();

					while (rs.next()) {

						final HashMap<String, String> tmprow = new HashMap<String, String>();

						for (int i = 0; i < columns; i++) {
							final String key = rs.getMetaData().getColumnLabel(
									i + 1);
							tmprow.put(key, rs.getString(key));
						}

						list.add(tmprow);
					}
				}
				return list.toArray(new HashMap[0]);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}

	public void setEventIDs() {
		ArrayList<Integer> tmpEventIDs = new ArrayList<Integer>();

		// Holiday-View-Setzen
		try {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			ontToDbConnection.setHolidayView(
					sdf.format(timeRangeStep.getFromDate().getTime()),
					sdf.format(timeRangeStep.getToDate().getTime()));
			ontToDbConnection.fillOntWithEventsFromHolidayView();

			// Distance view setzen...
			// TODO Bo muss komponente bauen...
			// DistanceCalculator.getReachableCities(double km);
			// DistanceCalculator.getReachableCities(double TimeInMillis);
			final String distanceUnit = distanceStep.getUnit();

			if (distanceUnit.equals(DistanceStepModel.DISTANCE_UNTI)) {

			} else {
				// TODO umrechnung zeit / km Verhältnis....
				// ontToDbConnection.setDistanceView(reachableCities);
			}

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

		// Duration-Ontologie
		// TODO aufpassen dass das was aus dem gui model kommt auch mit dem
		// Ontologie Klassennamen übereinstimmt...
		final Set<Integer> durationSet = new HashSet<Integer>(
				ontToDbConnection
						.getInvidualsFromOntologieClassByReasoner(durationStep
								.getDuration()));

		// Ontologie Class Restrictions for Culture Events in der form
		Set<Integer> cultureEvents = null;
		if (!kindOfEventSelectionStepModel.getCultureStatus().equals(
				KindOfEventSelectionStepModel.DONTLIKE)) {
			cultureEvents = calculateCultureEvents();
		}

		// falls, Leisure gewählt wurde, fuege den Klassennamen zur Liste der
		// Einschränkungen hinzu
		Set<Integer> leisureTimeEvents = null;
		if (!kindOfEventSelectionStepModel.getLeisureTimeStatus().equals(
				KindOfEventSelectionStepModel.DONTLIKE)) {
			leisureTimeEvents = calculateLeisureTimeEvents();
		}

		// falls, Sport gewählt wurde, fuege den Klassennamen zur Liste der
		// Einschränkungen hinzu
		Set<Integer> sportEvents = null;
		if (!kindOfEventSelectionStepModel.getSportStatus().equals(
				KindOfEventSelectionStepModel.DONTLIKE)) {
			sportEvents = calculateSportEvents();
		}

		// duration && (sport || culture || leisuretime)

		final HashSet<Integer> tmp = new HashSet<Integer>();
		if (cultureEvents != null && !cultureEvents.isEmpty()) {
			tmp.addAll(cultureEvents);
		}
		if (sportEvents != null && !sportEvents.isEmpty()) {
			tmp.addAll(sportEvents);
		}
		if (leisureTimeEvents != null && !leisureTimeEvents.isEmpty()) {
			tmp.addAll(leisureTimeEvents);
		}

		durationSet.retainAll(tmp);

		this.eventIDs = new ArrayList<Integer>(durationSet);
	}

	private Set<Integer> calculateLeisureTimeEvents() {
		// leisureTime && (festivity || ...|| ...)
				final Set<Integer> leisureTimeEvents = new HashSet<Integer>(
						ontToDbConnection
								.getInvidualsFromOntologieClassByReasoner("LeisureTimeEvents"));

				final HashMap<String, String> leisureTimeEventCategories = eventCategoryStepModel
						.getLeisureTimeCategories();
				final Iterator<String> it = leisureTimeEventCategories.keySet().iterator();
				final ArrayList<String> leisureTimeCategoryClassNames = new ArrayList<String>();
				while (it.hasNext()) {
					final String leisureTimeCategory = it.next();
					final String status = leisureTimeEventCategories.get(leisureTimeCategory);
					if (!status.equals(LikeBox.DONTLIKE)) {
						leisureTimeCategoryClassNames.add(leisureTimeCategory);
					}
				}
				final Set<Integer> sportCategoryEvents = new HashSet<Integer>(
						ontToDbConnection
								.getIndividualUnionOverClassesByReasoner(leisureTimeCategoryClassNames));
				leisureTimeEvents.retainAll(sportCategoryEvents);

				return leisureTimeEvents;
	}

	private Set<Integer> calculateSportEvents() {
		// sport && (running || motorsport|| ...)
		final Set<Integer> sportEvents = new HashSet<Integer>(
				ontToDbConnection
						.getInvidualsFromOntologieClassByReasoner("SportEvents"));

		final HashMap<String, String> sportEventCategories = eventCategoryStepModel
				.getSportCategories();
		final Iterator<String> it = sportEventCategories.keySet().iterator();
		final ArrayList<String> sportCategoryClassNames = new ArrayList<String>();
		while (it.hasNext()) {
			final String sportCategory = it.next();
			final String status = sportEventCategories.get(sportCategory);
			if (!status.equals(LikeBox.DONTLIKE)) {
				sportCategoryClassNames.add(sportCategory);
			}
		}
		final Set<Integer> sportCategoryEvents = new HashSet<Integer>(
				ontToDbConnection
						.getIndividualUnionOverClassesByReasoner(sportCategoryClassNames));
		sportEvents.retainAll(sportCategoryEvents);

		return sportEvents;
	}

	public HashMap<String, String>[] getDummyData() {
		HashMap<String, String>[] result = new HashMap[2];

		result[0] = new HashMap<String, String>();
		result[0].put("name", "Das Konzert Event");
		result[0].put("location", "SaarbrŸcken");

		result[1] = new HashMap<String, String>();
		result[1].put("name", "Das Kino Event");
		result[1].put("location", "Neunkirchen");

		return result;
	}
	
	private Set<Integer> calculateCultureEvents() {
		// culture && ((cinema && (genre1 || genre2)) || concert&&(genre ||
		// genre ...) || theatre&&(genre || genre ...))

		final Set<Integer> cultureEventsSet = new HashSet<Integer>(
				ontToDbConnection
						.getInvidualsFromOntologieClassByReasoner("CultureEvents"));

		final HashMap<String, String> culturEventCategories = eventCategoryStepModel
				.getCultureCategories();

		final Iterator<String> it = culturEventCategories.keySet().iterator();
		Set<Integer> cinemaEvents = new HashSet<Integer>();
		Set<Integer> concertEvents = new HashSet<Integer>();
		Set<Integer> theatreEvents = new HashSet<Integer>();

		while (it.hasNext()) {
			final String currentCategory = it.next();
			final String status = culturEventCategories.get(currentCategory);
			if (!status.equals(LikeBox.DONTLIKE)) {
				if (currentCategory.equals("CinemaEvent")) {
					cinemaEvents = new HashSet<Integer>(
							ontToDbConnection
									.getInvidualsFromOntologieClassByReasoner("CinemaEvent"));
					// here we now that cinema events are marked as "LIKE"
					// so we have to care about genres
					final ArrayList<String> cinemaGenreClassNames = new ArrayList<String>();
					final Set<Integer> cinemaGenreEvents;
					// iterate through the genres that are selected...
					final HashMap<String, String> cinemaGenresLikeStatusMap = genreSelectionStepModel
							.getCinemaGenres();
					final Iterator<String> cinemaGenreKeys = cinemaGenresLikeStatusMap
							.keySet().iterator();
					while (it.hasNext()) {
						final String cinemaGenre = it.next();
						final String likeStatus = cinemaGenresLikeStatusMap
								.get(cinemaGenre);
						if (!likeStatus.equals(LikeBox.DONTLIKE)) {
							cinemaGenreClassNames.add(cinemaGenre);
						}
					}
					cinemaGenreEvents = new HashSet<Integer>(
							ontToDbConnection
									.getIndividualUnionOverClassesByReasoner(cinemaGenreClassNames));
					// cinema && (genre 1 || genre 2...)
					cinemaEvents.retainAll(cinemaGenreEvents);

				}
				if (currentCategory.equals("ConcertEvent")) {
					// here we now that concert events are marked as "LIKE"
					// so we have to care about genres
					concertEvents = new HashSet<Integer>(
							ontToDbConnection
									.getInvidualsFromOntologieClassByReasoner("ConcertEvent"));
					final ArrayList<String> concertGenreClassNames = new ArrayList<String>();
					final Set<Integer> concertGenreEvents;
					// iterate through the genres that are selected...
					final HashMap<String, String> concertGenresLikeStatusMap = genreSelectionStepModel
							.getConcertGenres();
					final Iterator<String> concertGenreKeys = concertGenresLikeStatusMap
							.keySet().iterator();
					while (it.hasNext()) {
						final String concertGenre = it.next();
						final String likeStatus = concertGenresLikeStatusMap
								.get(concertGenre);
						if (!likeStatus.equals(LikeBox.DONTLIKE)) {
							concertGenreClassNames.add(concertGenre);
						}
					}
					concertGenreEvents = new HashSet<Integer>(
							ontToDbConnection
									.getIndividualUnionOverClassesByReasoner(concertGenreClassNames));
					// concert && (genre 1 || genre 2...)
					concertEvents.retainAll(concertGenreEvents);
				}
				if (currentCategory.equals("TheatreEvent")) {
					// here we now that theatre events are marked as "LIKE"
					// so we have to care about genres
					theatreEvents = new HashSet<Integer>(
							ontToDbConnection
									.getInvidualsFromOntologieClassByReasoner("TheatreEvent"));
					final ArrayList<String> theatreGenreClassNames = new ArrayList<String>();
					final Set<Integer> theatreGenreEvents;
					// iterate through the genres that are selected...
					final HashMap<String, String> theatreGenresLikeStatusMap = genreSelectionStepModel
							.getTheatreGenres();
					final Iterator<String> theatreGenreKeys = theatreGenresLikeStatusMap
							.keySet().iterator();
					while (it.hasNext()) {
						final String theatreGenre = it.next();
						final String likeStatus = theatreGenresLikeStatusMap
								.get(theatreGenre);
						if (!likeStatus.equals(LikeBox.DONTLIKE)) {
							theatreGenreClassNames.add(theatreGenre);
						}
					}
					theatreGenreEvents = new HashSet<Integer>(
							ontToDbConnection
									.getIndividualUnionOverClassesByReasoner(theatreGenreClassNames));
					// theatre && (genre 1 || genre 2...)
					theatreEvents.retainAll(theatreGenreEvents);
				}
			}
		}

		// cinema &&(...) || concert && (...) || theatre&&(...)
		final Set<Integer> tmp = new HashSet<Integer>(cinemaEvents);
		tmp.addAll(concertEvents);
		tmp.addAll(theatreEvents);

		// culture && (...)
		cultureEventsSet.retainAll(tmp);
		return cultureEventsSet;
	}

	/**
	 * temporary method that fills the local variable eventIds with some ids..
	 * only for testing reasons
	 */
	public void setDummyEventIds() {
		final LinkedList<Integer> ids = new LinkedList<Integer>();
		for (int i = 0; i < 99; i++) {
			ids.add(i + 1);
		}

		eventIDs = new ArrayList<Integer>(ids);
	}

	/**
	 * temporary method to print the local collected event ids to sout only for
	 * testing reasons
	 */
	private void printEventList(HashMap<String, String>[] mapArr) {
		for (int i = 0; i < mapArr.length; i++) {
			final HashMap<String, String> tmpMap = mapArr[i];
			final Iterator<String> it = tmpMap.keySet().iterator();
			while (it.hasNext()) {
				final String key = it.next();
				final String value = tmpMap.get(key);
				System.out.println(value + "\t");
			}
			System.out.println("\n");
		}
	}


	public static void main(String[] args) {
		EventCollector collector = new EventCollector();
		collector.setDummyEventIds();

		final HashMap<String, String>[] foo = collector.getEvents();
		collector.printEventList(foo);
	}

}

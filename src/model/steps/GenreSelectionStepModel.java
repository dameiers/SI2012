package model.steps;

import gui.components.LikeBox;
import gui.steps.GenreSelectionStep;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import ontologyAndDB.OntToDbConnection;

public class GenreSelectionStepModel extends InformationGatherStepModel 
{
	private static GenreSelectionStepModel instance;
	
	private HashMap<String, String> cinemaGenres;
	private HashMap<String, String> concertGenres;
	private HashMap<String, String> theatreGenres;
	
	private GenreSelectionStepModel() 
	{
		super("Genre", new GenreSelectionStep(true, true, true));
	}
	
	public void setPreselection()
	{	
		try
		{
			OntToDbConnection ontoConn = OntToDbConnection.getInstance();
			PersonAgeStepModel personAgesModel = PersonAgeStepModel.getInstance();

			List<String> cinemaGenreList = ontoConn.getSubClassesOfClassByOntology("CinemaGenres");
			List<String> concertGenreList = ontoConn.getSubClassesOfClassByOntology("ConcertGenres");
			List<String> theatreGenreList = ontoConn.getSubClassesOfClassByOntology("TheatreGenres");
			
			String[] preferedCategories = personAgesModel.getPreferedStuffBasedOnAgeClasses();
			
			setCinemaGenres(buildGenreListWithLikeState(cinemaGenreList, preferedCategories));
			setConcertGenres(buildGenreListWithLikeState(concertGenreList, preferedCategories));
			setTheatreGenres(buildGenreListWithLikeState(theatreGenreList, preferedCategories));
			
		} catch (Exception e)
		{	
			System.out.println("exception");
			e.printStackTrace();
			System.out.println(e.getMessage());
		}
	}
	
	private HashMap<String, String> buildGenreListWithLikeState(List<String> genreList, String[] preferedCats)
	{
		HashMap<String, String> result = new HashMap<String, String>();
		String tmpState;
		
		for(String cat : genreList) {
			tmpState = Arrays.asList(preferedCats).contains(cat) ? LikeBox.LIKE : LikeBox.DONTLIKE; 
			result.put(cat, tmpState);
		}
		
		return result;
	}
	
	public static GenreSelectionStepModel getInstance() 
	{
		if(instance != null)
			return instance;
		
		instance = new GenreSelectionStepModel();
		return instance;
	}

	public String getError() 
	{
		return null;
	}

	public HashMap<String, String> getCinemaGenres() {
		return cinemaGenres;
	}

	public void setCinemaGenres(HashMap<String, String> cinemaGenres) {
		this.cinemaGenres = cinemaGenres;
		updateAlredayFilled();
	}

	public HashMap<String, String> getConcertGenres() {
		return concertGenres;
	}

	public void setConcertGenres(HashMap<String, String> concertGenres) {
		this.concertGenres = concertGenres;
		updateAlredayFilled();
	}

	public HashMap<String, String> getTheatreGenres() {
		return theatreGenres;
	}

	public void setTheatreGenres(HashMap<String, String> theatreGenres) {
		this.theatreGenres = theatreGenres;
		updateAlredayFilled();
	}
}

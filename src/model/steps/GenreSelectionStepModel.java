package model.steps;

import gui.steps.GenreSelectionStep;

import java.util.HashMap;

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
		//TODO
		//PersonAgeStepModel personAgeStepModel = PersonAgeStepModel.getInstance();
		
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
	}

	public HashMap<String, String> getConcertGenres() {
		return concertGenres;
	}

	public void setConcertGenres(HashMap<String, String> concertGenres) {
		this.concertGenres = concertGenres;
	}

	public HashMap<String, String> getTheatreGenres() {
		return theatreGenres;
	}

	public void setTheatreGenres(HashMap<String, String> theatreGenres) {
		this.theatreGenres = theatreGenres;
	}

	
}

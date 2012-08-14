package model.steps;

import java.util.HashMap;

public class GenreSelectionStepModel extends InformationGatherStepModel 
{
	private static GenreSelectionStepModel instance;
	
	private HashMap<String, String> concertGenresMap;
	private HashMap<String, String> theatreGenresMap;
	private HashMap<String, String> cinemaGenresMap;
	
	private GenreSelectionStepModel() 
	{
		
	}
	
	public static GenreSelectionStepModel getInstance() 
	{
		return instance != null ? instance : (instance=new GenreSelectionStepModel());
	}

	public String getError() 
	{
		return null;
	}

	public HashMap<String, String> getConcertGenresMap() {
		return concertGenresMap;
	}

	public void setConcertGenresMap(HashMap<String, String> concertGenresMap) {
		this.concertGenresMap = concertGenresMap;
	}

	public HashMap<String, String> getTheatreGenresMap() {
		return theatreGenresMap;
	}

	public void setTheatreGenresMap(HashMap<String, String> theatreGenresMap) {
		this.theatreGenresMap = theatreGenresMap;
	}

	public HashMap<String, String> getCinemaGenresMap() {
		return cinemaGenresMap;
	}

	public void setCinemaGenresMap(HashMap<String, String> cinemaGenresMap) {
		this.cinemaGenresMap = cinemaGenresMap;
	}

	
}

package ontologyAndDB;

import java.util.HashMap;

public class SpellingMistakeCorrector 
{
	private static HashMap<String, String> mistakes;

	public static String correct(String str)
	{
		init();
		String correct = mistakes.get(str);
		return correct == null ? str : correct;
	}
	
	private static void init()
	{
		if(mistakes == null)
		{
			mistakes = new HashMap<String, String>();
			mistakes.put("ChildPreferredEvents", "ChildFriendlyEvent");
			mistakes.put("AdultsPreferredEvents", "AdulsPreferredEvents");
		}
	}
	
}

package model;

import java.util.HashMap;

class EventCollector 
{
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

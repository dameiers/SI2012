package model.steps;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import org.semanticweb.owlapi.model.OWLOntologyCreationException;

import ontologyAndDB.OntToDbConnection;

import gui.steps.DistanceStep;


public class DistanceStepModel extends InformationGatherStepModel 
{
	private static DistanceStepModel instance;
	public final static String HOUR_UNIT ="h";
	public final static String DISTANCE_UNTI ="km";
	
	private String unit;
	private String distance;
	private OntToDbConnection ontoconn;
	
	private DistanceStepModel() 
	{
		super("Distanz", new DistanceStep());
		try {
			ontoconn = OntToDbConnection.getInstance();
		} catch (OWLOntologyCreationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static DistanceStepModel getInstance() 
	{
		if(instance != null)
			return instance;
		
		instance = new DistanceStepModel();
		return instance;
	}
	
	public String getError() {
		
		if(!hasValidDistance())
			return "Ungültige Entfernung";
		if(!hasValidUnit())
			return "Ungültige Einheit";
		
		return null;
	}
	
	public boolean hasValidDistance()
	{
		return distance != null && distance.matches("[0-9]+(,[0-9]+)?");
	}
	
	public boolean hasValidUnit()
	{
		return "h".equals(unit) || "km".equals(unit);
	}

	public String getUnit() 
	{
		return unit;
	}

	public void setUnit(String unit) 
	{
		this.unit = unit;
		updateAlredayFilled();
	}

	public String getDistance() 
	{
		return distance;
	}

	public void setDistance(String distance) 
	{
		this.distance = distance;
		updateAlredayFilled();
	}
	
	public  ArrayList<String> getReachableCitiesByDistance(double wish_distance) throws Exception {
		ArrayList<String> allcities = ontoconn.getCitiesFromDB();
		ArrayList<String> reachablecities = new ArrayList<String>();
		String origin = OriginLocationStepModel.getInstance().getOrigin();
		for (int i=0; i<allcities.size(); i++){
			String city = allcities.get(i).replace("[", "").replace("]", "").trim();
			origin = normalizeCityName(origin);
			city = normalizeCityName(city);
			double dist = getDistance(origin, city);
			
			if (dist < wish_distance){
				reachablecities.add(city);
			}
		}
//		ontoconn.setDistanceView(reachablecities);
		return reachablecities;
	}
	
	public  ArrayList<String> getReachableCitiesByTime(double wish_time) throws Exception {
		ArrayList<String> allcities = ontoconn.getCitiesFromDB();
		ArrayList<String> reachablecities = new ArrayList<String>();
		String origin = OriginLocationStepModel.getInstance().getOrigin();
		for (int i=0; i<allcities.size(); i++){
			String city = allcities.get(i).replace("[", "").replace("]", "").trim();
			origin = normalizeCityName(origin);
			city = normalizeCityName(city);
			double dist = getDistance(origin, city);
			double estimate_time = dist / 60.0;
			if (dist < wish_time){
				reachablecities.add(city);
			}
		}
//		ontoconn.setDistanceView(reachablecities);
		return reachablecities;
	}
	
	
		public static double[] getLatLon(String city) throws Exception {
		double[] pos = new double[2];
		URL citylat = new URL("http://nominatim.openstreetmap.org/search?q="
				+ city + "&format=xml");
		URLConnection con = citylat.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				con.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine()) != null){
			if (inputLine.contains("lat='")){
				int index = inputLine.indexOf("lat='");
				String lat = inputLine.substring(index+5, inputLine.indexOf("'", index+5));
				pos[0]=Double.parseDouble(lat);
			}
			if (inputLine.contains("lon='")){
				int index = inputLine.indexOf("lon='");
				String lon = inputLine.substring(index+5, inputLine.indexOf("'", index+5));
				pos[1]=Double.parseDouble(lon);
			}
		}
			
		in.close();
		return pos;
	}
		
	public String normalizeCityName(String city){
		city = city.replaceAll("¸", "ue");
		city = city.replaceAll("‰", "ae");
		city = city.replaceAll("ˆ", "oe");
		city = city.replaceAll("ﬂ", "ss");
		city = city.replaceAll(" ", "%20");
		city = city.replaceAll("‹", "UE");
		city = city.replaceAll("A", "AE");
		city = city.replaceAll("÷", "OE");
		return city;
	}
	
	public static double getDistance(String from, String to) throws Exception{
		double[] pos1 = getLatLon(from);
		double[] pos2 = getLatLon(to);
		double dLon = Math.toRadians(pos2[1]-pos1[1]);
		double dLat = Math.toRadians(pos2[0]-pos1[0]);
		double lat1 = Math.toRadians(pos1[0]);
		double lat2 = Math.toRadians(pos2[0]);
		double a = Math.pow(Math.sin(dLat/2), 2)+ Math.pow(Math.sin(dLon/2), 2)* Math.cos(lat1) * Math.cos(lat2); 
		double c = 2*Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		return 6371*c;
	}

	public static double getRouteDistance (String from, String to, String vehicle, String type) throws Exception {
		double[] fpos = getLatLon(from);
		double[] tpos = getLatLon(to);
		URL dis = new URL("http://www.yournavigation.org/api/1.0/gosmore.php?format=kml&flat="+fpos[0]+"&flon="+fpos[1]+"&tlat="+tpos[0]+"&tlon="+tpos[1]+"&v="+vehicle+"&fast="+type);
		URLConnection con = dis.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		while ((inputLine = in.readLine())!=null){
			inputLine = inputLine.trim();
			if (inputLine.startsWith("<distance>") && inputLine.endsWith("</distance>")){
                        	String value = inputLine.substring(10, inputLine.indexOf("</distance"));
				in.close();
				return Double.parseDouble(value);
			}
		}
		in.close();
		return 0;
	}

}

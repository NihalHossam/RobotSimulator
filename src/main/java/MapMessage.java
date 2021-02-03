

package main.java.robotsimulator;

import java.util.HashMap;

/* This class represents a message containing points in the environment which were covered by a robot.
 * By default, robot has to visit 5 points and then send a message specifying exact locations of these points. */
public class MapMessage extends Message {
	
	/*short textual description of the message*/ 
	protected String messageDescription;
	
	/*Name of the reporting robot*/
	private String robotID;
	
	/*collection of points in the environment which were visited by a robot*/
	private HashMap<Position, double[]> searchedAreas;

	/*constructor for MapMessage class*/
	public MapMessage() {
		this.messageDescription = "Message containing positions of visited points in the environment.";
		this.robotID = null;
		this.searchedAreas = new HashMap<Position, double[]>();
	}
	
	/*adding the name of the reporting robot to the message*/
	public void setRobotID(String robotID) {
		this.robotID = robotID;
	}
	
	/*retrieving the name of the reporting robot*/
	public String getRobotID() {
		return this.robotID;
	}

	/*retrieving points in the environment which were visited by a robot*/
	public HashMap<Position, double[]> getSearchedAreas() {
		return this.searchedAreas;
	}

	/*adding collection of the visited points to the message*/
	public void setSearchedAreas(HashMap<Position, double[]> searchedArea) {
		this.searchedAreas = searchedArea;
	}
	
	@Override
	/*specifying the number of visited points in the message description*/
	public void attachMessageDescription(String messageDescription) {
		this.messageDescription = this.messageDescription + " The number of positions in the message is " + 
				Integer.toString(this.searchedAreas.size()) + ". Message is made by the " + messageDescription;
	}
};

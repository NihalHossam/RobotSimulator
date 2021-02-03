
package main.java.robotsimulator;

/************************************************************/
/*This class represents a message which is sent by the robot in case of its physical breakdown and
 * inability to continue to search for target boxes. The message contains ID of the broken robot, its location 
 * in the environment and the reason of failure. 
 */
public class ErrorMessage extends Message {
	/*Exact location in the world of the broken robot*/
	private Position robotLocation;
	
	/*Name of the broken robot*/
	private String robotID;
	
	/*Reason of robot's failure */
	private RobotStatus robotStatus;
	
	/*short textual description of the message*/ 
	protected String messageDescription;
	
	/* constructor for the ErrorMessage class */
	public ErrorMessage(){
		this.robotLocation = null;
		this.robotID = null;
		this.robotStatus = null;
		this.messageDescription = "Error message.";			
	}
	
	/*adding coordinates of the broken robot to the message*/
	public void setRobotLocation(Position robotLocation) {
		this.robotLocation = new Position(robotLocation.getX(), robotLocation.getY());
	}

	/*adding the name of the broken robot to the message*/
	public void setRobotID(String robotID) {
		this.robotID = robotID;
	}

	/*adding the reason of breakdown to the message*/
	public void setRobotStatus(RobotStatus errorStatus) {
		this.robotStatus = errorStatus;
	}
	
	@Override
	/*specifying error parameters in the message description.*/
	public void attachMessageDescription(String messageDescription) {
		this.messageDescription = this.messageDescription + " Name of the broken robot is " + this.robotID
				+ ". Location of the broken robot is " + Double.toString(this.robotLocation.getX())
				+ ", " + Double.toString(this.robotLocation.getY()) + ". Message is made by the " + messageDescription;		
	}

	/* retrieving coordinates of the broken robot*/
	public Position getRobotLocation() {
		return new Position(this.robotLocation.getX(), this.robotLocation.getY());
	}

	/*retrieving the name of the broken robot*/
	public String getRobotID() {
		return this.robotID;
	}

	/*retrieving the reason of the robot's failure*/
	public RobotStatus getRobotStatus(){
		return this.robotStatus;
	}
};

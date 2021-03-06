

package main.java.robotsimulator;

/*Abstract class Message encapsulates relevant information related to the execution of the
 * search mission. Robots can create different types of messages and send their search results
 * to the Central Station*/
public abstract class Message {
	/*short textual description of the information contained in the message*/
	protected String messageDescription;

	/*specifying parameters of the message, for example,  coordinates of the found box, 
	 * name of the robot who has made a message and so on. Parameter indicates the name
	 * of the robot which is an author of this message*/
	public abstract void attachMessageDescription(String messageDescription);

	/*retrieving text description of the message*/
	public String getMessageDescription(){
		return this.messageDescription;
	}
};

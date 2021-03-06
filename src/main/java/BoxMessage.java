

package main.java.robotsimulator;
import java.awt.image.BufferedImage;
/************************************************************/
/*This class represents a message containing photograph of the target box and its location which was discovered by a robot.
 * Message contains a short annotation specifying the coordinates of the found box. 
 */
public class BoxMessage extends Message {
	
	/*box photograph*/
	private BufferedImage boxPhoto;
	
	/*approximate box location*/
	private Position boxLocation;
	
	/*short textual description of the message*/
	protected String messageDescription;

	/*constructor for the BoxMessage class*/
	public BoxMessage() {
		this.boxPhoto = null;
		this.boxLocation = null;
		this.messageDescription = "Message containing the photograph of the box and its coordinates.";		
	}
	
	/*adding box photograph to the message*/
	public void saveBoxPhoto(BufferedImage photo) {
		this.boxPhoto = photo;	
	}

	/*adding position of the found target box to the message*/ 
	 public void saveBoxLocation(Position coordinates) {
		this.boxLocation = new Position(coordinates.getX(), coordinates.getY());
	}

	/*retrieving position of the found target box*/
	public Position getBoxLocation() {
		return new Position(this.boxLocation.getX(), this.boxLocation.getY());
	}

	/*retrieving image of the found target box*/
	public BufferedImage getBoxPhoto() {
		return this.boxPhoto;
	}
	
	@Override
	/* specifying found box position in the world in the description of the message */
	public void attachMessageDescription(String messageDescription) {
		this.messageDescription = this.messageDescription + " Position of the found box is " + 
				Double.toString(this.boxLocation.getX()) + ", " + Double.toString(this.boxLocation.getY()) 
				+ ". Message is made by the " + messageDescription;
	}
};
